package com.codegnan.service;

import java.io.IOException
;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.codegnan.entity.FileData;
import com.codegnan.reposity.FileDataRepo;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;



@Service
public class StorageService {
	
	@Autowired
	private FileDataRepo fileDataRepo;
	private static final Logger logger = LoggerFactory.getLogger(StorageService.class);
	

	private final String FOLDER_PATH = "E:/snrepair-app/src/ImageDic/";

 
    public String addActivity(String projectTitle, String description, MultipartFile beforeImage, MultipartFile afterImage) throws IOException {
    	logger.debug("the add image and projecttitle and desc and beforeImage and afterName to path ");
        String beforeImagePath = FOLDER_PATH + beforeImage.getOriginalFilename();
        String afterImagePath = FOLDER_PATH + afterImage.getOriginalFilename();

        
        beforeImage.transferTo(new File(beforeImagePath));
        afterImage.transferTo(new File(afterImagePath));

        FileData projectActivity = new FileData.Builder()
            .projectTitle(projectTitle)
            .description(description)
            .beforeImageName(beforeImage.getOriginalFilename())
            .afterImageName(afterImage.getOriginalFilename())
            .beforeImagePath(beforeImagePath)
            .afterImagePath(afterImagePath)
            .build();

        logger.debug("saveing the details"+projectActivity);
        fileDataRepo.save(projectActivity);
        if (projectActivity != null) {
            return "file uploaded successfully : " + projectActivity;
        }
        return null;
        
    }


    
    
    public Map<String, byte[]> downloadBeforeAndAfterImages(String fileName) throws IOException {
        Optional<FileData> fileDataOpt = fileDataRepo.findByBeforeImageName(fileName);

        if (fileDataOpt.isPresent()) {
            FileData fileData = fileDataOpt.get();
            String beforeImagePath = fileData.getBeforeImagePath();
            String afterImagePath = fileData.getAfterImagePath();

            File beforeImageFile = new File(beforeImagePath);
            File afterImageFile = new File(afterImagePath);

            Map<String, byte[]> imageMap = new HashMap<>();
            if (beforeImageFile.exists()) {
                imageMap.put("beforeImage", Files.readAllBytes(beforeImageFile.toPath()));
            } else {
                throw new FileNotFoundException("Before Image not found at path: " + beforeImagePath);
            }

            if (afterImageFile.exists()) {
                imageMap.put("afterImage", Files.readAllBytes(afterImageFile.toPath()));
            } else {
                throw new FileNotFoundException("After Image not found at path: " + afterImagePath);
            }

            return imageMap;
        } else {
            throw new FileNotFoundException("File data not found for fileName: " + fileName);
        }
    }

    
    
    public List<FileData> getAllProjectActivities() {
        return fileDataRepo.findAll();
    }
	
}
