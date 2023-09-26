package com.example.insurence.controllers;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.insurence.models.CustomerData;
import com.example.insurence.models.FamilyMedicalHistoryData;
import com.example.insurence.models.LoginClass;
import com.example.insurence.models.UserData;
import com.example.insurence.repositories.InsurenceRepository;

import jakarta.servlet.http.HttpSession;

@RestController
public class InsurenceController {

	private final String uploadDir = "insurence/src/main/resources/static/file"; // Replace with your actual upload
																					// directory

	InsurenceRepository insurenceRepository;
	private HttpSession session;

	List<UserData> UserDataList;

	@Autowired
	public InsurenceController(InsurenceRepository insurenceRepository, HttpSession httpSession) {

		this.insurenceRepository = insurenceRepository;
		this.session = httpSession;
	}

	@PostMapping("/saveUserData")
	@ResponseBody
	public Long saveUserData(@RequestParam("username") String userName, @RequestParam("password") String password) {

		// System.out.println("Received user data: " + userData);

		return insurenceRepository.saveUserData(userName, password);

	}

	@PostMapping("/saveCustomerData")
	@ResponseBody
	public String saveCustomerData(@RequestBody CustomerData customerData) {

		customerData.setCust_status("Active");
		customerData.setCust_luudate(new Date());
		customerData.setCust_luuser(1);

		// Set a default value for cust_cdate (assuming it's a Date field)
		customerData.setCust_cdate(new Date()); // You can set it to the current date

		System.out.println("Received customer data: " + customerData);
		insurenceRepository.saveCustomerData(customerData);

		return "Customer data saved successfully";
	}

	@PostMapping("/saveFamilyMedicalHistory")
	public String saveFamilyMedicalHistory(@RequestBody FamilyMedicalHistoryData data) {

		insurenceRepository.saveFamilyMedicalHistory(data);
		return "succesfuly saved";

	}

	@RequestMapping(value = "/Customers", method = RequestMethod.GET)

	public List<CustomerData> getAllCustomers() {

		System.out.println("customers");

		List<CustomerData> list = insurenceRepository.getAllCustomers();
		return list;
	}

	@RequestMapping(value = "/UpdateCustomers", method = RequestMethod.POST)

	public String UpdateCustomers(@RequestBody List<CustomerData> updatedCustomerData) {

		String check = insurenceRepository.updateCustomersData(updatedCustomerData);

		return check;
	}

	@RequestMapping(value = "/UpdateFamilyMedicalHistory", method = RequestMethod.POST)

	public String UpdateFamilyMedicalHistory(
			@RequestBody List<FamilyMedicalHistoryData> UpdatedFamilyMedicalHistoryData) {

		String check = insurenceRepository.UpdateFamilyMedicalHistory(UpdatedFamilyMedicalHistoryData);

		return check;
	}

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public List<UserData> getAllUsers() {

		System.out.println("users");

		UserDataList = insurenceRepository.getAllUsers();

		return UserDataList;
	}

	@RequestMapping(value = "/UserLogin", method = RequestMethod.POST)

	public String userCredinitial(@RequestParam("username") String userName,
			@RequestParam("password") String password) {

		UserDataList = insurenceRepository.getAllUsers();
		boolean b = insurenceRepository.userChecking(userName, password, UserDataList);
		if (b) {
			return "login successful";
		}

		System.out.println("customers");

		return "login Failed";
	}

	@RequestMapping(value = "/FamilyMedicalData", method = RequestMethod.GET)
	public List<FamilyMedicalHistoryData> getFamilyMedicalData() {

		System.out.println("medical");

		return insurenceRepository.getFamilyMedicalData();

	}

	@PostMapping("/uploadDocument")
	public ResponseEntity<String> uploadDocument(@RequestParam("file") MultipartFile file,
			@RequestParam("customerId") Long id, @RequestParam("userId") Long userId) {
		try {
			// Validate and process the uploaded file
			if (file.isEmpty()) {
				return new ResponseEntity<>("File is empty", HttpStatus.BAD_REQUEST);
			}

			String fileName = insurenceRepository.uploadFile(file); // Implement this method
			session.setAttribute("custId", id);

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

	@GetMapping("/email")
	@ResponseBody
	public String email(@RequestParam("to") String to_mail) {
		String email = to_mail;
		session.setAttribute("email", email);
		// storing generated otp
		int OTP = insurenceRepository.sendmail(to_mail);
		System.out.println(to_mail + "email here");
		System.out.println(OTP + "otp here");

		LocalTime currentTime = LocalTime.now();
		session.setAttribute("time", currentTime.plusMinutes(5));
		session.setAttribute("OTP", OTP);

		return "Email Sent Successfully";

	}

	@PostMapping(value = "/validateOTP")
	public String validateOTP(@RequestParam("otp") String otp, Model model) {
		model.addAttribute("to", "");
		int OTP = Integer.parseInt(otp);
		ModelAndView mav = new ModelAndView();
		int originalOtp = (Integer) session.getAttribute("OTP");
		String email = (String) session.getAttribute("email");

		LocalTime time = (LocalTime) session.getAttribute("time");
		int comp = time.compareTo(LocalTime.now());
		// checking the otp sent by the user if true returning reset page else need to stay in the same page with error
		// msg
		if (originalOtp == OTP && comp > 0) {
			mav.setViewName("reset");
			mav.addObject("email", email);
			return "otp Match";
		}
		if (comp < 0)
			return "OTP expired, please try again..";
		else
			return "Invalid OTP, please try again..";

	}

	@PostMapping("/reset")
	public String reset(Model model, @RequestParam("email") String email, @RequestParam("pwd") String pwd,
			@RequestParam("cnfpwd") String cnfpwd) {
		System.out.println(email + " " + pwd + " " + cnfpwd);
		int x = insurenceRepository.resetpwd(email, pwd);
		if (x == 1)
			model.addAttribute("message", "password changed");
		else
			model.addAttribute("message", "error while password changing");
		model.addAttribute("login", new LoginClass());
		return "password Changed";
	}

}