package ScheduleFolder;

import java.util.*;

import ReservationFolder.Reservation;

public class BusList {

	LinkedList<Bus> busList = new LinkedList<Bus>();

	public LinkedList<Bus> getBusList() {
		return busList;
	}

	public void setBusList(LinkedList<Bus> busList) {
		this.busList = busList;
	}

	public void deleteRecord() {
		Scanner scan = new Scanner(System.in);
		String choice = "-";

		while (!(choice.equals("x") || choice.equals("X"))) {

			for (Bus bus : busList) {
				System.out.println(bus.toString());
			}

			System.out.print("Please enter your choice or press X to exit  :  ");
			choice = scan.next();

			if (!(choice.equals("x") || choice.equals("X")) && isNumeric(choice)) {

				int number = Integer.parseInt(choice);

				if (number <= 0 || number > busList.size()) {

					System.out.println("out of range");

				} else {
					busList.remove(number - 1);

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

	public void modifyRecord() {
		// TODO Auto-generated method stub

	}

	public Bus addRecord(DriverList driverlist) {
		boolean loop;
		DriverList tempDriver = new DriverList();
		Scanner scan = new Scanner(System.in);
		int choice = 0;
//			public Bus(String numberPlate,Driver driver,int maxSeat){
//				this.numberPlate = numberPlate;
//				this.driver = driver;
//				this.seatList = this.generateSeat(maxSeat);
//			}

		Bus tempBus = new Bus("", null, 0);

		do {
			System.out.print("Please enter your number plate :  ");
			try {
				loop = false;
				String numberPlate = scan.next();
				tempBus.setNumberPlate(numberPlate);
			} catch (Exception e) {
				System.out.println(e.getLocalizedMessage());
				loop = true;
			}
		} while (loop);

		do {
			System.out.print("Please enter your seat number :  ");
			try {
				loop = false;
				String seat = scan.next();
				tempBus.setSeat(seat);
			} catch (Exception e) {
				System.out.println(e.getLocalizedMessage());
				loop = true;
			}
		} while (loop);
		
		System.out.println("do you want to choose from and existing list or create one your self");
		System.out.println("1. choose one from list");
		System.out.println("2. create one yourself");
		choice = readUserInput(1,2);
		if (choice == 1) {
			tempBus.setDriver(this.getDriver(driverlist.getDriverList()));
		}else if(choice == 2) {
			tempBus.setDriver(driverlist.addRecord());
		}
		
		
		
		
		return tempBus;

	}

	public void addRecordIntoList(DriverList driverlist) {
		this.busList.add(this.addRecord(driverlist));
	}
	
	
	
	public void updateBus(DriverList driverlist) {
		String choice = "-";
		int manuchoice;
		Scanner scan = new Scanner(System.in);
		Bus tempBus = null;
		boolean loop;
		int smallchoice;
		while (!(choice.equals("x") || choice.equals("X"))) {

			for (Bus bus : this.busList) {
				int i= 1;
				i++;
				System.out.println(i + "   " +this.busList.toString());
			}

			if (busList.isEmpty())
				throw new NullPointerException("ERROR, empty bus list");
			
			System.out.print("Please enter your choice or press X to exit  :  ");
			choice = scan.next();

			if (!(choice.equals("x") || choice.equals("X")) && isNumeric(choice)) {

				int number = Integer.parseInt(choice);

				if (number <= 0 || number > busList.size()) {

					System.out.println("out of range");

				} else {
					tempBus =  busList.get(number - 1);

				}
			}
		}
		
		System.out.println(tempBus.toString());
	
		System.out.println("update manu");
		System.out.println("1 update seat number");
		System.out.println("2 update number plate");
		System.out.println("3 update driver");
		System.out.println("4 exit");

		manuchoice = readUserInput(1,3);
		switch(manuchoice) {
		case 1:
			do {
				System.out.print("Please enter your seat number :  ");
				try {
					loop = false;
					String seat = scan.next();
					tempBus.setSeat(seat);
				} catch (Exception e) {
					System.out.println(e.getLocalizedMessage());
					loop = true;
				}
			} while (loop);
			break;
		case 2 :do {
			System.out.print("Please enter your number plate :  ");
			try {
				loop = false;
				String numberPlate = scan.next();
				tempBus.setNumberPlate(numberPlate);
			} catch (Exception e) {
				System.out.println(e.getLocalizedMessage());
				loop = true;
			}
		} while (loop);
			break;
		case 3 :
			System.out.println("do you want to choose from and existing list or create one your self");
			System.out.println("1. choose one from list");
			System.out.println("2. create one yourself");
			smallchoice = readUserInput(1,2);
			if (smallchoice == 1) {
				tempBus.setDriver(this.getDriver(driverlist.getDriverList()));
			}else if(smallchoice == 2) {
				tempBus.setDriver(driverlist.addRecord());
			}
			break;
		case 4:
			System.out.println("exit the update manu");

			break;
		default:
			System.out.println("ERROR,process to exit");

			break;
		}
		
		for (Bus bus : this.busList) {
			System.out.println(this.busList.toString());
		}
	}
	
	
	
	
	public Driver getDriver(LinkedList<Driver> driverlist) {
		String choice = "-";
		Scanner scan = new Scanner(System.in);
		Driver tempDriver = null;
		
		
		
		while (!(choice.equals("x") || choice.equals("X"))) {

			for (Driver driver : driverlist) {
				System.out.println(driverlist.toString());
			}

			System.out.print("Please enter your choice or press X to exit  :  ");
			choice = scan.next();

			if (!(choice.equals("x") || choice.equals("X")) && isNumeric(choice)) {

				int number = Integer.parseInt(choice);

				if (number <= 0 || number > driverlist.size()) {

					System.out.println("out of range");

				} else {
					tempDriver =  driverlist.get(number - 1);

				}
			}
		}
		return tempDriver;
	}
	
	public Bus getBusPlate(LinkedList<Bus> buslist,String busPlateNum) {
		if ( busPlateNum == null)
			throw new IllegalArgumentException();

		boolean found = false;
		Bus bus = null;
		for (Bus b : buslist) {
			if (busPlateNum.equals(b.getNumberPlate())) {
				bus = b;
				found = true;
				break;
			}
			
			
		}
		if(!found)
			throw new IllegalArgumentException("no record found");
		return bus;

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
