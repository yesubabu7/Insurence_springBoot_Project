package com.example.insurence.rowmappers;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.insurence.models.FileUploadData;

public class FileUploadDataRowMapper implements RowMapper<FileUploadData> {
    @Override
    public FileUploadData mapRow(ResultSet rs, int rowNum) throws SQLException {
        FileUploadData fileUploadData = new FileUploadData();
        fileUploadData.setCustomerId(rs.getLong("customerId"));
        fileUploadData.setUserId(rs.getLong("userId"));
        fileUploadData.setFullPath(rs.getString("fullPath"));
        fileUploadData.setFileName(rs.getString("fileName"));
        return fileUploadData;
    }
}