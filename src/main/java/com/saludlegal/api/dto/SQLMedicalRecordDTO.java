package com.saludlegal.api.dto;

import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;

public class SQLMedicalRecordDTO extends MedicalRecordDTO implements SQLData {
	
	@Override
	public String getSQLTypeName() throws SQLException {
		// TODO Auto-generated method stub
		return "TYPE_MEDICAL_RECORD";
	}

	@Override
	public void readSQL(SQLInput stream, String typeName) throws SQLException {
		// TODO Auto-generated method stub
		setPatientID(stream.readLong());
		setHealthCareProfessionalID(stream.readLong());
		setVitalSigns(stream.readString());
		setSymptomatology(stream.readString());
		setInjuries(stream.readString());
		setConsultationReason(stream.readString());
		setDiagnosis(stream.readString());
		setMedicalBackground(stream.readString());
		setInterconsultations(stream.readString());
		setEpicrisis(stream.readString());
	}

	@Override
	public void writeSQL(SQLOutput stream) throws SQLException {
		// TODO Auto-generated method stub
		stream.writeLong(getPatientID());
		stream.writeLong(getHealthCareProfessionalID());
		stream.writeString(getVitalSigns());
		stream.writeString(getSymptomatology());
		stream.writeString(getInjuries());
		stream.writeString(getConsultationReason());
		stream.writeString(getDiagnosis());
		stream.writeString(getMedicalBackground());
		stream.writeString(getInterconsultations());
		stream.writeString(getEpicrisis());
	}
}