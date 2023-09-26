package com.example.insurence.models;
import java.util.Date;

public class UserLoginValidation {
	private Long userId;
	private Date loginTimeFrom;
	private Date loginTimeTo;

	public UserLoginValidation() {
		// Default constructor
	}

	public UserLoginValidation(Long userId, Date loginTimeFrom, Date loginTimeTo) {
		this.userId = userId;
		this.loginTimeFrom = loginTimeFrom;
		this.loginTimeTo = loginTimeTo;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Date getLoginTimeFrom() {
		return loginTimeFrom;
	}

	public void setLoginTimeFrom(Date loginTimeFrom) {
		this.loginTimeFrom = loginTimeFrom;
	}

	public Date getLoginTimeTo() {
		return loginTimeTo;
	}

	public void setLoginTimeTo(Date loginTimeTo) {
		this.loginTimeTo = loginTimeTo;
	}
}