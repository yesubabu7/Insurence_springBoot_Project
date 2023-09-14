package yesu.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import yesu.models.Customers;
import yesu.repositery.ImpInsurance;

@Service
public class InsuranceService {

	ImpInsurance impInsurance;

	@Autowired
	public InsuranceService(ImpInsurance impInsurance) {
		this.impInsurance = impInsurance;
	}

	public List<Customers> getAllCusomers() {

		return impInsurance.getAllCustomers();
	}
}