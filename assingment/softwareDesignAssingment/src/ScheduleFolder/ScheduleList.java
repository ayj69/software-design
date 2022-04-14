package ScheduleFolder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

import ReservationFolder.Reservation;

public class ScheduleList {

	LinkedList<Schedule> scheduleList = new LinkedList<Schedule>();

	public void printAll() {
		System.out.print("\nAvalible Schedule\n----------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");

		for (int i = 0; i < scheduleList.size(); i++) {
			System.out.println(scheduleList.get(i).toString());
		}
		System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

	}

	public void printArrayString(LinkedList<?> tempArr) {
		System.out.print("\nAvalible Schedule\n----------------------------------------------------------------------------------\n");
		for (int i = 0; i < tempArr.size(); i++) {
			System.out.println(i+1 + "   " + tempArr.get(i).toString());
		}
		System.out.println("----------------------------------------------------------------------------------");

	}

	public LinkedList<Schedule> getScheduleList() {
		return scheduleList;
	}

	public void setScheduleList(LinkedList<Schedule> scheduleList) {
		this.scheduleList = scheduleList;
	}

	public Schedule addSchedule(BusList buslist,DriverList driverlist) {
//		
//		private String destination;
//		private LocalDateTime departureTime;
//		private LocalDateTime arrivalTime;
//		private String departurePoint;
//		private Bus bus;
//		private double price;
//		private String status;
//		private int duration;
		
//		public Schedule(String destination,LocalDateTime departureTime,String departurePoint,int duration,Bus bus,double price,String status){
//			this.destination = destination;
//			this.departureTime = departureTime;
//			this.arrivalTime = departureTime.plusHours(duration);
//			this.departurePoint = departurePoint;
//			this.bus = bus;
//			this.price = price;
//			this.status = status;
//		}
		boolean loop;
		DriverList tempDriver = new DriverList();
		Schedule tempschedule = new Schedule();
		BusList tempBus = new BusList();
		Scanner scan = new Scanner(System.in);
		int choice = 0;
		
		do {
			System.out.print("Please enter your destination :  ");
			try {
				loop = false;
				String destination = scan.next();
				tempschedule.setDestination(destination);
			} catch (Exception e) {
				System.out.println(e.getLocalizedMessage());
				loop = true;
			}
		} while (loop);
		
		do {
			System.out.print("Please enter your departurePoint :  ");
			try {
				loop = false;
				String departurePoint = scan.next();
				tempschedule.setDeparturePoint(departurePoint);
			} catch (Exception e) {
				System.out.println(e.getLocalizedMessage());
				loop = true;
			}
		} while (loop);
		
		do {
			System.out.print("Please enter your duration :  ");
			try {
				loop = false;
				String duration = scan.next();
				tempschedule.setDuration(duration);
			}catch(Exception e) {
				System.out.println(e.getLocalizedMessage());
				scan.nextLine();
				loop = true;
			}
		}while(loop);
		
		do {
			System.out.print("Please enter your price :  ");
			try {
				loop = false;
				String price = scan.next();
				tempschedule.setDuration(price);
			}catch(Exception e) {
				System.out.println(e.getLocalizedMessage());
				scan.nextLine();
				loop = true;
			}
		}while(loop);
		
		System.out.println("do you want to choose from and existing list or create one your self");
		System.out.println("1. choose one from list");
		System.out.println("2. create one yourself");
		choice = readUserInput(1,2);
		if(choice == 1) {
			tempschedule.setBus(this.getBus(buslist.getBusList()));
		}else if(choice == 2) {
			tempschedule.setBus(buslist.addRecord(driverlist));
		}
		
		tempschedule.setStatus("available");
		
		LocalDateTime departureTime = LocalDateTime.of(getDate(), getHour());
		tempschedule.setDepartureTime(departureTime);
		
		
		return tempschedule;
		
		
	}

