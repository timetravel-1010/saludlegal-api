package com.saludlegal.api.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicalRecordDetailsDTO {

	private Long medicalRecordID;
	private Long patientID;
	private String patientName;
	private Long healthcareProfessionalID;
	private String healthcareProfessionalName;
	private Date creationDate;
	private String vitalSigns;
	private String symptomatology;
	private String injuries;
	private String consultationReason;
	private String diagnosis;
	private String medicalBackground;
	private String interconsultations;
	private String epicrisis;
}
