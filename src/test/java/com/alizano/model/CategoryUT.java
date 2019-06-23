package com.alizano.model;

import javax.persistence.Entity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alizano.AssertAnnotations;

@RunWith(SpringRunner.class)
@SpringBootTest
@SpringBootConfiguration
public class CategoryUT {

	@Test
	public void typeAnnotations() {
		// assert
		AssertAnnotations.assertType(Category.class, Entity.class);
	}

	@Test
	public void fieldAnnotations() {
		// assert
		AssertAnnotations.assertField(Category.class, "id");
		AssertAnnotations.assertField(Category.class, "category");
	}

}
