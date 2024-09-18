package com.codegnan.entity;

import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;



@Entity
@Table(name="Image_Data")

public class FileData {
    
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String projectTitle;
    private String description;
    
    private String beforeImageName;
    private String afterImageName;
    
    private String beforeImagePath;
    private String afterImagePath;

  
    public FileData() {
    }


    private FileData(Builder builder) {
        this.projectTitle = builder.projectTitle;
        this.description = builder.description;
        this.beforeImageName = builder.beforeImageName;
        this.afterImageName = builder.afterImageName;
        this.beforeImagePath = builder.beforeImagePath;
        this.afterImagePath = builder.afterImagePath;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectTitle() {
        return projectTitle;
    }

    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBeforeImageName() {
        return beforeImageName;
    }

    public void setBeforeImageName(String beforeImageName) {
        this.beforeImageName = beforeImageName;
    }

    public String getAfterImageName() {
        return afterImageName;
    }

    public void setAfterImageName(String afterImageName) {
        this.afterImageName = afterImageName;
    }

    public String getBeforeImagePath() {
        return beforeImagePath;
    }

    public void setBeforeImagePath(String beforeImagePath) {
        this.beforeImagePath = beforeImagePath;
    }

    public String getAfterImagePath() {
        return afterImagePath;
    }

    public void setAfterImagePath(String afterImagePath) {
        this.afterImagePath = afterImagePath;
    }

 
    public static class Builder {
        private String projectTitle;
        private String description;
        private String beforeImageName;
        private String afterImageName;
        private String beforeImagePath;
        private String afterImagePath;

        public Builder projectTitle(String projectTitle) {
            this.projectTitle = projectTitle;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder beforeImageName(String beforeImageName) {
            this.beforeImageName = beforeImageName;
            return this;
        }

        public Builder afterImageName(String afterImageName) {
            this.afterImageName = afterImageName;
            return this;
        }

        public Builder beforeImagePath(String beforeImagePath) {
            this.beforeImagePath = beforeImagePath;
            return this;
        }

        public Builder afterImagePath(String afterImagePath) {
            this.afterImagePath = afterImagePath;
            return this;
        }

        public FileData build() {
            return new FileData(this);
        }
    }
		
		
	    
	    

}
