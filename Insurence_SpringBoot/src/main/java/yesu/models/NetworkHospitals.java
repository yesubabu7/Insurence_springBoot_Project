package yesu.models;

import java.sql.Date;

public class NetworkHospitals {
	int hosp_id;
	String hosp_title;
	String hosp_location;
	String hosp_address;
	String hosp_mobile;
	String hosp_phone;
	String hosp_pincode;
	Date hosp_ludate;
	int hosp_luuser;

	public NetworkHospitals(int hosp_id, String hosp_title, String hosp_location, String hosp_address,
			String hosp_mobile, String hosp_phone, String hosp_pincode, Date hosp_ludate, int hosp_luuser) {

		this.hosp_id = hosp_id;
		this.hosp_title = hosp_title;
		this.hosp_location = hosp_location;
		this.hosp_address = hosp_address;
		this.hosp_mobile = hosp_mobile;
		this.hosp_phone = hosp_phone;
		this.hosp_pincode = hosp_pincode;
		this.hosp_ludate = hosp_ludate;
		this.hosp_luuser = hosp_luuser;
	}

	public NetworkHospitals() {

	}

	public int getHosp_id() {
		return hosp_id;
	}

	public String getHosp_title() {
		return hosp_title;
	}

	public String getHosp_location() {
		return hosp_location;
	}

	public String getHosp_address() {
		return hosp_address;
	}

	public String getHosp_mobile() {
		return hosp_mobile;
	}

	public String getHosp_phone() {
		return hosp_phone;
	}

	public String getHosp_pincode() {
		return hosp_pincode;
	}

	public Date getHosp_ludate() {
		return hosp_ludate;
	}

	public int getHosp_luuser() {
		return hosp_luuser;
	}

	public void setHosp_id(int hosp_id) {
		this.hosp_id = hosp_id;
	}

	public void setHosp_title(String hosp_title) {
		this.hosp_title = hosp_title;
	}

	public void setHosp_location(String hosp_location) {
		this.hosp_location = hosp_location;
	}

	public void setHosp_address(String hosp_address) {
		this.hosp_address = hosp_address;
	}

	public void setHosp_mobile(String hosp_mobile) {
		this.hosp_mobile = hosp_mobile;
	}

	public void setHosp_phone(String hosp_phone) {
		this.hosp_phone = hosp_phone;
	}

	public void setHosp_pincode(String hosp_pincode) {
		this.hosp_pincode = hosp_pincode;
	}

	public void setHosp_ludate(Date hosp_ludate) {
		this.hosp_ludate = hosp_ludate;
	}

	public void setHosp_luuser(int hosp_luuser) {
		this.hosp_luuser = hosp_luuser;
	}

}