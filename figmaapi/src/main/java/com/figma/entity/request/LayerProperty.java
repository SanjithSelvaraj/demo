package com.figma.entity.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LayerProperty {

	@JsonProperty("type")
	private String type;
	
	@JsonProperty("cssProperties")
	private CssProperties cssProperties;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public CssProperties getCssProperties() {
		return cssProperties;
	}

	public void setCssProperties(CssProperties cssProperties) {
		this.cssProperties = cssProperties;
	}
	
}
