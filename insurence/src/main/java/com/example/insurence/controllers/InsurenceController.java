package com.example.insurence.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
import com.example.insurence.models.OTPclass;
import com.example.insurence.models.UserData;
import com.example.insurence.repositories.InsurenceRepository;

import jakarta.servlet.http.HttpServletRequest;
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

	@GetMapping("/")
	public String login(Model model) {
		model.addAttribute("login", new LoginClass());
		return "LoginHtml";
	}

	@PostMapping(value = "/userLogin")
	public String adminlogin(HttpServletRequest request, @ModelAttribute("login") LoginClass lc, Model model) {
		System.out.println("came to this method  " + lc.toString());
		int check = insurenceRepository.checkCredentials(lc);
		if (check == 1) {
			System.out.println("i am in check=1");

			return "dashboard";
		}

		model.addAttribute("user_name", "lc.getUser_name()");
		model.addAttribute("password", "lc.getPassword()");
		model.addAttribute("errorMessage", "incorrect credentials");
		return "loginPage";
	}

	@GetMapping("/email")
	@ResponseBody
	public String email(@RequestParam("to") String to_mail) {
		String email = to_mail;
		httpSession.setAttribute("email", email);
		// storing generated otp
		int OTP = insurenceRepository.sendmail(to_mail);
		httpSession.setAttribute("OTP", OTP);

		return "Email Sent Successfully";

	}

	@PostMapping(value = "/validateOTP")
	public ModelAndView validateOTP(@RequestParam("otp") String otp, Model model) {
		model.addAttribute("to", "");
		int OTP = Integer.parseInt(otp);
		ModelAndView mav = new ModelAndView();
		int originalOtp = (Integer) httpSession.getAttribute("OTP");
		String email = (String) httpSession.getAttribute("email");
		// checking the otp sent by the user if true returning reset page else need to stay in the same page with error
		// msg
		if (originalOtp == OTP) {
			mav.setViewName("reset");
			mav.addObject("email", email);
			return mav;
		}
		mav.setViewName("forgotPasswordPage");
		mav.addObject("msg", "Please Enter Valid OTP");
		mav.addObject("email", email);
		return mav;
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
		return "loginPage";
	}

	@GetMapping("/forgotpassword")
	public String forgotpassword(Model model) {
		model.addAttribute("to", "");
		model.addAttribute("login", new OTPclass());
		model.addAttribute("enotp", "");
		model.addAttribute("otp", "");

		return "forgotPasswordPage";
	}

}
