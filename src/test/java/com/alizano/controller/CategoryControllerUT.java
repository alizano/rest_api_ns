package com.alizano.controller;

import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.alizano.model.Category;
import com.alizano.model.CategoryRepository;


public class CategoryControllerUT {
	
	@Mock
	private CategoryRepository repository;
	
	@Mock
	private Iterable<Category> categories;
	
	@InjectMocks
	CategoryController controller;
	
	@Before public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }


	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		
		when(repository.findAll()).thenReturn(categories);
		controller.process("");
	}

}
