package com.example.insurence.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.insurence.models.LoginClass;
import com.example.insurence.models.OTPclass;
import com.example.insurence.repositories.InsurenceRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class CredintialController {

	InsurenceRepository insurenceRepository;
	private HttpSession httpSession;

	@Autowired
	public CredintialController(InsurenceRepository insurenceRepository, HttpSession httpSession) {

		this.insurenceRepository = insurenceRepository;
		this.httpSession = httpSession;
	}

	@GetMapping("/")
	public String login(Model model) {
		model.addAttribute("login", new LoginClass());
		return "LoginHtml";
	}

	@PostMapping("/userLogin")
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
		// checking the otp sent by the user if true returning reset page else need to stay in the same page with
		// error
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
