package com.example.insurence.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.example.insurence.contracts.RepositoryInterface;
import com.example.insurence.daos.InsurenceDao;
import com.example.insurence.models.CustomerData;
import com.example.insurence.models.FamilyMedicalHistoryData;
import com.example.insurence.models.UserData;

@Repository
public class InsurenceRepository implements RepositoryInterface {
	
	private InsurenceDao insurenceDao;
	
	
	@Autowired
	public InsurenceRepository(InsurenceDao insurenceDao) {
		this.insurenceDao=insurenceDao;
		
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
		
		
		return  insurenceDao.uploadFileToDao(file);
	}
	
	

}
