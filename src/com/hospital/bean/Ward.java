package com.hospital.bean;

import java.util.ArrayList;
import java.util.List;

public class Ward {
	//instance variables
	private int wardNo;
	private List<Room> rooms;
	private int capacity;
	private String wardCode;
	
	//constructors
	public Ward(int wardNo, List<Room> rooms,int capacity,String wardCode) {
		super();
		this.wardNo = wardNo;
		this.rooms = new ArrayList<>();
		for(int i=1;i<=10;i++) {
			rooms.add(new Room(i));
		}
		this.capacity=2;
		this.wardCode=wardCode;
	}
	public Ward() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public boolean isVaccant(){
		for(Room room:rooms) {
			if(room.getPatient()==null) {
				return true;
			}
		}
		return false;
	}
	
	//getters and setters
	public int getWardNo() {
		return wardNo;
	}
	public void setWardNo(int wardNo) {
		this.wardNo = wardNo;
	}
	public List<Room> getRooms() {
		return rooms;
	}
	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}
	
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public String getWardCode() {
		return wardCode;
	}
	public void setWardCode(String wardCode) {
		this.wardCode = wardCode;
	}
	//to string 
	@Override
	public String toString() {
		return "Ward [wardNo=" + wardNo + ", rooms=" + rooms + ", capacity=" + capacity + ", wardCode=" + wardCode
				+ "]\n";
	}
	
	

}
