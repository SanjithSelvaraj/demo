package com.figma.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import com.figma.entity.request.CssProperties;
import com.figma.entity.request.LayerProperty;
import com.figma.utils.FigmaUtils;
import com.figma.utils.HTMLGeneratorUtils;

@Service
public class FigmaLayerProcessingService {
	
	public String processFigmaLayerProperties(List<LayerProperty> inputData) {
		HTMLGeneratorUtils utils = new HTMLGeneratorUtils();
		Document doc = utils.parseHtmlTemplate(FigmaUtils.readHTmlGenTemplate());
		int i=0;
		Map<String, List<String>> cssMapper = new HashMap<>();
		for(LayerProperty input: inputData) {			
			String type = input.getType();
			boolean processCss = false;
			String cssPropName = input.getType()+"_"+(++i);
			switch (type){
			case "RECTANGLE":
				doc.body().appendChild(utils.addButton(cssPropName));
				processCss = true;
				break;
			default :
				System.out.println("not matching");
			}
			if(processCss) {
				cssMapper.put(cssPropName, FigmaCssProcessing.cssPropertiesMapper(input.getCssProperties()));
			}
		}
		
		String finalHtml = doc.toString();
		String finalCss = FigmaUtils.mapToString(cssMapper);
		String zipFileName = "";
		try {
			zipFileName = FigmaUtils.writeToFile(finalHtml, finalCss);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return zipFileName;
	}

}


