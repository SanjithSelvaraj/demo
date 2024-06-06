package com.figma.entity.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CssProperties {
	
	@JsonProperty("width")
	private double width;
	
	@JsonProperty("heigth")
	private double height;
	
	@JsonProperty("xaxis")
	private double xAxis;
	
	@JsonProperty("yaxis")
	private double yAxis;
	
	@JsonProperty("r")
	private double rColor;
	
	@JsonProperty("b")
	private double bColor;
	
	@JsonProperty("g")
	private double gColor;

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getxAxis() {
		return xAxis;
	}

	public void setxAxis(double xAxis) {
		this.xAxis = xAxis;
	}

	public double getyAxis() {
		return yAxis;
	}

	public void setyAxis(double yAxis) {
		this.yAxis = yAxis;
	}

	public double getrColor() {
		return rColor;
	}

	public void setrColor(double rColor) {
		this.rColor = rColor;
	}

	public double getbColor() {
		return bColor;
	}

	public void setbColor(double bColor) {
		this.bColor = bColor;
	}

	public double getgColor() {
		return gColor;
	}

	public void setgColor(double gColor) {
		this.gColor = gColor;
	}
}