	public void updateSchedule(BusList buslist,DriverList driverlist) {
		String choice = "-";
		int manuchoice;
		Scanner scan = new Scanner(System.in);
		Schedule tempschedule = null;
		boolean loop;
		int smallchoice;


			for (Schedule schedule : this.scheduleList) {
				int i = 1;
				i ++;
				System.out.println(i + "   " +this.scheduleList.toString());
			}

			if (scheduleList.isEmpty())
				throw new NullPointerException("ERROR, empty schedule");
			
			System.out.print("Please enter your choice or press X to exit  :  ");
			choice = scan.next();

			if (!(choice.equals("x") || choice.equals("X")) && isNumeric(choice)) {

				int number = Integer.parseInt(choice);

				if (number <= 0 || number > scheduleList.size()) {

					System.out.println("out of range");

				} else {
					tempschedule =  scheduleList.get(number - 1);

				}
			}
		
		
			if (tempschedule == null)
				throw new NullPointerException("ERROR, empty schedule");

			
		System.out.println(tempschedule.toString());
		
		
		System.out.println("update manu");
		System.out.println("1 update destination");
		System.out.println("2 update departureTime");
		System.out.println("3 update departurePoint");
		System.out.println("4 update duration");
		System.out.println("5 update bus");
		System.out.println("6 update price");
		System.out.println("7 exit");

		manuchoice = readUserInput(1,7);
		switch(manuchoice) {
		case 1:
			do {
				System.out.print("Please enter your destination :  ");
				try {
					loop = false;
					String destination = scan.next();
					tempschedule.setDestination(destination);
				} catch (Exception e) {
					System.out.println(e.getLocalizedMessage());
					loop = true;
				}
			} while (loop);
			break;
		case 2 :
			LocalDateTime departureTime = LocalDateTime.of(getDate(), getHour());
			tempschedule.setDepartureTime(departureTime);
			break;
		case 3 :
			do {
				System.out.print("Please enter your departurePoint :  ");
				try {
					loop = false;
					String departurePoint = scan.next();
					tempschedule.setDeparturePoint(departurePoint);
				} catch (Exception e) {
					System.out.println(e.getLocalizedMessage());
					loop = true;
				}
			} while (loop);
			break;
		case 4:
			do {
				System.out.print("Please enter your duration :  ");
				try {
					loop = false;
					String duration = scan.next();
					tempschedule.setDuration(duration);
				}catch(Exception e) {
					System.out.println(e.getLocalizedMessage());
					scan.nextLine();
					loop = true;
				}
			}while(loop);
			break;
		case 5 :
			System.out.println("do you want to choose from and existing list or create one your self");
			System.out.println("1. choose one from list");
			System.out.println("2. create one yourself");
			smallchoice = readUserInput(1,2);
			if(smallchoice == 1) {
				tempschedule.setBus(this.getBus(buslist.getBusList()));
			}else if(smallchoice == 2) {
				tempschedule.setBus(buslist.addRecord(driverlist));
			}
			break;
		case 6 :
			do {
				System.out.print("Please enter your price :  ");
				try {
					loop = false;
					String price = scan.next();
					tempschedule.setPrice(price);
				}catch(Exception e) {
					System.out.println(e.getLocalizedMessage());
					scan.nextLine();
					loop = true;
				}
			}while(loop);
			break;
		case 7:
			System.out.println("exit the update manu");
			break;
		default:
			System.out.println("ERROR,process to exit");
			break;
		}
		
		
		
		
		for (Schedule schedule : this.scheduleList) {
			System.out.println(schedule.toString());
		}
		
		
	}

	
	public void addRecordIntoList(BusList buslist,DriverList driverlist) {
		this.scheduleList.add(this.addSchedule(buslist, driverlist));
	}
	
