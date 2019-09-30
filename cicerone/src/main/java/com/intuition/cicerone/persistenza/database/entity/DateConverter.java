package com.intuition.cicerone.persistenza.database.entity;

import java.time.LocalDate;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import java.sql.Date;

@Converter(autoApply=true)
public class DateConverter implements AttributeConverter<LocalDate, Date>  {

	@Override
	public Date convertToDatabaseColumn(LocalDate attribute) {
		return attribute == null ? null : Date.valueOf(attribute);
	}

	@Override
	public LocalDate convertToEntityAttribute(Date dbData) {
		return dbData == null ? null : dbData.toLocalDate();
	}

}
