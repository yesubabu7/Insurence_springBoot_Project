package com.example.insurence.repositories;

import java.util.List;
import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.example.insurence.contracts.RepositoryInterface;
import com.example.insurence.daos.InsurenceDao;
import com.example.insurence.models.CustomerData;
import com.example.insurence.models.FamilyMedicalHistoryData;
import com.example.insurence.models.LoginClass;
import com.example.insurence.models.UserData;

@Repository
public class InsurenceRepository implements RepositoryInterface {

	private InsurenceDao insurenceDao;

	@Autowired
	public InsurenceRepository(InsurenceDao insurenceDao) {
		this.insurenceDao = insurenceDao;

	}

	public void saveCustomerData(CustomerData customerData) {

		insurenceDao.saveCustomerData(customerData);

	}

	public void saveFamilyMedicalHistory(FamilyMedicalHistoryData data) {
		insurenceDao.saveFamilyMedicalHistoryData(data);

	}

	public List<CustomerData> getAllCustomers() {

		return insurenceDao.getAllCustomersFromDao();

	}

	public List<UserData> getAllUsers() {

		return insurenceDao.getAllUsersFromDao();
	}

	public List<FamilyMedicalHistoryData> getFamilyMedicalData() {

		return insurenceDao.getFamilyMedicalData();
	}

	public String uploadFile(MultipartFile file) {

		return insurenceDao.uploadFileToDao(file);
	}

	public List<String> getPdfFileNames() {
		// TODO Auto-generated method stub
		return insurenceDao.getPdfFileNames();
	}

	public int sendmail(String to_mail) {
		String to = to_mail;
		String subject = "Password Reset";

		int OTP = generateOTP();
		String body = "The OTP for the Password Reset: " + OTP;
		sendEmail(to, subject, body);

		return OTP;
	}

	private static void sendEmail(String to, String subject, String body) {
		String host = "smtp.gmail.com";
		int port = 587;
		String username = "avengersbtrs@gmail.com";
		String password = "urpr twig ffeb uqlx";

		// Set properties
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);

		// Create session
		Session session = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			// Create message
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setSubject(subject);
			message.setText(body);
			Transport.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}

	private static int generateOTP() {
		Random random = new Random();
		int randomNumber = 100000 + random.nextInt(900000);

		return randomNumber;
	}

	public int resetpwd(String email, String pwd) {
		return insurenceDao.resetpwd(email, pwd);
	}

	public int checkCredentials(LoginClass lc) {

		return insurenceDao.checkCredentials(lc);
	}

	
	
	
	public boolean userChecking(Long userId, String userName, String password, List<UserData> userDataList) {
	    for (UserData userData : userDataList) {
	        if (userName.equals(userData.getUserName()) && password.equals(userData.getUserPwd()) && userData.getUserId().equals(userId)) {
	            return true; // Found a matching user
	        }
	    }
	    return false; // No matching user found
	}
	
	
	

	public String updateCustomersData(List<CustomerData> updatedCustomerData) {
		
		insurenceDao.updateCustomersData(updatedCustomerData);
		
		return "updated Succesfully";
	}
	
	
	

	public String UpdateFamilyMedicalHistory(List<FamilyMedicalHistoryData> updatedFamilyMedicalHistoryData) {
		
		insurenceDao.updateFamilyMedicalHistory(updatedFamilyMedicalHistoryData);

		return "";
	}


}