package yesu.models;

import java.sql.Date;

public class Package {

	int insp_id;
	String insp_title;
	String insp_status;
	Date insp_cdate;

	public Package() {

	}

	public int getInsp_id() {
		return insp_id;
	}

	public String getInsp_title() {
		return insp_title;
	}

	public String getInsp_status() {
		return insp_status;
	}

	public Date getInsp_cdate() {
		return insp_cdate;
	}

	public void setInsp_id(int insp_id) {
		this.insp_id = insp_id;
	}

	public void setInsp_title(String insp_title) {
		this.insp_title = insp_title;
	}

	public void setInsp_status(String insp_status) {
		this.insp_status = insp_status;
	}

	public void setInsp_cdate(Date insp_cdate) {
		this.insp_cdate = insp_cdate;
	}

}