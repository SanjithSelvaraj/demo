package com.figma.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

import com.figma.entity.request.CssProperties;
import com.figma.utils.FigmaCssMapper;

public class FigmaCssProcessing {
	
	public static List<String> cssPropertiesMapper(CssProperties cssProperties){
		 List<String> cssPropertiesList = new ArrayList<>();
		 
		 if(!StringUtils.isEmpty(cssProperties.getHeight())) {
			 cssPropertiesList.add(FigmaCssMapper.HEIGTH.value +":"+cssProperties.getHeight() +"px");
		 }
		 
		 if(!StringUtils.isEmpty(cssProperties.getWidth())) {
			 cssPropertiesList.add(FigmaCssMapper.WIDTH.value +":"+cssProperties.getWidth() +"px");
		 }
		 
		 if(!StringUtils.isEmpty(cssProperties.getxAxis())) {
			 cssPropertiesList.add(FigmaCssMapper.XAXIS.value +":"+cssProperties.getxAxis() +"px");
		 }
		 
		 if(!StringUtils.isEmpty(cssProperties.getyAxis())) {
			 cssPropertiesList.add(FigmaCssMapper.YAXIS.value +":"+cssProperties.getyAxis() +"px");
		 }
		
		 if(!StringUtils.isEmpty(cssProperties.getrColor())) {
			 cssPropertiesList.add(FigmaCssMapper.BGCOLOR.value +":rgb("+cssProperties.getrColor()+","
					 + cssProperties.getbColor() + "," + cssProperties.getgColor() + ")") ;
		 }		
		
		return cssPropertiesList;
	}
}
