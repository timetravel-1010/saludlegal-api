package com.saludlegal.api.service;

import java.util.List;

import com.saludlegal.api.dto.MedicalRecordDetailsDTO;
import com.saludlegal.api.dto.MedicalRecordInDTO;
import com.saludlegal.api.dto.SQLMedicalRecordDTO;
import com.saludlegal.api.model.MedicalRecord;

public interface MedicalRecordService {
	
	public void createMedicalRecord(MedicalRecordInDTO medRecord);
	
	public List<MedicalRecordDetailsDTO> getMedicalRecordsByPatientDocument(Long patientDocumentNumber);

	public void createMedicalRecordsReport(String objectDirectory);
}
