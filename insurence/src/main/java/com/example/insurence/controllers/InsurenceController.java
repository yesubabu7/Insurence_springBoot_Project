package com.example.insurence.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
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
import com.example.insurence.models.UserData;
import com.example.insurence.repositories.InsurenceRepository;

import jakarta.servlet.http.HttpSession;

@RestController
public class InsurenceController {

	private final String uploadDir = "insurence/src/main/resources/static/file"; // Replace with your actual upload
																					// directory

	InsurenceRepository insurenceRepository;
	private HttpSession httpSession;

	@Autowired
	public InsurenceController(InsurenceRepository insurenceRepository, HttpSession httpSession) {

		this.insurenceRepository = insurenceRepository;
		this.httpSession = httpSession;
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
	public List<UserData> getAllUsers() {

		System.out.println("users");

		return insurenceRepository.getAllUsers();
	}

	@RequestMapping(value = "/FamilyMedicalData", method = RequestMethod.GET)
	public List<FamilyMedicalHistoryData> getFamilyMedicalData() {

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

	@Value("${pdfFilesPath}") // Replace with the actual path to your PDF files
	private String pdfFilesPath;

	@RequestMapping(value = "/list-pdf-files", method = RequestMethod.GET)
	public List<String> listPdfFiles(Model model) {
		// Use pdfFilesPath to construct the full path to your PDF files
		String fullPath = pdfFilesPath + "/"; // Add a slash to separate the base path from the filenames

		// Implement logic to get a list of PDF file names from your repository
		// For this example, we'll assume you have a method getPdfFileNames that returns a list of filenames
		List<String> pdfFileNames = insurenceRepository.getPdfFileNames();

		List<String> pdfFileUrls = new ArrayList<>();
		for (String fileName : pdfFileNames) {
			// Create the PDF file URL based on the full path and file name
			String pdfFileUrl = fullPath + fileName;
			pdfFileUrls.add(pdfFileUrl);
		}

		return pdfFileUrls; // This should be the name of your HTML template
	}

}