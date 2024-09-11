package com.codegnan.controller;

import java.io.File;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.codegnan.entity.FileData;

import com.codegnan.service.StorageService;
import java.nio.file.Files;

@RestController
@RequestMapping("/admin/image")

public class ImageuploadController {
	
	@Autowired
	private StorageService storageService;
	

	 @PostMapping("/fileSystem")
	    public ResponseEntity<?> addProjectActivity(
	    		 @RequestParam("projectTitle") String projectTitle,
	    	        @RequestParam("description") String description,
	            @RequestParam("beforeImage") MultipartFile beforeImage,
	            @RequestParam("afterImage") MultipartFile afterImage) throws IOException {
		 
	        String result = storageService.addActivity(projectTitle, description, beforeImage, afterImage);
	        return ResponseEntity.status(HttpStatus.OK).body(result);
	    }


	 
	 
	 @GetMapping("/fileSystem/{fileName}")
	 public ResponseEntity<byte[]> downloadBeforeAndAfterImages(@PathVariable String fileName) throws IOException {
	     File file = new File("E:/snrepair-app/src/ImageDic/" + fileName);
	     
	     if (!file.exists()) {
	         throw new FileNotFoundException("File not found");
	     }
	     byte[] imageBytes = Files.readAllBytes(file.toPath());

	     String contentType = Files.probeContentType(file.toPath());

	     return ResponseEntity
	         .ok()
	         .contentType(MediaType.parseMediaType(contentType))
	         .body(imageBytes);
	 }


	    
	    @GetMapping("/project-activities")
	    public ResponseEntity<List<FileData>> getAllProjectActivities() {
	        List<FileData> activities = storageService.getAllProjectActivities();
	        return ResponseEntity.ok(activities);
	    }
	

}
