package com.example.insurence.repositories;

import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.example.insurence.models.AuthUtils;
import com.example.insurence.models.CustomerData;
import com.example.insurence.models.FamilyMedicalHistoryData;
import com.example.insurence.models.UserData;
import com.example.insurence.models.UserLoginValidation;

import jakarta.servlet.http.HttpSession;

@Repository
public class InsurenceRepository implements RepositoryInterface {

	private InsurenceDao insurenceDao;
	HttpSession session;

	@Autowired
	public InsurenceRepository(InsurenceDao insurenceDao, HttpSession session) {
		this.insurenceDao = insurenceDao;
		this.session = session;
	}

	public Long saveUserData(String userName, String password) {

		long id = insurenceDao.saveUserData(userName, password);
		return id;
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
		
		String username = "avengersbtrs@gmail.com";
		String password = "urpr twig ffeb uqlx";
		
		
		String host = "smtp.gmail.com";
		int port = 587;
		
		/*When you specify the SMTP server's address (hostname) as "smtp.gmail.com",
		 *  you are essentially telling your code to connect to Gmail's SMTP server for sending
		 *   emails. This is the server that will handle the transmission of your email message 
		 *   to its destination, and it's the location to which you send your email data for
		 *    processing and delivery.
		 */
		

		// Set properties
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		
		
		/*
		 * Purpose: Enabling SMTP authentication is essential for ensuring that only authorized users 
		 * can send 
		 *emails through the SMTP server. It helps prevent unauthorized access and misuse of the
		 * SMTP server.
		 */
		props.put("mail.smtp.starttls.enable", "true");
		
		/*
		 * Purpose: Enabling STARTTLS is crucial for secure email transmission. It ensures that the email 
		 * communication between your client and the SMTP server is encrypted, making 
		 * it more resistant to interception or eavesdropping.
		 */
		
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

	public boolean userChecking(String userName, String password, List<UserData> userDataList) {
		for (UserData userData : userDataList) {

			// checking username and password
			if (userName.equals(userData.getUserName()) && password.equals(userData.getUserPwd())) {
				System.out.println(userData.getUserPwd());
				System.out.println(userData.getUserName());

				session.setAttribute("userId", userData.getUserId());

				// generate randome key for security and store in session
				String key = AuthUtils.generateKey();
				session.setAttribute("key", key);

				Long userId = userData.getUserId();

				UserLoginValidation user = insurenceDao.getLoginTimeRange(userId);

				Date currentDate = new Date();
				System.out.println("checked key");

				// Check if the current date and time falls within the login time range
				// Format the current date to match the login date format
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String formattedCurrentDate = dateFormat.format(currentDate);

				// Get the login time range as strings
				String loginTimeFrom = dateFormat.format(user.getLoginTimeFrom());
				String loginTimeTo = dateFormat.format(user.getLoginTimeTo());

				System.out.println("current Date" + formattedCurrentDate);

				System.out.println("loginDate to Date" + loginTimeTo);

				// checking login time is expired or not
				if (formattedCurrentDate.compareTo(loginTimeFrom) >= 0
						&& formattedCurrentDate.compareTo(loginTimeTo) <= 0) {
					// Your code here

					System.out.println("iam inside time checking");

					System.out.println("Time checking");
					return true;

				}
			}

		}
		return false;
	} // No matching user found

	public String updateCustomersData(List<CustomerData> updatedCustomerData) {

		insurenceDao.updateCustomersData(updatedCustomerData);

		return "updated Succesfully";
	}

	public String UpdateFamilyMedicalHistory(List<FamilyMedicalHistoryData> updatedFamilyMedicalHistoryData) {

		insurenceDao.updateFamilyMedicalHistory(updatedFamilyMedicalHistoryData);

		return "updated succesfully";
	}

}