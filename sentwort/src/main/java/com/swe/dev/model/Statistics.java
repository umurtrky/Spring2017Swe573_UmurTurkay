package com.swe.dev.model;


public class Statistics {
	public Statistics(String category, double value, String color) {
		this.setCategory(category);
        this.setValue(value);
        this.setColor(color);
    } 

	private String category;
	private double value;
    private String color;

    public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
