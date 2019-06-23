package com.alizano.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alizano.model.Category;
import com.alizano.model.CategoryCount;
import com.alizano.model.CategoryRepository;
import com.alizano.model.SubCategory;
import com.alizano.utils.Utils;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@RestController
public class CategoryController {


	private static final String CATEGORY = "category";
	private static final String SUBCATEGORY = "subcategory";

	@Autowired
	private CategoryRepository repository;

	@RequestMapping("/process")
	public String process(@RequestBody(required = false) String json) {

		// Category List
		Iterable<Category> categories = repository.findAll();
		List<Category> categoriesList = new ArrayList<>();
		categories.forEach(categoriesList::add);
		List<SubCategory> subCategories = new ArrayList<>();
		Gson gson = new Gson();
		List<CategoryCount> categoryCountList = new ArrayList<>();

		if (json != null && Utils.isJSONValid(json)) {
			
			JsonObject jsonObject;
			JsonElement jsonElement = new JsonParser().parse(json);
			JsonArray jsonArray;
			

			if (jsonElement.isJsonArray()) {
				jsonArray = jsonElement.getAsJsonArray();
				for (int i = 0; i < jsonArray.size(); i++) {
					jsonObject = jsonArray.get(i).getAsJsonObject();
					parseSubCategory(jsonObject, subCategories);
					
				}
			}
			if (jsonElement.isJsonObject()) {
				jsonObject = jsonElement.getAsJsonObject();
				parseSubCategory(jsonObject, subCategories);
			}

			if (!subCategories.isEmpty()) {
				cleanSubcategories(categoriesList, subCategories);
			}
			
			categoryCountList = countSubcategories(subCategories);

		}

		return gson.toJson(categoryCountList);

	}

	private List<SubCategory> cleanSubcategories(List<Category> categories, List<SubCategory> subCategories) {

		// Delete duplicates
		subCategories = removeDuplicates(subCategories);
		// Delete invalid categories
		List<String> validCategories = new ArrayList<>();
		for (Iterator<Category> iterator = categories.iterator(); iterator.hasNext();) {
			Category category = (Category) iterator.next();
			validCategories.add(category.getCategory());
		}
		List<SubCategory> remove = new ArrayList<>();
		for (Iterator<SubCategory> iterator2 = subCategories.iterator(); iterator2.hasNext();) {
			SubCategory subCat = iterator2.next();
			if (!validCategories.contains(subCat.getCategory())) {
				remove.add(subCat);
			}
		}
		subCategories.removeAll(remove);

		return subCategories;

	}

	private List<SubCategory> removeDuplicates(List<SubCategory> subCategories) {
		Set<SubCategory> set = new LinkedHashSet<>();
		set.addAll(subCategories);
		subCategories.clear();
		subCategories.addAll(set);
		return subCategories;
	}
	
	@SuppressWarnings("rawtypes")
	private  List<CategoryCount> countSubcategories(List<SubCategory> subCategories){
		List<CategoryCount> categoryCountList = new ArrayList<>();
		TreeMap<String, Integer> tmap = new TreeMap<String, Integer>(); 
		for (Iterator<SubCategory> iterator2 = subCategories.iterator(); iterator2.hasNext();) {
			SubCategory subCat = iterator2.next();
			 Integer c = tmap.get(subCat.getCategory()); 
	         tmap.put(subCat.getCategory(), (c == null) ? 1 : c + 1);
		}
		for (Map.Entry m : tmap.entrySet()) {
			String cat = m.getKey().toString(); 
			Long count = Long.valueOf(m.getValue().toString());
			categoryCountList.add(new CategoryCount(cat, count));
		}
			
			
		return categoryCountList;
	}
	
	private void parseSubCategory(JsonObject jsonObject, List<SubCategory> subCategories ) {
		if(jsonObject.get(CATEGORY) != null && jsonObject.get(SUBCATEGORY)!=null) {
			SubCategory subCategory = new SubCategory();
			subCategory.setCategory(jsonObject.get(CATEGORY).getAsString());
			subCategory.setSubCategory(jsonObject.get(SUBCATEGORY).getAsString());
			subCategories.add(subCategory);
		}
	}
	
}
