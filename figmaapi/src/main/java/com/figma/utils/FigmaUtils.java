package com.figma.utils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.io.FileUtils;

public class FigmaUtils {

	private static String htmlGenFileName = "HtmlGenTemplate.html";

	public static String readHTmlGenTemplate() {
		String htmlString = null;
		try {
			URL url = new FigmaUtils().getClass().getClassLoader().getResource(htmlGenFileName);
			File htmlTemplateFile = new File(url.toURI());
			htmlString = FileUtils.readFileToString(htmlTemplateFile);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return htmlString;
	}
	
	public static String mapToString(Map<String, List<String>> cssMapper) {
		StringBuffer sb = new StringBuffer();
		
		for(Entry<String, List<String>> entry : cssMapper.entrySet()) {
			sb.append(".");
			sb.append(entry.getKey());
			sb.append(" {");
			for(String value : entry.getValue()) {
				sb.append(value);
				sb.append(";");
				sb.append("\n");
			}	
			sb.append("}\n");
		} 		
		return sb.toString();
	}

	public static String writeToFile(String htmlFile, String cssFile) throws IOException {
		String foldername = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		String sourceFolder = "D:\\FigmaFileOutput\\"+foldername;
//		String zipFolderName = sourceFolder + ".zip";
		File htmlTemplateFile = new File(sourceFolder+"\\HtmlOutput.html");
		FileUtils.writeStringToFile(htmlTemplateFile, htmlFile);
		htmlTemplateFile = new File(sourceFolder+"\\MyStyle.css");
		FileUtils.writeStringToFile(htmlTemplateFile, cssFile);
//		ZipUtils appZip = new ZipUtils();
//		
//        appZip.generateFileList(new File(sourceFolder), sourceFolder);
//        appZip.zipIt(zipFolderName,sourceFolder);
        return sourceFolder;
	}
}
