package com.example.insurence.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.insurence.models.CustomerData;
import com.example.insurence.models.FamilyMedicalHistoryData;
import com.example.insurence.models.*;
import com.example.insurence.repositories.InsurenceRepository;


@RestController
public class InsurenceController {
	
	InsurenceRepository insurenceRepository;

	@Autowired
	public InsurenceController(InsurenceRepository insurenceRepository) {
		
		this.insurenceRepository=insurenceRepository;
	}

	
	@PostMapping("/saveCustomerData")
    @ResponseBody
    public String saveCustomerData(@RequestBody CustomerData customerData) {
        
        System.out.println("Received customer data: " + customerData);
        
        insurenceRepository.saveCustomerData(customerData);
        
        return "Customer data saved successfully";
    }
	
	
	@PostMapping("/saveFamilyMedicalHistory")
    public ResponseEntity<String> saveFamilyMedicalHistory(@RequestBody FamilyMedicalHistoryData data) {
        
            
            insurenceRepository.saveFamilyMedicalHistory(data);
			return null;

         
    }
	
	@RequestMapping(value = "/Customers", method = RequestMethod.GET)
	
	public List<CustomerData> getAllCustomers() {
		
		System.out.println("customers");

		
		return insurenceRepository.getAllCustomers();
	}
	
	
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public List<UserData> getAllUsers()	{
		
		System.out.println("users");

		
		return insurenceRepository.getAllUsers();
	}
	
	@RequestMapping(value = "/FamilyMedicalData", method = RequestMethod.GET)
	public List<FamilyMedicalHistoryData> getFamilyMedicalData()	{
		
		System.out.println("medical");

		
		return insurenceRepository.getFamilyMedicalData();
	}
	
	@PostMapping("/uploadDocument")
    public ResponseEntity<String> uploadDocument(@RequestParam("file") MultipartFile file) {
        try {
            // Validate and process the uploaded file
            if (file.isEmpty()) {
                return new ResponseEntity<>("File is empty", HttpStatus.BAD_REQUEST);
            }

            String fileName = insurenceRepository.uploadFile(file); // Implement this method

            // You can return a success message or the file name
            return new ResponseEntity<>("File uploaded successfully. File name: " + fileName, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error uploading file: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

	
	

	 

	
	
}