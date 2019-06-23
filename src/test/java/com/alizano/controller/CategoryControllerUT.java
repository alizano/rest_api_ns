package com.alizano.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.alizano.model.Category;
import com.alizano.model.CategoryRepository;


public class CategoryControllerUT {
	
	private String json = "";
	private String response = "";
	private List<Category> categoriesList = new ArrayList<>();
	
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
		
		categoriesList.add(new Category(Long.valueOf(1),"PERSON"));
		categoriesList.add(new Category(Long.valueOf(2),"PLACE"));
		categoriesList.add(new Category(Long.valueOf(3),"ANIMAL"));
		categoriesList.add(new Category(Long.valueOf(4),"COMPUTER"));
		categoriesList.add(new Category(Long.valueOf(5),"OTHER"));
	}

	@Test
	public void testEmptyJson() {
		when(repository.findAll()).thenReturn(categories);
		response = controller.process(json);
		assertNotNull(response);
		assertEquals(response, "[]");
	}
	
	@Test
	public void testInvalidJson() {
		json = "asaklsa";
		when(repository.findAll()).thenReturn(categories);
		response = controller.process(json);
		assertNotNull(response);
		assertEquals(response, "[]");
	}
	
	@Test
	public void testNullJson() {
		when(repository.findAll()).thenReturn(categories);
		response = controller.process(null);
		assertNotNull(response);
		assertEquals(response, "[]");
	}
	
	@Test
	public void testJson() {
		when(repository.findAll()).thenReturn(categories);
		json = "{\r\n" + 
				"		\"category\": \"PERSON\",\r\n" + 
				"		\"subcategory\": \"Bob Jones\"\r\n" + 
				"		}";
		response = controller.process(json);
		assertNotNull(response);
		assertEquals(response, "[]");
	}
	
	@Test
	public void testJsonNoCategory() {
		when(repository.findAll()).thenReturn(categories);
		json = "{\r\n" + 
				"		\"nocategory\": \"PERSON\",\r\n" + 
				"		\"subcategory\": \"Bob Jones\"\r\n" + 
				"		}";
		response = controller.process(json);
		assertNotNull(response);
		assertEquals(response, "[]");
	}
	
	@Test
	public void testJsonNoSubcategory() {
		when(repository.findAll()).thenReturn(categories);
		json = "{\r\n" + 
				"		\"category\": \"PERSON\",\r\n" + 
				"		\"Nosubcategory\": \"Bob Jones\"\r\n" + 
				"		}";
		response = controller.process(json);
		assertNotNull(response);
		assertEquals(response, "[]");
	}
	
	@Test
	public void testSubCategoriesJson() {
		
		json = "[\r\n" + 
				"{\r\n" + 
				"\"category\": \"PERSON\",\r\n" + 
				"\"subcategory\": \"Bob Jones\"\r\n" + 
				"},\r\n" + 
				"{\r\n" + 
				"\"category\": \"PLACE\",\r\n" + 
				"\"subcategory\": \"Washington\"\r\n" + 
				"},\r\n" + 
				"{\r\n" + 
				"\"category\": \"PERSON\",\r\n" + 
				"\"subcategory\": \"Mary\"\r\n" + 
				"},\r\n" + 
				"{\r\n" + 
				"\"category\": \"COMPUTER\",\r\n" + 
				"\"subcategory\": \"Mac\"\r\n" + 
				"},\r\n" + 
				"{\r\n" + 
				"\"category\": \"PERSON\",\r\n" + 
				"\"subcategory\": \"Bob Jones\"\r\n" + 
				"},\r\n" + 
				"{\r\n" + 
				"\"category\": \"OTHER\",\r\n" + 
				"\"subcategory\": \"Tree\"\r\n" + 
				"},\r\n" + 
				"{\r\n" + 
				"\"category\": \"ANIMAL\",\r\n" + 
				"\"subcategory\": \"Dog\"\r\n" + 
				"}\r\n" + 
				",\r\n" + 
				"{\r\n" + 
				"\"category\": \"PLACE\",\r\n" + 
				"\"subcategory\": \"Texas\"\r\n" + 
				"}\r\n" + 
				",\r\n" + 
				"{\r\n" + 
				"\"category\": \"FOOD\",\r\n" + 
				"\"subcategory\": \"Steak\"\r\n" + 
				"}\r\n" + 
				",\r\n" + 
				"{\r\n" + 
				"\"category\": \"ANIMAL\",\r\n" + 
				"\"subcategory\": \"Cat\"\r\n" + 
				"}\r\n" + 
				",\r\n" + 
				"{\r\n" + 
				"\"category\": \"PERSON\",\r\n" + 
				"\"subcategory\": \"Mac\"\r\n" + 
				"}\r\n" + 
				"]";
		when(repository.findAll()).thenReturn(categoriesList);
		response = controller.process(json);
		assertNotNull(response);
		assertEquals(response, "[{\"category\":\"ANIMAL\",\"count\":2},{\"category\":\"COMPUTER\",\"count\":1},{\"category\":\"OTHER\",\"count\":1},{\"category\":\"PERSON\",\"count\":3},{\"category\":\"PLACE\",\"count\":2}]");
	}

}
