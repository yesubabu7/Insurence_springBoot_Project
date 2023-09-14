package yesu.models;

import java.sql.Date;

public class Customers {
	int cust_id;
	String cust_firstname;
	String cust_lastname;
	Date cust_dob;
	String cust_address;
	String cust_gender;
	Date cust_cdate;
	String cust_aadhar;
	String cust_status;
	Date cust_ludate;
	int cust_luuser;

	public Customers() {
		super();
	}

	public int getCust_id() {
		return cust_id;
	}

	public String getCust_firstname() {
		return cust_firstname;
	}

	public String getCust_lastname() {
		return cust_lastname;
	}

	public Date getCust_dob() {
		return cust_dob;
	}

	public String getCust_address() {
		return cust_address;
	}

	public String getCust_gender() {
		return cust_gender;
	}

	public Date getCust_cdate() {
		return cust_cdate;
	}

	public String getCust_aadhar() {
		return cust_aadhar;
	}

	public String getCust_status() {
		return cust_status;
	}

	public Date getCust_ludate() {
		return cust_ludate;
	}

	public int getCust_luuser() {
		return cust_luuser;
	}

	public void setCust_id(int cust_id) {
		this.cust_id = cust_id;
	}

	public void setCust_firstname(String cust_firstname) {
		this.cust_firstname = cust_firstname;
	}

	public void setCust_lastname(String cust_lastname) {
		this.cust_lastname = cust_lastname;
	}

	public void setCust_dob(Date cust_dob) {
		this.cust_dob = cust_dob;
	}

	public void setCust_address(String cust_address) {
		this.cust_address = cust_address;
	}

	public void setCust_gender(String cust_gender) {
		this.cust_gender = cust_gender;
	}

	public void setCust_cdate(Date cust_cdate) {
		this.cust_cdate = cust_cdate;
	}

	public void setCust_aadhar(String cust_aadhar) {
		this.cust_aadhar = cust_aadhar;
	}

	public void setCust_status(String cust_status) {
		this.cust_status = cust_status;
	}

	public void setCust_ludate(Date cust_ludate) {
		this.cust_ludate = cust_ludate;
	}

	public void setCust_luuser(int cust_luuser) {
		this.cust_luuser = cust_luuser;
	}

}