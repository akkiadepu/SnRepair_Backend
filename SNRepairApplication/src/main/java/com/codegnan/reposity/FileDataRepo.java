package com.codegnan.reposity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codegnan.entity.FileData;

public interface FileDataRepo extends JpaRepository<FileData, Long> {
	
	
	Optional<FileData> findByBeforeImageName(String beforeImageName);
    Optional<FileData> findByAfterImageName(String afterImageName);

}
