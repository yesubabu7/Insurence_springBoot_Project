package yesu.models;

public class InsurancePackage {

	int insp_id;
	String insp_title;
	String insp_desc;
	String insp_status;
	double insp_range_start;
	double insp_range_end;
	int insp_agelimit_start;
	int insp_agelimit_end;

	public int getInsp_id() {
		return insp_id;
	}

	public void setInsp_id(int insp_id) {
		this.insp_id = insp_id;
	}

	public String getInsp_title() {
		return insp_title;
	}

	public String getInsp_desc() {
		return insp_desc;
	}

	public String getInsp_status() {
		return insp_status;
	}

	public double getInsp_range_start() {
		return insp_range_start;
	}

	public double getInsp_range_end() {
		return insp_range_end;
	}

	public int getInsp_agelimit_start() {
		return insp_agelimit_start;
	}

	public int getInsp_agelimit_end() {
		return insp_agelimit_end;
	}

	public void setInsp_title(String insp_title) {
		this.insp_title = insp_title;
	}

	public void setInsp_desc(String insp_desc) {
		this.insp_desc = insp_desc;
	}

	public void setInsp_status(String insp_status) {
		this.insp_status = insp_status;
	}

	public void setInsp_range_start(double insp_range_start) {
		this.insp_range_start = insp_range_start;
	}

	public void setInsp_range_end(double insp_range_end) {
		this.insp_range_end = insp_range_end;
	}

	public void setInsp_agelimit_start(int insp_agelimit_start) {
		this.insp_agelimit_start = insp_agelimit_start;
	}

	public void setInsp_agelimit_end(int insp_agelimit_end) {
		this.insp_agelimit_end = insp_agelimit_end;
	}

	public InsurancePackage() {

	}

}