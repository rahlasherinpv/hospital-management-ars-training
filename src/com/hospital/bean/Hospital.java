package com.hospital.bean;

import java.util.List;

public class Hospital {
	//instance variables
	private String hospitalName;
	private List<Ward> wards;
	
	//constructors
	public Hospital() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Hospital(String hospitalName, List<Ward> wards) {
		super();
		this.hospitalName = hospitalName;
		this.wards = wards;
	}
	
	//getters and setters
	public String getHospitalName() {
		return hospitalName;
	}
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}
	public List<Ward> getWards() {
		return wards;
	}
	public void setWards(List<Ward> wards) {
		this.wards = wards;
	}
	
	//to string
	@Override
	public String toString() {
		return "Hospital [hospitalName=" + hospitalName + ", wards=\n" + wards + "]";
	}

}
