package com.hospital.bean;

public class Patient {
	
	//instance variables
	private int patientId;
	private String patientName;
	private int age;
	private String illnessCode;
	
	//constructors
	public Patient() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Patient(int patientId, String patientName, int age, String illnessCode) {
		super();
		this.patientId = patientId;
		this.patientName = patientName;
		this.age = age;
		this.illnessCode = illnessCode;
	}
	
	//getters and setters
	public int getPatientId() {
		return patientId;
	}
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public int getAge() {
	
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getIllnessCode() {
		return illnessCode;
	}
	public void setIllnessCode(String illnessCode) {
		this.illnessCode = illnessCode;
	}
	
	//to string
	@Override
	public String toString() {
		return "Patient [patientId=" + patientId + ", patientName=" + patientName + ", age=" + age + ", illnessCode="
				+ illnessCode + "]";
	}
	
	

}
