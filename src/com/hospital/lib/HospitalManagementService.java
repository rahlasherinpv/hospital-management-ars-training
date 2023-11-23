package com.hospital.lib;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.hospital.bean.Hospital;
import com.hospital.bean.Patient;
import com.hospital.bean.Room;
import com.hospital.bean.Ward;

public class HospitalManagementService {
	Hospital hospital;

	public HospitalManagementService() {
		super();
		hospital = new Hospital();
		List<Room> rooms1 = new ArrayList<Room>();
		List<Room> rooms2 = new ArrayList<Room>();
		List<Room> rooms3 = new ArrayList<Room>();
		List<Room> rooms4 = new ArrayList<Room>();
		List<Room> rooms5 = new ArrayList<Room>();

		List<Ward> wards = new ArrayList<Ward>();

		Ward ward1 = new Ward(1, rooms1, 10, "CVD");
		Ward ward2 = new Ward(2, rooms2, 10, "CVD");
		Ward ward3 = new Ward(3, rooms3, 10, "CHILD");
		Ward ward4 = new Ward(4, rooms4, 10, "PW");
		Ward ward5 = new Ward(5, rooms5, 10, "OT");

		wards.add(ward1);
		wards.add(ward2);
		wards.add(ward3);
		wards.add(ward4);
		wards.add(ward5);

		hospital.setWards(wards);
		hospital.setHospitalName("ROYAL HEALTH CARE");
		try {
			loadHospitalDetails();
		} catch (FileNotFoundException e) {
			
		}
	}

	Scanner input = new Scanner(System.in);
	String choice;
	//int Id=0;

