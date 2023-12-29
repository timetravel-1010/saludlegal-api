package com.saludlegal.api.dto;


import java.sql.Date;

import com.opencsv.bean.CsvBindByName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientDTO {
	@CsvBindByName
	private Long documentNumber;
	@CsvBindByName
	private String documentType;
	@CsvBindByName
	private String name1;
	@CsvBindByName
	private String name2;
	@CsvBindByName
	private String lastname1;
	@CsvBindByName
	private String lastname2;
	@CsvBindByName
	private Date birthdate;
	@CsvBindByName
	private String gender;
	@CsvBindByName
	private String sex;
	@CsvBindByName
	private String address;
	@CsvBindByName
	private String phoneNumber;
	@CsvBindByName
	private Long planTypeId;
}
