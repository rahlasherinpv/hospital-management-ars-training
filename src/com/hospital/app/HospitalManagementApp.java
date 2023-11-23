package com.hospital.app;

import java.io.IOException;

import com.hospital.lib.HospitalManagementService;

public class HospitalManagementApp {

	public static void main(String[] args) throws IOException {
		HospitalManagementService hospitalManagementService=new HospitalManagementService();
		hospitalManagementService.menuDriven();

	}

}
