package com.alizano.model;

public class CategoryCount {
	
	private String category;
	
	private Long count;

	public CategoryCount(String category, Long count) {
		this.category = category;
		this.count = count;
	}
	
	public CategoryCount() {
		this.category = "";
		this.count = Long.valueOf(0);
	}

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
	 * @return the count
	 */
	public Long getCount() {
		return count;
	}

	/**
	 * @param count the count to set
	 */
	public void setCount(Long count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "CategoryCount [category=" + category + ", count=" + count + "]";
	}
	

}
