package ca.codeward.model;

import ca.codeward.model.ResidenceAddress;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value="individual")
public class Individual {
	private int key;
	private String lastName;
	private String firstName;
	private String titlePrefix;
	private String birthDt;
	private char genderCd;
	private ResidenceAddress residenceAddress;
	
	// GETTERS AND SETTERS
	public int getKey() {
		return key;
	}
	public void setKey(int key) {
		this.key = key;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getTitlePrefix() {
		return titlePrefix;
	}
	public void setTitlePrefix(String titlePrefix) {
		this.titlePrefix = titlePrefix;
	}
	public String getBirthDt() {
		return birthDt;
	}
	public void setBirthDt(String birthDt) {
		this.birthDt = birthDt;
	}
	public char getGenderCd() {
		return genderCd;
	}
	public void setGenderCd(char genderCd) {
		this.genderCd = genderCd;
	}
	public ResidenceAddress getResidenceAddress() {
		return residenceAddress;
	}
	public void setResidenceAddress(ResidenceAddress residenceAddress) {
		this.residenceAddress = residenceAddress;
	}
}
