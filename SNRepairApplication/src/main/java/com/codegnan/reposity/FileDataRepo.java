package com.codegnan.reposity;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.codegnan.entity.FileData;

public interface FileDataRepo extends JpaRepository<FileData, Long> {
	
	
	Optional<FileData> findByBeforeImageName(String beforeImageName);
    Optional<FileData> findByAfterImageName(String afterImageName);
    
    
    @Query("SELECT f FROM FileData f ORDER BY f.id DESC")
    List<FileData> findLatestActivities();

}
