package com.figma.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.figma.entity.request.LayerProperty;
import com.figma.service.FigmaLayerProcessingService;

@RestController
public class FigmaController {
	
	@Autowired
	private FigmaLayerProcessingService figmaService;

	@PostMapping(value ="/process") 
    public void processRequest(@RequestBody List<LayerProperty> inputData, HttpServletResponse response) throws IOException { 
		
		String zipFileName = figmaService.processFigmaLayerProperties(inputData);
//		String zipFileName = "D:\\FigmaFileOutput\\20240411161240069";
		response.setStatus(HttpServletResponse.SC_OK);
		response.setContentType("application/octet-stream");
		response.addHeader("Content-Disposition", "attachment; filename=download.zip");
		File folder = new File(zipFileName);
		File[] listOfFiles = folder.listFiles();
		List<String> filesList = new ArrayList<>();
		for (File file : listOfFiles) {
		    if (file.isFile()) {
		    	filesList.add(file.getName());
		    }
		}
		try (ZipOutputStream zippedOut = new ZipOutputStream(response.getOutputStream())) {
            for (String file : filesList) {
                FileSystemResource resource = new FileSystemResource(zipFileName+"\\"+file);

                ZipEntry e = new ZipEntry(resource.getFilename());
                // Configure the zip entry, the properties of the file
                e.setSize(resource.contentLength());
                e.setTime(System.currentTimeMillis());
                // etc.
                zippedOut.putNextEntry(e);
                // And the content of the resource:
                StreamUtils.copy(resource.getInputStream(), zippedOut);
                zippedOut.closeEntry();
            }
            zippedOut.finish();
        } catch (Exception e) {
             System.out.println(e.getMessage());
        }
    } 
}