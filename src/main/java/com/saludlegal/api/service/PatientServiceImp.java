package com.saludlegal.api.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saludlegal.api.dto.PatientDTO;
import com.saludlegal.api.dto.PatientDTOSQL;
import com.saludlegal.api.repository.PatientProcedures;
import com.saludlegal.api.repository.PatientRepository;

@Service
public class PatientServiceImp implements PatientService {
	
	@Autowired
	private PatientRepository patientRepository;
	@Autowired
	private PatientProcedures patientProcedures;
	

	@Override
	public void createPatient(PatientDTOSQL p) {
		//patientRepository.createPatient(p);
		this.patientProcedures.addPatient(p);
	}


	@Override
	public void createPatients(List<PatientDTO> patients) {
		this.patientProcedures.addPatients(patients);
	}
}
