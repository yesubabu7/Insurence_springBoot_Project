
package com.example.insurence.models;
import java.util.Date;

public class CustomerData {
    private Long cust_id;
    private String cust_fname;
    private String cust_lname;
    private Date cust_dob;
    private String cust_address;
    private char cust_gender;
    private Date cust_cdate;
    private long cust_aadhar;
    private String cust_status;
    private Date cust_luudate;
    private int cust_luuser;

    // Constructors, getters, and setters

    public CustomerData() {
        // Default constructor
    }

    public CustomerData(Long cust_id, String cust_fname, String cust_lname, Date cust_dob,
                    String cust_address, char cust_gender, Date cust_cdate,
                    long cust_aadhar, String cust_status, Date cust_luudate, int cust_luuser) {
        this.cust_id = cust_id;
        this.cust_fname = cust_fname;
        this.cust_lname = cust_lname;
        this.cust_dob = cust_dob;
        this.cust_address = cust_address;
        this.cust_gender = cust_gender;
        this.cust_cdate = cust_cdate;
        this.cust_aadhar = cust_aadhar;
        this.cust_status = cust_status;
        this.cust_luudate = cust_luudate;
        this.cust_luuser = cust_luuser;
    }

    public Long getCust_id() {
        return cust_id;
    }

    public void setCust_id(Long cust_id) {
        this.cust_id = cust_id;
    }

    public String getCust_fname() {
        return cust_fname;
    }

    public void setCust_fname(String cust_fname) {
        this.cust_fname = cust_fname;
    }

    public String getCust_lname() {
        return cust_lname;
    }

    public void setCust_lname(String cust_lname) {
        this.cust_lname = cust_lname;
    }

    public Date getCust_dob() {
        return cust_dob;
    }

    public void setCust_dob(Date cust_dob) {
        this.cust_dob = cust_dob;
    }

    public String getCust_address() {
        return cust_address;
    }

    public void setCust_address(String cust_address) {
        this.cust_address = cust_address;
    }

    public char getCust_gender() {
        return cust_gender;
    }

    public void setCust_gender(char cust_gender) {
        this.cust_gender = cust_gender;
    }

    public Date getCust_cdate() {
        return cust_cdate;
    }

    public void setCust_cdate(Date cust_cdate) {
        this.cust_cdate = cust_cdate;
    }

    public long getCust_aadhar() {
        return cust_aadhar;
    }

    public void setCust_aadhar(long cust_aadhar) {
        this.cust_aadhar = cust_aadhar;
    }

    public String getCust_status() {
        return cust_status;
    }

    public void setCust_status(String cust_status) {
        this.cust_status = cust_status;
    }

    public Date getCust_luudate() {
        return cust_luudate;
    }

    public void setCust_luudate(Date cust_luudate) {
        this.cust_luudate = cust_luudate;
    }

    public int getCust_luuser() {
        return cust_luuser;
    }

    public void setCust_luuser(int cust_luuser) {
        this.cust_luuser = cust_luuser;
    }
}
