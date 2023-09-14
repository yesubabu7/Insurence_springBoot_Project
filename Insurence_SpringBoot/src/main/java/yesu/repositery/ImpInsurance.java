package yesu.repositery;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import yesu.contracts.InsuranceContractor;
import yesu.models.Customers;
import yesu.rowMappers.CustomerRowMapper;

@Repository
public class ImpInsurance implements InsuranceContractor {

	JdbcTemplate jdbc;

	@Autowired
	public ImpInsurance(DataSource ds) {
		this.jdbc = new JdbcTemplate(ds);

	}

	@Override
	public List<Customers> getAllCustomers() {

		return jdbc.query("select * from ins_customers", new CustomerRowMapper());

	}

}