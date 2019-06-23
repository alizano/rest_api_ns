package com.alizano.utils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;


public class UtilsUT {

	
	@InjectMocks
	Utils controller;
	
	@Before public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }


	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testNotValidJason() {
		String json = "No Valid Json";
		assertFalse(Utils.isJSONValid(json));
	}
	
	@Test
	public void testValidJason() {
		String json = "{\r\n" + 
				"\"category\": \"PLACE\",\r\n" + 
				"\"subcategory\": \"Texas\"\r\n" + 
				"}";
		assertTrue(Utils.isJSONValid(json));
	}

}
