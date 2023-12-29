package com.saludlegal.api.dto;

import java.sql.Date;
import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;

public class PatientDTOSQL extends PatientDTO implements SQLData {

	@Override
	public String getSQLTypeName() throws SQLException {
		// TODO Auto-generated method stub
		return "TYRC_PATIENT";
	}

	@Override
	public void readSQL(SQLInput stream, String typeName) throws SQLException {
		// TODO Auto-generated method stub
		setDocumentNumber(stream.readLong());
		setDocumentType(stream.readString());
		setName1(stream.readString());
		setName2(stream.readString());
		setLastname1(stream.readString());
		setLastname2(stream.readString());
		setBirthdate(stream.readDate());
		setGender(stream.readString());
		setSex(stream.readString());
		setAddress(stream.readString());
		setPhoneNumber(stream.readString());
		setPlanTypeId(stream.readLong());
	}

	@Override
	public void writeSQL(SQLOutput stream) throws SQLException {
		// TODO Auto-generated method stub
		stream.writeLong(getDocumentNumber().longValue());
		stream.writeString(getDocumentType());
		stream.writeString(getName1());
		stream.writeString(getName2());
		stream.writeString(getLastname1());
		stream.writeString(getLastname2());
		stream.writeDate(getBirthdate());
		stream.writeString(getGender());
		stream.writeString(getSex());
		stream.writeString(getAddress());
		stream.writeString(getPhoneNumber());
		stream.writeLong(getPlanTypeId()); // Probably .longValue is not necessary.
	}
	
}
