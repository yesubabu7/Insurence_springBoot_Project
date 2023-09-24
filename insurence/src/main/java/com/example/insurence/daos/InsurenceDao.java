package com.example.insurence.daos;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import javax.sql.DataSource;
import org.springframework.util.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.example.insurence.contracts.DaoInterface;
import com.example.insurence.models.CustomerData;
import com.example.insurence.models.FamilyMedicalHistoryData;
import com.example.insurence.models.UserData;
import com.example.insurence.rowmappers.*;


@Repository

public class InsurenceDao implements DaoInterface {
	
	JdbcTemplate jdbcTemplate;

	@Autowired
	public InsurenceDao(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);

	}

	
	@Override
	public void saveCustomerData(CustomerData customerData) {
	    String sql = "INSERT INTO Customers (cust_fname, cust_lname, cust_dob, cust_address, cust_gender, cust_cdate, cust_aadhar, cust_status, cust_luudate, cust_luuser) " +
	                 "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	    
	    
	    jdbcTemplate.update(
	        sql,
	        customerData.getCust_fname(),
	        customerData.getCust_lname(),
	        customerData.getCust_dob(),
	        customerData.getCust_address(),
	        String.valueOf(customerData.getCust_gender()),
	        customerData.getCust_cdate(),
	        customerData.getCust_aadhar(),
	        customerData.getCust_status(),
	        customerData.getCust_luudate(),
	        customerData.getCust_luuser()
	    );
	}


	 public void saveFamilyMedicalHistoryData(FamilyMedicalHistoryData history) {
	        String sql = "INSERT INTO FamilyMedicalHistory (mother_disease, grandmother_disease, father_disease, grandfather_disease, userid) " +
	                "VALUES (?, ?, ?, ?, ?)";

	        jdbcTemplate.update(
	            sql,
	            history.getMotherDisease(),
	            history.getGrandmotherDisease(),
	            history.getFatherDisease(),
	            history.getGrandfatherDisease(),
	            history.getUserid()
	        );
	    }
	 
	 
	public List<CustomerData> getAllCustomersFromDao() {
		
		return jdbcTemplate.query("select * from Customers", new CustomerDataRowMapper());

	}


	public List<UserData> getAllUsersFromDao() {
		// TODO Auto-generated method stub
		return jdbcTemplate.query("select * from users", new UsersDataRowMapper());
	}


	public List<FamilyMedicalHistoryData> getFamilyMedicalData() {
		// TODO Auto-generated method stub
		return jdbcTemplate.query("select * from FamilyMedicalHistor ", new FamilyMedicalHistoryDataRowMapper());

	}


	public String uploadFileToDao(MultipartFile file) {
	    String uploadDir = "insurence/src/main/resources/static/file"; // Replace with the actual path on your server

	    try {
	        // Get the original file name
	        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

	        // Create the target directory if it doesn't exist
	        Files.createDirectories(Paths.get(uploadDir));

	        // Create the target file path within the directory
	        Path targetLocation = Paths.get(uploadDir).resolve(fileName);

	        // Copy the file to the target location
	        Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

	        return fileName; // Return the stored file name
	    } catch (IOException ex) {
	        System.out.println(ex);
	        return null; // Return an error message or handle the exception as needed
	    }
	}

	
	
	
}