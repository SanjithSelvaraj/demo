package com.figma.utils;

public enum FigmaCssMapper {
	
	WIDTH("width"),
	HEIGTH("heigth"),
	XAXIS("margin-top"),
	YAXIS("margin-left"),
	BGCOLOR("background-color");
	
	public final String value;

    private FigmaCssMapper(String value) {
        this.value = value;
    }

}