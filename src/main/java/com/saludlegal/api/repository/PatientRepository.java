package com.saludlegal.api.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.saludlegal.api.dto.PatientDTOSQL;
import com.saludlegal.api.model.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long>{
	
	
	  @Procedure(name = "Patient.create_PATIENT") 
	  public void createPatient(@Param("iPatient") PatientDTOSQL iPatient);
	 
	  public Patient findByDocumentNumber(Long documentNumber);
	
}