	public Bus getBus(LinkedList<Bus> buslist) {
		String choice = "-";
		Scanner scan = new Scanner(System.in);
		Bus tempbus = null;
		while (!(choice.equals("x") || choice.equals("X"))) {

			for (Bus bus : buslist) {
				System.out.println(bus.toString());
			}

			System.out.print("Please enter your choice or press X to exit  :  ");
			choice = scan.next();

			if (!(choice.equals("x") || choice.equals("X")) && isNumeric(choice)) {

				int number = Integer.parseInt(choice);

				if (number <= 0 || number > buslist.size()) {

					System.out.println("out of range");

				} else {
					tempbus =  buslist.get(number - 1);

				}
			}
		}
		return tempbus;
	}
	
	
	public LocalTime getHour() {

		LocalTime time = null;
		int hour;
		int minute;
		int second = 00;
		Scanner scan = new Scanner(System.in);
		boolean get = false;
		System.out.println("PLease enter your departure time");

		do {

			try {

				System.out.print("Hour :  ");
				hour = scan.nextInt();

				System.out.print("Minute :  ");
				minute = scan.nextInt();
								
				if (validateMonthDay(hour, minute, second) == true)
					time = LocalTime.of(hour, minute, second);

				get = true;

			} catch (Exception e) {
				System.out.println(e.getMessage());
				System.out.println("ERROR, invalid input");
				scan.nextLine();
			}

		} while (!get || time == null);

		scan.close();
		return time;
	}
	
	
	
	public LocalDate getDate() {

		LocalDate date = null;
		int year = LocalDate.now().getYear();
		int month;
		int days;
		Scanner scan = new Scanner(System.in);
		boolean get = false;
		System.out.println("PLease enter your departure date");

		do {

			try {

				System.out.print("Days :  ");
				days = scan.nextInt();

				System.out.print("Month :  ");
				month = scan.nextInt();

				if (validateMonthDay(year, month, days) == true)
					date = LocalDate.of(year, month, days);

				get = true;

			} catch (Exception e) {
				System.out.println(e.getMessage());
				System.out.println("ERROR, invalid input");
				scan.nextLine();
			}

		} while (!get || date == null);

		scan.close();
		return date;
	}
	
	public boolean validateHour(int hour, int minute, int second) {

		boolean flag = false;
		LocalTime time = LocalTime.of(hour, minute);


			if (hour > 0 && hour < 24) {
				if (minute <= 29 && minute > 0)
					flag = true;
				else
					throw new IllegalArgumentException("ERROR, Invalid time.");
			}





		return flag;
	}
	
	
	public boolean validateMonthDay(int year, int month, int days) {

		boolean flag;
		LocalDate dateNow = LocalDate.now();
		LocalDate date = LocalDate.of(year, month, days);

		switch (month) {
		case 1, 3, 5, 7, 9, 11:

			if (days <= 31 && days > 0)
				flag = true;
			else
				throw new IllegalArgumentException("ERROR, Invalid date.");

			break;
		case 4, 6, 8, 10, 12:

			if (days <= 30 && days > 0)
				flag = true;
			else
				throw new IllegalArgumentException("ERROR, Invalid date.");

			break;
		case 2:

			if ((year % 4 == 0) && !(year % 100 == 0)) {
				if (days <= 29 && days > 0)
					flag = true;
				else
					throw new IllegalArgumentException("ERROR, Invalid date.");
			} else {
				if (days <= 28 && days > 0)
					flag = true;
				else
					throw new IllegalArgumentException("ERROR, Invalid date.");
			}
			break;

		default:
			flag = false;
			break;
		}

		if (date.isBefore(dateNow)) {
			throw new IllegalArgumentException("ERROR, Please enter today or future date.");
		}

		return flag;
	}
	
	public void deleteSchedule() {
		Scanner scan = new Scanner(System.in);
		String choice = "-";

		while (!(choice.equals("x") || choice.equals("X"))) {

			for (Schedule schedule : scheduleList) {
				System.out.println(schedule.toString());
			}

			System.out.print("Please enter your choice or press X to exit  :  ");
			choice = scan.next();

			if (!(choice.equals("x") || choice.equals("X")) && isNumeric(choice)) {

				int number = Integer.parseInt(choice);

				if (number <= 0 || number > scheduleList.size()) {

					System.out.println("out of range");

				} else {
					scheduleList.remove(number - 1);

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
