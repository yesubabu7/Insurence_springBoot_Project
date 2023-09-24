package com.example.insurence.models;
public class FamilyMedicalHistoryData {
    private Long userid;
    private String motherDisease;
    private String grandmotherDisease;
    private String fatherDisease;
    private String grandfatherDisease;
    
    
	public Long getUserid() {
		return userid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	public String getMotherDisease() {
		return motherDisease;
	}
	public void setMotherDisease(String motherDisease) {
		this.motherDisease = motherDisease;
	}
	public String getGrandmotherDisease() {
		return grandmotherDisease;
	}
	public void setGrandmotherDisease(String grandmotherDisease) {
		this.grandmotherDisease = grandmotherDisease;
	}
	public String getFatherDisease() {
		return fatherDisease;
	}
	public void setFatherDisease(String fatherDisease) {
		this.fatherDisease = fatherDisease;
	}
	public String getGrandfatherDisease() {
		return grandfatherDisease;
	}
	public void setGrandfatherDisease(String grandfatherDisease) {
		this.grandfatherDisease = grandfatherDisease;
	}

    
}
