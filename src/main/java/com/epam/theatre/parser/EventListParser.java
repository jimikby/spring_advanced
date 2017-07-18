package com.epam.theatre.parser;

import java.io.IOException;
import java.util.List;

import com.epam.theatre.domain.Event;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.common.collect.Lists;

public class EventListParser {

	public static List<Event> getEventList(String data) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		List<Event> events = Lists.newArrayList();
		if (!data.isEmpty()) {
			events = mapper.readValue(data,
					TypeFactory.defaultInstance().constructCollectionType(List.class, Event.class));
		}
		return events;
	}
}
