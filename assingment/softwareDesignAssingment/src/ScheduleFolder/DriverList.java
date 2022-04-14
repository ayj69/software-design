package ScheduleFolder;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import UserFolder.User;

public class DriverList {

	LinkedList<Driver> driverList = new LinkedList<Driver>();

	public LinkedList<Driver> getDriverList() {
		return driverList;
	}

	public void setDriverList(LinkedList<Driver> driverList) {
		this.driverList = driverList;
	}

	public void updateDriver() {
		

		String choice = "-";
		int manuchoice;
		Scanner scan = new Scanner(System.in);
		Driver tempDriver = null;
		boolean loop;
		while (!(choice.equals("x") || choice.equals("X"))) {

			for (Driver driver : this.driverList) {
				int i = 1;
				i++;
				System.out.println(i + "   " + this.driverList.toString());
			}

			if (driverList.isEmpty())
				throw new NullPointerException("ERROR, empty driver list");
			
			System.out.print("Please enter your choice or press X to exit  :  ");
			choice = scan.next();

			if (!(choice.equals("x") || choice.equals("X")) && isNumeric(choice)) {

				int number = Integer.parseInt(choice);

				if (number <= 0 || number > driverList.size()) {

					System.out.println("out of range");

				} else {
					tempDriver =  driverList.get(number - 1);

				}
			}
		}
		
		System.out.println(tempDriver.toString());
	
		System.out.println("update manu");
		System.out.println("1 update driver name");
		System.out.println("2 update driver license");
		System.out.println("3 update update driver age");
		System.out.println("4 exit");

		manuchoice = readUserInput(1,3);
		
		switch(manuchoice) {
		case 1:
			do {
				System.out.print("Please enter your driver name :  ");
				try {
					loop = false;
					String name = scan.next();
					tempDriver.setName(name);
				} catch (Exception e) {
					System.out.println(e.getLocalizedMessage());
					loop = true;
				}
			} while (loop);
			break;
		case 2 :
			do {
				System.out.print("Please enter your license :  ");
				try {
					loop = false;
					String license = scan.next();
					tempDriver.setLicense(license);
				} catch (Exception e) {
					System.out.println(e.getLocalizedMessage());
					scan.nextLine();
					loop = true;
				}
			} while (loop);
			break;
		case 3 :
			do {
				System.out.print("Please enter your age :  ");
				try {
					loop = false;
					String age = scan.next();
					tempDriver.setAge(age);
				} catch (Exception e) {
					System.out.println(e.getLocalizedMessage());
					loop = true;
				}
			} while (loop);

			break;
		case 4:
			System.out.println("exit the update manu");

			break;
		default:
			System.out.println("ERROR,process to exit");

			break;
		}
		
		for(Driver driver : this.driverList) {
			System.out.println(driver.toString());
		}
		
	}
	
	
	public Driver addRecord() {
		boolean loop;
		Scanner scan = new Scanner(System.in);
//		public Driver(String name,int age,String licence){
//			this.name = name;
//			this.age = age;
//			this.licence = licence;
//		}

		Driver tempDriver = new Driver("", 0, "");

		do {
			System.out.print("Please enter your driver name :  ");
			try {
				loop = false;
				String name = scan.next();
				tempDriver.setName(name);
			} catch (Exception e) {
				System.out.println(e.getLocalizedMessage());
				loop = true;
			}
		} while (loop);

		do {
			System.out.print("Please enter your age :  ");
			try {
				loop = false;
				String age = scan.next();
				tempDriver.setAge(age);
			} catch (Exception e) {
				System.out.println(e.getLocalizedMessage());
				loop = true;
			}
		} while (loop);

		do {
			System.out.print("Please enter your license :  ");
			try {
				loop = false;
				String license = scan.next();
				tempDriver.setLicense(license);
			} catch (Exception e) {
				System.out.println(e.getLocalizedMessage());
				scan.nextLine();
				loop = true;
			}
		} while (loop);

		return tempDriver;

	}

	public void addRecordIntoList() {
		this.driverList.add(this.addRecord());
	}
	
	
	
	
	public void deleteRecord() {
		Scanner scan = new Scanner(System.in);
		String choice = "-";

		while (!(choice.equals("x") || choice.equals("X"))) {

			for (Driver driver : driverList) {
				System.out.println(driver.toString());
			}

			System.out.print("Please enter your choice or press X to exit  :  ");
			choice = scan.next();

			if (!(choice.equals("x") || choice.equals("X")) && isNumeric(choice)) {

				int number = Integer.parseInt(choice);

				if (number <= 0 || number > driverList.size()) {

					System.out.println("out of range");

				} else {
					driverList.remove(number - 1);

				}
			}
		}
	}

	public static boolean isNumeric(String string) {
		int intValue;

		System.out.println(String.format("Verify Choice: \"%s\"", string));

		if (string == null || string.equals("")) {
			System.out.println("Invalid Choice, it is null or empty.");
			return false;
		}

		try {
			intValue = Integer.parseInt(string);
			return true;
		} catch (NumberFormatException e) {
			System.out.println("Input String cannot be parsed to Integer.");
		}
		return false;
	}


	
	
	public Driver getDriverName(String drivername) {
		if ( drivername == null)
			throw new IllegalArgumentException();

		boolean found = false;
		Driver driver = null;
		for (Driver d : this.driverList) {
			if (drivername.equals(d.getName())) {
				driver = d;
				found = true;
				break;
			}

			
		}
		return driver;

	}
	
	
	public static int readUserInput(int min, int max) {
		Scanner scan = new Scanner(System.in);
		boolean loop = false;
		int integer = 0;
		do {
			System.out.print("Pls enter your choice  :  ");
			try {
				loop = false;
				integer = scan.nextInt();
				if (integer < min || integer > max)
					throw new IllegalArgumentException("the input are not in range");

			} catch (Exception e) {
				System.out.println(e.getLocalizedMessage());
				scan.nextLine();
				loop = true;
			}
		} while (loop);
		return integer;
	}
}