	public void menuDriven() throws IOException {

		do {
			System.out.println("\n\tWELCOME TO ROYAL HEALTH CARE");
			System.out.println("===========================================================");
			System.out.println("\n\t1.Admit Patient " + "\n\t2.Discharge Patient " + "\n\t3.List of vacant rooms"
					+ "\n\t4.List of admitted patients" + "\n\t5.Search patients with illness Code" + "\n\t0.exit");
			System.out.println("\nEnter Choice:");

			choice =input.nextLine();
			switch (choice) {
			case "1":
				admitPatient();
				break;
			case "2":
				dischargepatient();
				break;
			case "3":
				listvaccantRooms();
				break;
			case "4":
				listAdmittedpatient();
				break;
			case "5":
				searchPatients();
				break;
			case "0":
				System.exit(0);

			default:
				System.out.println("Enter a valid input");
			}
		} while (true);

	}
//search patients
	private void searchPatients() {
		System.out.println("\nEnter the illness code:");
		String illnessCode = input.nextLine();
		for (Ward w : hospital.getWards()) {
			if (w.getWardCode().equalsIgnoreCase(illnessCode)) {
				for (Room r : w.getRooms()) {
					System.out.println("name: " + r.getPatient().getPatientName() + " Age: " + r.getPatient().getAge()
							+ " illness code: " + r.getPatient().getIllnessCode());

				}
			}
		}

	}
//list of admitted patients
	private void listAdmittedpatient() {
		System.out.println("\n List of all Patients Admitted\n");
		List<Patient> patients=new ArrayList<Patient>();
		for (Ward w : hospital.getWards()) {
			for (Room r : w.getRooms()) {
				patients.add(r.getPatient());
				
			}
		}
		//sorting 
		ComparatorIllness comparatorIllness=new ComparatorIllness();
		patients.sort(comparatorIllness);
		for (Patient patient : patients) {
			System.out.println("name: " + patient.getPatientName() + " Age: " +patient.getAge()
					+ " illness code: " + patient.getIllnessCode());
        }
		
	}
//list of vaccant rooms
	private void listvaccantRooms() {
		for (Ward w : hospital.getWards()) {
			int vaccantRooms = w.getCapacity() - w.getRooms().size();
			System.out.println("vaccant rooms in ward " + w.getWardNo() + " =" + vaccantRooms);
		}

	}

//discharge patient
	private void dischargepatient() throws IOException {
		
		String lineToRemove = "";
		System.out.println("Enter the patient id to be discharged:");
		String StrId = input.nextLine();
		int id = Integer.parseInt(StrId);

		for (Ward w : hospital.getWards()) {

			for (Iterator<Room> iterator = w.getRooms().iterator(); iterator.hasNext();) {
				Room r = iterator.next();
				if (r.getPatient().getPatientId() == id) {

					Patient patient = r.getPatient();
					lineToRemove = patient.getPatientId() + "," + patient.getPatientName() + "," + patient.getAge() + ","
							+ patient.getIllnessCode()+","+w.getWardNo() ;
					

					System.out
							.println(r.getPatient().getPatientName() + " discharged from the Hospital successfully...");
					iterator.remove();
				}
			}

		}
		
		
		 String fileName = "patient.txt";
	        
	        try {
	            File inputFile = new File(fileName);
	            File tempFile = new File(fileName + ".tmp");

	            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
	            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

	            String currentLine;

	            while ((currentLine = reader.readLine()) != null) {
	                // If the current line matches the line to remove, skip it
	                if (currentLine.equals(lineToRemove)) {
	                    continue;
	                }
	                writer.write(currentLine);
	                writer.newLine();
	            }

	            writer.close();
	            reader.close();

	            // Delete the original file and rename the temporary file to the original filename
	            inputFile.delete();
	            tempFile.renameTo(inputFile);

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        hospitalDetails();

	}


//admit patients
	private void admitPatient() throws IOException {

		int tempId=0;
		String fileName="patient.txt";
		Scanner scanner =new Scanner(new File(fileName));
		while(scanner.hasNext()) {
			String line=scanner.nextLine();
			String[] patientData=line.split(",");
			
			if(patientData.length==5) {
				 tempId=Integer.parseInt(patientData[0]);
			}
		}
		scanner.close();
		
		Patient patient = new Patient();
		if(tempId==0) {
			patient.setPatientId(1);
		}else {
			patient.setPatientId(tempId+1);
		}
		
		System.out.println("Enter the patient name");
		patient.setPatientName(input.nextLine());
		System.out.println("Enter the Age:");
		patient.setAge(Integer.parseInt(input.nextLine()));
		do {
		System.out.println("Enter the illness code");
		String tempIllnessCode=input.nextLine();
		if(patient.getAge()<18 ) {
			if( tempIllnessCode.equalsIgnoreCase("cvd") ||tempIllnessCode.equalsIgnoreCase("child") ) {
			
				patient.setIllnessCode(tempIllnessCode);
				break;
			}else {
				System.out.println("Enter a valid input");
			}
		}else {
			if(!tempIllnessCode.equalsIgnoreCase("child")) {
				patient.setIllnessCode(tempIllnessCode);
				break;
			}else {
				System.out.println("Enter a valid input");
			}	
		}
		}while(true);
		
		

		for (Ward w : hospital.getWards()) {
			if (patient.getIllnessCode().equalsIgnoreCase(w.getWardCode())) {
				Room room = new Room();
				room.setPatient(patient);
				if (w.getRooms().size() < w.getCapacity()) {
					w.getRooms().add(room);
					room.setRoomNo(w.getRooms().size());
					
					System.out.println("name: " + patient.getPatientName() + " Age: " + patient.getAge()
					+ " illness code: " + patient.getIllnessCode());
			
					System.out.println("admitted to " + w.getWardCode() + " ward successfully");

					FileWriter myWriter = new FileWriter("patient.txt", true);

					String data = patient.getPatientId() + "," + patient.getPatientName() + "," + patient.getAge() + ","
							+ patient.getIllnessCode()+","+w.getWardNo() ;
					
					
					myWriter.write(data);
					myWriter.write(System.lineSeparator());
					myWriter.close();

					break;
				} else {
					if (w.getWardNo() != 1) {
						System.out.println("No Rooms Available in " + w.getWardCode() + " ward");
					}

				}

			}
		}
		hospitalDetails();
		
	}
//hospitl details
	private void hospitalDetails() {
		for (Ward w : hospital.getWards()) {
			System.out.println("\n\nWard Code:"+w.getWardCode());
			for (Room r : w.getRooms()) {
				System.out.println("name: " + r.getPatient().getPatientName() + " Age: " + r.getPatient().getAge()
						+ " illness code: " + r.getPatient().getIllnessCode());

			}


	}
	}
//load hospital details
	private void loadHospitalDetails() throws FileNotFoundException {
		String fileName="patient.txt";
		Scanner scanner =new Scanner(new File(fileName));

		while(scanner.hasNext()) {
			String line=scanner.nextLine();
			String[] patientData=line.split(",");
			if(patientData.length==5) {
				int patientId=Integer.parseInt(patientData[0]);
				String patientName=patientData[1];
				int age =Integer.parseInt(patientData[2]);
				String illnessCode=patientData[3];
				int wardNo =Integer.parseInt(patientData[4]);
				
				Patient patient=new Patient(patientId,patientName,age,illnessCode);
				Room room=new Room();
				room.setPatient(patient);
				
				for(Ward w :hospital.getWards()) {
					if(w.getWardNo()==wardNo) {
						w.getRooms().add(room);
					}
				}
				
			}
		}
		scanner.close();
	}
	
	
}













   










