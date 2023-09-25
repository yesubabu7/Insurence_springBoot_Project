package com.example.insurence.daos;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.insurence.contracts.DaoInterface;
import com.example.insurence.models.CustomerData;
import com.example.insurence.models.FamilyMedicalHistoryData;
import com.example.insurence.models.LoginClass;
import com.example.insurence.models.UserData;
import com.example.insurence.rowmappers.CustomerDataRowMapper;
import com.example.insurence.rowmappers.FamilyMedicalHistoryDataRowMapper;
import com.example.insurence.rowmappers.UsersDataRowMapper;

import jakarta.servlet.http.HttpSession;

@Repository

public class InsurenceDao implements DaoInterface {

	JdbcTemplate jdbcTemplate;
	HttpSession session;

	@Autowired
	public InsurenceDao(DataSource datasource,HttpSession session) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
		this.session=session;

	}

	@Override
	public void saveCustomerData(CustomerData customerData) {
		String sql = "INSERT INTO Customers (cust_fname, cust_lname, cust_dob, cust_address, cust_gender, cust_cdate, cust_aadhar, cust_status, cust_luudate, cust_luuser) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		jdbcTemplate.update(sql, customerData.getCust_fname(), customerData.getCust_lname(), customerData.getCust_dob(),
				customerData.getCust_address(), String.valueOf(customerData.getCust_gender()),
				customerData.getCust_cdate(), customerData.getCust_aadhar(), customerData.getCust_status(),
				customerData.getCust_luudate(), customerData.getCust_luuser());
	}

	public void saveFamilyMedicalHistoryData(FamilyMedicalHistoryData history) {
		String sql = "INSERT INTO FamilyMedicalHistory (mother_disease, grandmother_disease, father_disease, grandfather_disease, userid) "
				+ "VALUES (?, ?, ?, ?, ?)";

		jdbcTemplate.update(sql, history.getMotherDisease(), history.getGrandmotherDisease(),
				history.getFatherDisease(), history.getGrandfatherDisease(), history.getUserid());
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
		String uploadDir = "src/main/resources/static/file"; // Replace with the actual path on your server

		try {
			// Get the original file name
			String fileName = StringUtils.cleanPath(file.getOriginalFilename());

			// Create the target directory if it doesn't exist
			Files.createDirectories(Paths.get(uploadDir));

			// Create the target file path within the directory
			Path targetLocation = Paths.get(uploadDir).resolve(fileName);

			// Copy the file to the target location
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
			
	        String fullPath = targetLocation.toAbsolutePath().toString();
	        Long userId=(Long) session.getAttribute("userId");
	        
	        Long customerId=(Long) session.getAttribute("customerId");


	        uploadFileToDatabase(userId,customerId,fullPath,fileName);
			return fileName; // Retur the stored file name
		} catch (IOException ex) {
			System.out.println(ex);
			return null; // Return an error message or handle the exception as needed
		}
	}
	
	
	
	

	public void uploadFileToDatabase(Long userId, Long customerId, String fullPath, String fileName) {
	    String sql = "INSERT INTO fileUploadData (userId, customerId, fullPath, fileName) VALUES (?, ?, ?, ?)";

	    jdbcTemplate.update(sql, userId, customerId, fullPath, fileName);
	}


	public List<String> getPdfFileNames() {
		String uploadDir = "src/main/resources/static/file"; // Replace with the actual path on your server

		List<String> pdfFileNames = new ArrayList<>();
		File directory = new File(uploadDir);

		if (directory.exists() && directory.isDirectory()) {
			File[] files = directory.listFiles();

			if (files != null) {
				for (File file : files) {
					if (file.isFile() && file.getName().toLowerCase().endsWith(".png")) {
						pdfFileNames.add(file.getName());
					}
				}
			}
		}
		System.out.println(pdfFileNames);
		return pdfFileNames;
	}

	public int resetpwd(String email, String pwd) {
		return jdbcTemplate.update("update checktable set password='" + pwd + "' where username='" + email + "'");
	}

	public int checkCredentials(LoginClass lc) {

		String sql = "SELECT COUNT(*) FROM checktables WHERE username = '" + lc.getUser_name() + "' and password='"
				+ lc.getPassword() + "'";
		System.out.println(sql);
		// Execute the SQL query and return the count
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

	public void updateCustomersData(List<CustomerData> updatedCustomerData) {
        for (CustomerData customer : updatedCustomerData) {
            // Step 1: Delete existing record with the same cust_id
            String deleteSql = "DELETE FROM Customers WHERE cust_id = ?";
            jdbcTemplate.update(deleteSql, customer.getCust_id());

            // Step 2: Insert the updated customer data
            String insertSql = "INSERT INTO Customers (cust_id, cust_fname, cust_lname, cust_dob, " +
                    "cust_address, cust_gender, cust_cdate, cust_aadhar, cust_status, " +
                    "cust_luudate, cust_luuser) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            jdbcTemplate.update(insertSql,
                    customer.getCust_id(),
                    customer.getCust_fname(),
                    customer.getCust_lname(),
                    customer.getCust_dob(),
                    customer.getCust_address(),
                    customer.getCust_gender(),
                    customer.getCust_cdate(),
                    customer.getCust_aadhar(),
                    customer.getCust_status(),
                    customer.getCust_luudate(),
                    customer.getCust_luuser());
        }
    }

	 public void updateFamilyMedicalHistory(List<FamilyMedicalHistoryData> updatedFamilyMedicalHistoryData) {
	        // Step 1: Delete all existing records for the given userid
	        Long userid = updatedFamilyMedicalHistoryData.get(0).getUserid(); // Assuming the list is not empty
	        String deleteSql = "DELETE FROM FamilyMedicalHistory WHERE userid = ?";
	        jdbcTemplate.update(deleteSql, userid);

	        // Step 2: Insert new data into the FamilyMedicalHistory table
	        for (FamilyMedicalHistoryData data : updatedFamilyMedicalHistoryData) {
	            String insertSql = "INSERT INTO FamilyMedicalHistory (userid, mother_disease, " +
	                    "grandmother_disease, father_disease, grandfather_disease) VALUES (?, ?, ?, ?, ?)";

	            jdbcTemplate.update(insertSql,
	                    data.getUserid(),
	                    data.getMotherDisease(),
	                    data.getGrandmotherDisease(),
	                    data.getFatherDisease(),
	                    data.getGrandfatherDisease());
	        }
	    }

}