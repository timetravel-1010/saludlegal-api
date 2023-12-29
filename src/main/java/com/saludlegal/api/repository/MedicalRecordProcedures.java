package com.saludlegal.api.repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.saludlegal.api.dto.MedicalRecordDetailsDTO;
import com.saludlegal.api.dto.SQLMedicalRecordDTO;


@Repository
public class MedicalRecordProcedures {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	public void createMedicalRecord(final SQLMedicalRecordDTO medRecord) {
		SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
				.withSchemaName("APP_HIST_CLINICA")
				.withCatalogName("PKG_APP_CREATE_MEDICAL_RECORDS")
				.withProcedureName("CREATE_MEDICAL_RECORD")
				.declareParameters(
						new SqlParameter("in_med_record", Types.STRUCT, "TYPE_MEDICAL_RECORD"));
		
		Map<String, SQLMedicalRecordDTO> in = Collections.singletonMap("in_med_record", medRecord);
		call.executeObject(SQLMedicalRecordDTO.class, in);
	}
	
	public List<MedicalRecordDetailsDTO> getMedicalRecordByPatientDocument(Long patientDocumentNumber) {
		List<MedicalRecordDetailsDTO> medicalRecords = new ArrayList<MedicalRecordDetailsDTO>();
		String call = "{CALL APP_HIST_CLINICA.PKG_MEDICAL_RECORDS.GET_PATIENT_MEDICAL_RECORDS(?, ?)}";
		
		try(Connection conn = this.jdbcTemplate.getDataSource().getConnection();
			CallableStatement cs = conn.prepareCall(call);
		) {

			cs.setLong(1, patientDocumentNumber);
			cs.registerOutParameter(2, Types.REF_CURSOR);
			cs.executeQuery();	
			ResultSet rs = (ResultSet) cs.getObject(2);
			
			if (rs == null) {
				return null;
			}
			
			while (rs.next()) {
				MedicalRecordDetailsDTO md = new MedicalRecordDetailsDTO();
				md.setMedicalRecordID(rs.getLong(1));
				md.setPatientID(rs.getLong(2));
				md.setPatientName(rs.getString(3));
				md.setHealthcareProfessionalID(rs.getLong(4));
				md.setHealthcareProfessionalName(rs.getString(5));
				md.setCreationDate(rs.getDate(6));
				md.setVitalSigns(rs.getString(7));
				md.setSymptomatology(rs.getString(8));
				md.setInjuries(rs.getString(9));
				md.setConsultationReason(rs.getString(10));
				md.setDiagnosis(rs.getString(11));
				md.setMedicalBackground(rs.getString(12));
				md.setInterconsultations(rs.getString(13));
				md.setEpicrisis(rs.getString(14));
				
				medicalRecords.add(md);
			}
			
			return medicalRecords;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void createMedicalRecordsReport(String objectDirectory) {
		SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
				.withSchemaName("APP_HIST_CLINICA")
				.withCatalogName("PKG_MEDICAL_RECORDS_REPORTS")
				.withProcedureName("GENERATE_MEDICAL_RECORDS_REPORT")
				.declareParameters(
						new SqlParameter("directory", Types.VARCHAR));
		
		Map<String, String> in = Collections.singletonMap("directory", objectDirectory);
		call.executeObject(SQLMedicalRecordDTO.class, in);
	}

}