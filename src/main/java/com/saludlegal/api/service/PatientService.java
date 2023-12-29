package com.saludlegal.api.service;

import java.util.List;

import com.saludlegal.api.dto.PatientDTO;
import com.saludlegal.api.dto.PatientDTOSQL;


public interface PatientService {
	
	public void createPatient(PatientDTOSQL p);
	
	public void createPatients(List<PatientDTO> patients);
}
