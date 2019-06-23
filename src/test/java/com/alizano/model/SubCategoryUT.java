package com.alizano.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;


public class SubCategoryUT {
	
	@Before
	public void setUp() throws Exception {
		subCategory = new SubCategory();
	}
	
	private SubCategory subCategory;

	@Test
	public void testToString() {		
		assertNotNull(subCategory.toString());		
	}
	
	@Test
	public void testEquals() {
		assertTrue(subCategory.equals(new SubCategory()));
		assertFalse(subCategory.equals(new Category(null, null)));
		assertFalse(subCategory.equals(null));
	}
	

}
