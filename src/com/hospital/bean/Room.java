package com.hospital.bean;

public class Room {
	
	//instance variables
	private int roomNo;
	private Patient patient;
	
	//constructors
	public Room(int roomNo) {
		super();
		this.roomNo = roomNo;
		this.patient = null;
	}
	public Room() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	//getters and setters
	public int getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	
	//to string
	@Override
	public String toString() {
		return "Room [roomNo=" + roomNo + ", patient=" + patient + "]\n";
	}
	
	

}
