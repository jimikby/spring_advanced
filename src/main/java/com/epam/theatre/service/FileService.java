package com.epam.theatre.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.epam.theatre.domain.Customer;
import com.epam.theatre.domain.Event;
import com.epam.theatre.parser.Converter;
import com.epam.theatre.parser.CustomerListParser;
import com.epam.theatre.parser.EventListParser;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.google.common.collect.Lists;

@Service
@Transactional
public class FileService {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private EventService eventService;

	public List<String> parseFiles(Map<String, MultipartFile> fileMap)
			throws IOException, JsonParseException, JsonMappingException {

		List<String> dataList = Lists.newArrayList();
		for (String key : fileMap.keySet()) {
			if (key.equals("users")) {
				String data = Converter.read(fileMap.get(key).getInputStream());
				List<Customer> customers = CustomerListParser.getUserList(data);
				customerService.saveAll(customers);
				dataList.add(data);
			}

			if (key.equals("events")) {
				String data = Converter.read(fileMap.get(key).getInputStream());
				List<Event> events = EventListParser.getEventList(data);
				eventService.saveAll(events);
				dataList.add(data);
			}

		}
		return dataList;
	}

}
