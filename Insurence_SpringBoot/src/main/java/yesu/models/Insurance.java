package yesu.models;

import java.sql.Date;

public class Insurance {
	int ins_id;
	int ins_customer_id;
	Date ins_cdate;
	double ins_sum_assured;
	int ins_insp_id;
	double ins_yrly_prem_amt;
	Date ins_st_date;

	public int getIns_id() {
		return ins_id;
	}

	public int getIns_customer_id() {
		return ins_customer_id;
	}

	public Date getIns_cdate() {
		return ins_cdate;
	}

	public double getIns_sum_assured() {
		return ins_sum_assured;
	}

	public int getIns_insp_id() {
		return ins_insp_id;
	}

	public double getIns_yrly_prem_amt() {
		return ins_yrly_prem_amt;
	}

	public Date getIns_st_date() {
		return ins_st_date;
	}

	public void setIns_id(int ins_id) {
		this.ins_id = ins_id;
	}

	public void setIns_customer_id(int ins_customer_id) {
		this.ins_customer_id = ins_customer_id;
	}

	public void setIns_cdate(Date ins_cdate) {
		this.ins_cdate = ins_cdate;
	}

	public void setIns_sum_assured(double ins_sum_assured) {
		this.ins_sum_assured = ins_sum_assured;
	}

	public void setIns_insp_id(int ins_insp_id) {
		this.ins_insp_id = ins_insp_id;
	}

	public void setIns_yrly_prem_amt(double ins_yrly_prem_amt) {
		this.ins_yrly_prem_amt = ins_yrly_prem_amt;
	}

	public void setIns_st_date(Date ins_st_date) {
		this.ins_st_date = ins_st_date;
	}

	public Insurance(int ins_id, int ins_customer_id, Date ins_cdate, double ins_sum_assured, int ins_insp_id,
			double ins_yrly_prem_amt, Date ins_st_date) {

		this.ins_id = ins_id;
		this.ins_customer_id = ins_customer_id;
		this.ins_cdate = ins_cdate;
		this.ins_sum_assured = ins_sum_assured;
		this.ins_insp_id = ins_insp_id;
		this.ins_yrly_prem_amt = ins_yrly_prem_amt;
		this.ins_st_date = ins_st_date;
	}

	public Insurance() {

	}

}