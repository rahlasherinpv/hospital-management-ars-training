package com.hospital.lib;

import java.util.Comparator;

import com.hospital.bean.Patient;

public class ComparatorIllness implements Comparator<Patient>{

	@Override
	public int compare(Patient patient1, Patient patient2) {
		
		return patient1.getIllnessCode().compareTo(patient2.getIllnessCode());
	}

	

}
