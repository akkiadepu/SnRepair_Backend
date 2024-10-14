package com.codegnan.controller;

import java.io.File;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.codegnan.entity.FileData;

import com.codegnan.service.StorageService;
import java.nio.file.Files;

@RestController
@RequestMapping("/admin/image")
//@CrossOrigin(origins = "*")
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
	    
	    
	 // Add in ImageuploadController
	    @PutMapping("/{id}")
	    public ResponseEntity<?> updateProjectActivity(
	            @PathVariable Long id,
	            @RequestParam("projectTitle") String projectTitle,
	            @RequestParam("description") String description,
	            @RequestParam(value = "beforeImage", required = false) MultipartFile beforeImage,
	            @RequestParam(value = "afterImage", required = false) MultipartFile afterImage) throws IOException {

	        Optional<FileData> fileDataOpt = storageService.getActivityById(id);
	        if (fileDataOpt.isPresent()) {
	            FileData fileData = fileDataOpt.get();
	            
	            fileData.setProjectTitle(projectTitle);
	            fileData.setDescription(description);
	            
	            if (beforeImage != null && !beforeImage.isEmpty()) {
	                String beforeImagePath = storageService.uploadImage(beforeImage);
	                fileData.setBeforeImageName(beforeImage.getOriginalFilename());
	                fileData.setBeforeImagePath(beforeImagePath);
	            }
	            
	            if (afterImage != null && !afterImage.isEmpty()) {
	                String afterImagePath = storageService.uploadImage(afterImage);
	                fileData.setAfterImageName(afterImage.getOriginalFilename());
	                fileData.setAfterImagePath(afterImagePath);
	            }

	            storageService.saveActivity(fileData);
	            return ResponseEntity.status(HttpStatus.OK).body("Project updated successfully");
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Project not found");
	        }
	    }
	    
	    
	    
	 // Add in ImageuploadController
	    @DeleteMapping("/{id}")
	    public ResponseEntity<?> deleteProjectActivity(@PathVariable Long id) {
	        Optional<FileData> fileDataOpt = storageService.getActivityById(id);
	        if (fileDataOpt.isPresent()) {
	            FileData fileData = fileDataOpt.get();

	            // Delete the images from the file system
	            storageService.deleteImage(fileData.getBeforeImagePath());
	            storageService.deleteImage(fileData.getAfterImagePath());

	            // Delete the record from the database
	            storageService.deleteActivityById(id);

	            return ResponseEntity.status(HttpStatus.OK).body("Project and images deleted successfully");
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Project not found");
	        }
	    }

	    
	    @GetMapping("/latest-activities")
	    public ResponseEntity<List<FileData>> getLatestActivities() {
	        List<FileData> latestActivities = storageService.getLatestActivities();
	        return ResponseEntity.ok(latestActivities);
	    }


	    
	    
	    
	    
	    
	    
	

}
