package com.alizano.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alizano.model.Category;
import com.alizano.model.CategoryRepository;

@RestController
public class CategoryController {
	
	
	private static final Logger log = LoggerFactory.getLogger(CategoryController.class);

	
	@Autowired
	private CategoryRepository repository;

	@RequestMapping("/process")
	public String process(@RequestParam(value="name", defaultValue="[{}]") String json) {
		Iterable<Category> categories = repository.findAll();
		/**
		 * TODO load json from request then return json
		 * */
		
		
		log.info(categories.toString());
		log.info(json);
		return "";
		
	}
}
