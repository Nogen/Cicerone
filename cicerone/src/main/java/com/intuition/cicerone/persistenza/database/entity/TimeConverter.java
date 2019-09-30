package com.intuition.cicerone.persistenza.database.entity;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.LocalTime;
import java.sql.Time;

@Converter(autoApply=true)
public class TimeConverter implements AttributeConverter<LocalTime, Time>{

	@Override
	public Time convertToDatabaseColumn(LocalTime attribute) {
		return attribute == null ? null : Time.valueOf(attribute);
	}

	@Override
	public LocalTime convertToEntityAttribute(Time dbData) {
		return dbData == null ? null : dbData.toLocalTime();
	}

}
