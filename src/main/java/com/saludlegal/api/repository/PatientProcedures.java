package com.saludlegal.api.repository;

import java.sql.Array;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Struct;
import java.sql.Types;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.saludlegal.api.dto.PatientDTO;
import com.saludlegal.api.dto.PatientDTOSQL;

import oracle.jdbc.driver.OracleConnection;


@Repository
public class PatientProcedures {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	
	public void addPatient(PatientDTOSQL patient) {

		SimpleJdbcCall addPatientCall = new SimpleJdbcCall(jdbcTemplate)
				.withSchemaName("APP_HIST_CLINICA")
				.withCatalogName("PKG_APP_CREATE_PATIENTS")
				.withProcedureName("create_PATIENT")
				.declareParameters(
						new SqlParameter("iPatient", Types.STRUCT, "TYRC_PATIENT"));
		
		Map<String, PatientDTOSQL> in = Collections.singletonMap("iPatient", patient);
		addPatientCall.executeObject(PatientDTOSQL.class, in);
	}


	public void addPatients(List<PatientDTO> patients) {
		
		try(Connection conn = this.jdbcTemplate.getDataSource().getConnection();
		) {
			Object[] fields = new Object[17];
			Struct[] structs = new Struct[patients.size()];
			OracleConnection oc = conn.unwrap(OracleConnection.class);
			
			int i = 0;
			for (PatientDTO p : patients) {
				fields[0] = 0; // id
				fields[1] = p.getDocumentNumber();
				fields[2] = p.getDocumentType();
				fields[3] = p.getName1();
				fields[4] = p.getName2();
				fields[5] = p.getLastname1();
				fields[6] = p.getLastname2();
				fields[7] = p.getBirthdate();
				fields[8] = p.getGender();
				fields[9] = p.getSex();
				fields[10] = p.getAddress();
				fields[11] = p.getPhoneNumber();
				fields[12] = Date.valueOf("2001-12-12"); // affiliationDate
				fields[13] = p.getPlanTypeId();
				fields[14] = Date.valueOf("2001-12-12"); // activeDate
				fields[15] = 1; // isActive
				fields[16] = null; // retirementDate
		        structs[i++] = oc.createStruct("PKG_PATIENTS.TYRCPATIENT", fields);
			}
			Array patientsArray = oc.createOracleArray("PKG_PATIENTS.TABLE_PATIENTS", structs);
			
			SimpleJdbcCall addPatientsCall = new SimpleJdbcCall(jdbcTemplate)
					.withSchemaName("APP_HIST_CLINICA")
					.withCatalogName("PKG_PATIENTS")
					.withProcedureName("INSERT_PATIENTS")
					.declareParameters(
							new SqlParameter("io_records", Types.ARRAY)
					);
			
			MapSqlParameterSource msql = new MapSqlParameterSource();
			msql.addValue("io_records", patientsArray);
			addPatientsCall.executeObject(Array.class, msql);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}