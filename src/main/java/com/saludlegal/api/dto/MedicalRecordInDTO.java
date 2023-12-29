package com.saludlegal.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicalRecordInDTO {
	
	private Long patientCC;
	private Long healthCareProfessionalCC;
	private String vitalSigns;
	private String symptomatology;
	private String injuries;
	private String consultationReason;
	private String diagnosis;
	private String medicalBackground;
	private String interconsultations;
	private String epicrisis;
}
