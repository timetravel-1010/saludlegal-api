package com.saludlegal.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.saludlegal.api.model.HealthcareProfessional;

@Repository
public interface HealthcareProfessionalRepository extends JpaRepository<HealthcareProfessional, Long> {
    
	@Query("SELECT h.healthcareProfessionalID FROM HealthcareProfessional h WHERE h.documentNumber = ?1")
    Long findIdByDocumentNumber(Long documentNumber);
}