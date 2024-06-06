package com.figma.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.jsoup.select.Elements;

public class HTMLGeneratorUtils {
	
	public Document parseHtmlTemplate(String htmlContent)
	{
		Document htmlDoc = null;
		try {
			htmlDoc = Jsoup.parse(htmlContent);
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		return htmlDoc;
	}
	
	public Element addButton(String cssPropName)
	{
		Element button = new Element(Tag.valueOf("button"), "")
				  .text("Check");
		button.addClass(cssPropName);
		return button;
	}
	
	public static void main(String arg[]) {
		HTMLGeneratorUtils utils = new HTMLGeneratorUtils();
		Document doc = utils.parseHtmlTemplate(FigmaUtils.readHTmlGenTemplate());
		doc.body().appendChild(utils.addButton("test"));
		System.out.println(doc.toString());
		}
	
}
