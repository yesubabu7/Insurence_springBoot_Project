package yesu.rowMappers;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@ComponentScan("com.spring.boot.insurance.rowmappers")
public class InsuranceConfig {

	@Autowired
	Environment env;

	@Bean
	DataSource dataSource() {
		DriverManagerDataSource dmds = new DriverManagerDataSource();
		dmds.setDriverClassName("org.postgresql.Driver");
		dmds.setUrl("jdbc:postgresql://192.168.110.48:5432/plf_training");
		dmds.setUsername("plf_training_admin");
		dmds.setPassword("pff123");

		return dmds;
	}

}