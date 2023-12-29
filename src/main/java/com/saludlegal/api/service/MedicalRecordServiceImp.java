package com.saludlegal.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saludlegal.api.dto.MedicalRecordDetailsDTO;
import com.saludlegal.api.dto.MedicalRecordInDTO;
import com.saludlegal.api.dto.SQLMedicalRecordDTO;
import com.saludlegal.api.model.MedicalRecord;
import com.saludlegal.api.model.Patient;
import com.saludlegal.api.repository.HealthcareProfessionalRepository;
import com.saludlegal.api.repository.MedicalRecordProcedures;
import com.saludlegal.api.repository.MedicalRecordRepository;
import com.saludlegal.api.repository.PatientRepository;

@Service
public class MedicalRecordServiceImp implements MedicalRecordService {
	
	@Autowired
	private MedicalRecordRepository medRecordRepository;
	@Autowired
	private MedicalRecordProcedures medRecordProcedures;
	@Autowired
	private PatientRepository patientRepository;
	@Autowired
	private HealthcareProfessionalRepository healthcareProfRepository;


	@Override
	public void createMedicalRecord(MedicalRecordInDTO medRecord) {
		SQLMedicalRecordDTO medRecordDTO = new SQLMedicalRecordDTO();
		
		Patient p = patientRepository.findByDocumentNumber(medRecord.getPatientCC());
		Long patientID = p.getPatientID();
		System.out.println(patientID);
		medRecordDTO.setPatientID(patientID);
		medRecordDTO.setHealthCareProfessionalID(healthcareProfRepository.findIdByDocumentNumber(medRecord.getHealthCareProfessionalCC()));
		medRecordDTO.setVitalSigns(medRecord.getVitalSigns());
		medRecordDTO.setSymptomatology(medRecord.getSymptomatology());
		medRecordDTO.setInjuries(medRecord.getInjuries());
		medRecordDTO.setConsultationReason(medRecord.getConsultationReason());
		medRecordDTO.setDiagnosis(medRecord.getDiagnosis());
		medRecordDTO.setMedicalBackground(medRecord.getMedicalBackground());
		medRecordDTO.setInterconsultations(medRecord.getInterconsultations());
		medRecordDTO.setEpicrisis(medRecord.getEpicrisis());
		
		this.medRecordProcedures.createMedicalRecord(medRecordDTO);
	}

	@Override
	public List<MedicalRecordDetailsDTO> getMedicalRecordsByPatientDocument(Long patientDocumentNumber) {
		return this.medRecordProcedures.getMedicalRecordByPatientDocument(patientDocumentNumber);
	}
	
	@Override
	public void createMedicalRecordsReport(String objectDirectory) {
		this.medRecordProcedures.createMedicalRecordsReport(objectDirectory);
	}
}
