package com.example.insurence.models;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FileUploadData {
    private Long customerId;
    private Long userId;
    private String fullPath;
    private String fileName;

    // Constructors, getters, and setters

    // Default constructor
    public FileUploadData() {
    }

    public FileUploadData(Long customerId, Long userId, String fullPath, String fileName) {
        this.customerId = customerId;
        this.userId = userId;
        this.fullPath = fullPath;
        this.fileName = fileName;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFullPath() {
        return fullPath;
    }

    public void setFullPath(String fullPath) {
        this.fullPath = fullPath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}