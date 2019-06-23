package com.alizano.model;

import java.io.Serializable;

public class SubCategory implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8371117529050805956L;

	private String category = "";
	private String subCategory = "";

	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * @return the subCategory
	 */
	public String getSubCategory() {
		return subCategory;
	}

	/**
	 * @param subCategory the subCategory to set
	 */
	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		SubCategory subCategoryObj = (SubCategory) o;
		return category.equals(subCategoryObj.category) && subCategory.equals(subCategoryObj.subCategory);
	}

	@Override
	public int hashCode() {
		return (this.category.hashCode() + this.subCategory.hashCode());
	}

	@Override
	public String toString() {
		return "SubCategory [category=" + category + ", subCategory=" + subCategory + "]";
	}
	
	

}
