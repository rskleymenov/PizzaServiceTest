package domain;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class StateConverter implements AttributeConverter<State, String>{

	@Override
	public String convertToDatabaseColumn(State attribute) {
		return attribute.state;
	}

	@Override
	public State convertToEntityAttribute(String dbData) {
		return new State(dbData);
	}

}
