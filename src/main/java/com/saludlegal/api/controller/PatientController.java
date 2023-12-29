package com.saludlegal.api.controller;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.saludlegal.api.dto.PatientDTO;
import com.saludlegal.api.dto.PatientDTOSQL;
import com.saludlegal.api.model.Patient;
import com.saludlegal.api.repository.PatientProcedures;
import com.saludlegal.api.service.PatientService;
import com.saludlegal.api.service.PatientServiceImp;

@RestController
@RequestMapping("patients")
@CrossOrigin(origins = "*")
public class PatientController {

	@Autowired
	private PatientServiceImp patientService;

	@PostMapping
	public ResponseEntity<String> create(@RequestBody PatientDTOSQL p) {
		try {
			patientService.createPatient(p);
			return ResponseEntity.status(HttpStatus.CREATED).body("Usuario creado exitosamente"); 
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ha ocurrido un error.");
		}
	}

	@PostMapping("upload-csv-file")
	public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
		
		try(Reader reader = new InputStreamReader(file.getInputStream());) {
			
			CsvToBean<PatientDTO> csvToBean = new CsvToBeanBuilder<PatientDTO>(reader)
													.withType(PatientDTO.class)
													.withIgnoreLeadingWhiteSpace(true)
													.build();
			
			List<PatientDTO> patients = csvToBean.parse();
			int patientsSize = patients.size();
			
			patientService.createPatients(patients);
			
			System.out.println("Se han registrado "+patientsSize+" pacientes exitosamente.");
			
			return ResponseEntity.status(HttpStatus.CREATED)
					.body("Se han registrado "+patientsSize+" pacientes exitosamente.");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ha ocurrido un error.");
		}
	}
}
