package com.saludlegal.api.model;


import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table(name = "PATIENTS")
public class Patient {
	
	@Id
	@Column(name = "PATIENT_ID")
	private Long patientID;
	
	@Column(name = "DOCUMENT_NUMBER")
	private Long documentNumber;
	
	@Column(name = "DOCUMENT_TYPE")
	private String documentType;
	
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
	
	@Column(name = "GENDER")
	private String gender;
	
	@Column(name = "SEX")
	private String sex;
	
	@Column(name = "ADDRESS")
	private String address;
	
	@Column(name = "PHONE_NUMBER")
	private String phoneNumber;
	
	@Column(name = "AFFILIATION_DATE")
	private Date affiliationDate;
	
	@Column(name = "PLAN_TYPE_ID")
	private Long planTypeId;
	
	@Column(name = "ACTIVE_DATE")
	private Date activeDate;
	
	@Column(name = "IS_ACTIVE")
	private Integer isActive;
	
	@Column(name = "RETIREMENT_DATE")
	private Date retirementDate;
}
