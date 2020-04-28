package com.groferstest.webservice.filtering;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {
	
	
	@RequestMapping(method= RequestMethod.GET, path ="/filtering")
	public MappingJacksonValue retrieveBean()
	{
		Somebean somebean = new Somebean("value 1", "value 2","value 3 ");
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1","field2");
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBean", filter);
		
		MappingJacksonValue mapping = new MappingJacksonValue(somebean);
		
		mapping.setFilters(filters);
		return mapping;
	}

}
