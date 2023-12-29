package com.saludlegal.api.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "MEDICAL_RECORDS")
public class MedicalRecord {

	@Id
	@Column(name = "MEDICAL_RECORD_ID")
	private Long medicalRecordID;
	
	@ManyToOne
	@JoinColumn(name = "PATIENT_ID")
	private Patient patientID;
	
	@ManyToOne
	@JoinColumn(name = "HEALTHCARE_PROFESSIONAL_ID")
	private HealthcareProfessional healthcareProfessionalID;
	
	@Column(name = "CREATION_DATE")
	private Date creationDate;
	
	@Column(name = "VITAL_SIGNS")
	private String vitalSigns;
	
	@Column(name = "SYMPTOMATOLOGY")
	private String symptomatology;
	
	@Column(name = "INJURIES")
	private String injuries;
	
	@Column(name = "CONSULTATION_REASON")
	private String consultationReason;
	
	@Column(name = "DIAGNOSIS")
	private String diagnosis;
	
	@Column(name = "MEDICAL_BACKGROUND")
	private String medicalBackground;
	
	@Column(name = "INTERCONSULTATIONS")
	private String interconsultations;
	
	@Column(name = "EPICRISIS")
	private String epicrisis;
	
}
