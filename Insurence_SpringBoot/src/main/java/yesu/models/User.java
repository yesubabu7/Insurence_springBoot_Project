package yesu.models;

import java.sql.Date;

public class User {
	int user_id;
	String user_name;
	Date user_cdate;
	String user_password;
	String user_type;
	String user_status;

	public User() {

	}

	public int getUser_id() {
		return user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public Date getUser_cdate() {
		return user_cdate;
	}

	public String getUser_password() {
		return user_password;
	}

	public String getUser_type() {
		return user_type;
	}

	public String getUser_status() {
		return user_status;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public void setUser_cdate(Date user_cdate) {
		this.user_cdate = user_cdate;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}

	public void setUser_status(String user_status) {
		this.user_status = user_status;
	}

}