package com.saludlegal.api.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "HEALTHCARE_PROFESSIONALS")
public class HealthcareProfessional {

	@Id
	@Column(name = "HEALTHCARE_PROFESSIONAL_ID")
	private Long healthcareProfessionalID;
	
	@Column(name = "DOCUMENT_NUMBER")
	private Long documentNumber;
	
	@Column(name = "NAME_1")
	private String name1;
	
	@Column(name = "NAME_2")
	private String name2;
	
	@Column(name = "LASTNAME_1")
	private String lastname1;
	
	@Column(name = "LASTNAME_2")
	private String lastname2;
	
	@Column(name = "BIRTH_DATE")
	private Date birthdate;
	
	@Column(name = "MEDICAL_LICENSE")
	private Long medicalLicense;
	
	@Column(name = "SPECIALTY_ID")
	private Long specialtyID;
	
	@Column(name = "ADDRESS")
	private String address;
	
	@Column(name = "PHONE_NUMBER")
	private String phoneNumber;
	
}
