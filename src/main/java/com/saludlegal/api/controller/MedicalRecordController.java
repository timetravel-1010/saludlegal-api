package com.saludlegal.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saludlegal.api.dto.MedicalRecordDetailsDTO;
import com.saludlegal.api.dto.MedicalRecordInDTO;
import com.saludlegal.api.service.MedicalRecordService;

@RestController
@RequestMapping("medical-records")
@CrossOrigin(origins = "*")
public class MedicalRecordController {

	@Autowired
	private MedicalRecordService medRecordService;

	@GetMapping("{patientDocumentNumber}")
	public ResponseEntity<List<MedicalRecordDetailsDTO>> getMedicalRecords(@PathVariable Long patientDocumentNumber) {
		try {
			return ResponseEntity.status(HttpStatus.OK)
					.body(this.medRecordService.getMedicalRecordsByPatientDocument(patientDocumentNumber));
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@PostMapping
	public ResponseEntity<String> create(@RequestBody MedicalRecordInDTO medRecordDTO) {
		try {
			medRecordService.createMedicalRecord(medRecordDTO);
			return ResponseEntity.status(HttpStatus.CREATED).body("La historia clínica se ha creado exitosamente.");
		} catch (Exception e) {
			System.out.println(e.getMessage());

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ha ocurrido un error.");
		}
	}

	@PostMapping("reports")
	public ResponseEntity<String> generateMedicalRecordsReport(@RequestBody String objectDirectory) {
		try {
			System.out.println("directory: " + objectDirectory);
			medRecordService.createMedicalRecordsReport(objectDirectory);
			return ResponseEntity.status(HttpStatus.CREATED).body("Reporte generado exitosamente.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ha ocurrido un error.");
		}
	}
}
