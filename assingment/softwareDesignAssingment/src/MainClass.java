import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import ReservationFolder.Reservation;
import ReservationFolder.ReservationList;
import ScheduleFolder.Bus;
import ScheduleFolder.BusList;
import ScheduleFolder.Driver;
import ScheduleFolder.DriverList;
import ScheduleFolder.Schedule;
import ScheduleFolder.ScheduleList;
import ScheduleFolder.Seat;
import UserFolder.User;
import UserFolder.UserList;

public class MainClass {

	public static void main(String[] aers) {

		Scanner scan = new Scanner(System.in);

		int choice = 0;
		boolean loop = false;
		String exitChoice;
		boolean exitFlag = false;
		
		UserList userlist = new UserList();
		DriverList driverList = new DriverList();
		BusList buslist = new BusList();
		ScheduleList scheduleList = new ScheduleList();
		Reservation reservation = new Reservation();
		User tempUser = new User();

		ArrayList<User> tempUserList = readUserFile();
		ArrayList<Driver> tempDriverList = readDriverFile();
		ArrayList<List<String>> tempBusList = readBusFile();
		ArrayList<List<String>> tempScheduleList = readScheduleFile();

		for (User u : tempUserList)
			userlist.getUserList().add(u);
		
		for (Driver d : tempDriverList)
			driverList.getDriverList().add(d);

		for(List<String> str : tempBusList ) {
			Bus tempbus = new Bus(str.get(0), driverList.getDriverName(str.get(1)), Integer.parseInt(str.get(2)));
			buslist.getBusList().add(tempbus);
		}
			
		for(List<String> str : tempScheduleList ) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
			LocalDateTime dateTime = LocalDateTime.parse(str.get(1), formatter);
			
			Schedule schedule = new Schedule(str.get(0), dateTime , str.get(2), Integer.parseInt(str.get(3)), buslist.getBusPlate(buslist.getBusList(),str.get(4)), Double.parseDouble(str.get(5)), str.get(6));
			scheduleList.getScheduleList().add(schedule);
		}

		System.out.println("\n");

		;

		System.out.println("Welcome to bus Scheduling system");
		System.out.println("	1 login");
		System.out.println("	2 register");

		choice = readUserInput(1,2);
		switch(choice) {
		case 1:
			tempUser = userlist.loginModule();
			System.out.println("Welcome " + tempUser.getUserName().toString());
			break;
		case 2:
			userlist.addUser();
			break;
		default:
			break;
				
		
		}
		
		do {
			
			
			if (tempUser.getStatus().equals("admin")) {

				System.out.println("Admin Manu");
				System.out.println("	1 add Schedule");
				System.out.println("	2 delete Schedule");
				System.out.println("	3 modify Schedule");
				System.out.println("	4 add bus");
				System.out.println("	5 delete bus");
				System.out.println("	6 modify bus");
				System.out.println("	7 add driver");
				System.out.println("	8 delete driver");
				System.out.println("	9 modify driver");
				System.out.println("	0 exit");
				choice = readUserInput(0, 9);
				try {
					switch (choice) {
					case 1:
						scheduleList.addRecordIntoList(buslist, driverList);
						break;
					case 2:
						scheduleList.deleteSchedule();
						break;
					case 3:
						scheduleList.updateSchedule(buslist, driverList);
						break;
					case 4:
						buslist.addRecordIntoList(driverList);
						break;
					case 5:
						buslist.deleteRecord();
						break;
					case 6:
						buslist.updateBus(driverList);
						break;
					case 7:
						driverList.addRecordIntoList();
						break;
					case 8:
						driverList.deleteRecord();
						break;
					case 9:
						driverList.updateDriver();
						break;
					case 0 :
						System.out.println("[PROGRAME ENDED]");
						System.exit(0);
						break;
					default:
						break;

					}
				}catch(Exception e) {
					System.out.println(e.getLocalizedMessage());
				}
				

			} else if (tempUser.getStatus().equals("user")) {

				System.out.println("   client manu");
				System.out.println("	1 add Reservation");
				System.out.println("	2 delete Reservation");
				System.out.println("	3 generate receipt");
				System.out.println("	4 view all schedule");
				System.out.println("	5 view all Reservation");
				System.out.println("	6 exit");
				choice = readUserInput(1, 7);

				switch (choice) {
				case 1:
					LinkedList<Reservation> templist = reservation.getItem(scheduleList.getScheduleList());
					ReservationList rl = new ReservationList();
					tempUser.setReservation(templist);
					break;
				case 2:
					
					
					try{
						if (tempUser.getReservation() == null)
							throw new NullPointerException("nothing reserved yet");
						tempUser.deleteReservation();
					}catch(Exception e) {
						System.out.println(e.getLocalizedMessage());
					}
					break;
				case 3:
					try{
						double totalprice = 0;
						if (tempUser.getReservation() == null)
							throw new NullPointerException("nothing reserved yet");
						System.out.print("\n\\Your Reservation\\\n--------------------------------------------------------------------------------------------------------------------------------------------------\n");

						for (Reservation s: tempUser.getReservation()) {
							System.out.println(s.getSchedule().toString());
						}	
						System.out.print("------------------------------------------------------------------------------------------------------------------------------------------------\n");
						for (Reservation s: tempUser.getReservation()) {
							totalprice = totalprice + s.getSchedule().getPrice();
						}	
						System.out.println("Total Price = RM"+ totalprice);
						
					}catch(Exception e) {
						System.out.println(e.getLocalizedMessage());
					}
					break;
				case 4:
					scheduleList.printAll();
					break;
				case 5:
					try{
						if (tempUser.getReservation() == null)
							throw new NullPointerException("nothing reserved yet");
						System.out.print("\n\\All Reservation\\\n-------------------------------------------------------------------------------------------------------------------------------------------------\n");
						for (Reservation s: tempUser.getReservation()) {
							System.out.println(s.getSchedule().toString());
						}	
						System.out.print("------------------------------------------------------------------------------------------------------------------------------------------------\n");

					}catch(Exception e) {
						System.out.println(e.getLocalizedMessage());
					}
					
					
					break;
				case 6:
					System.out.println("[PROGRAME ENDED]");
					System.exit(0);
					break;
				default:
					break;

				}

			} else {
				System.out.println("account not found");
			}
			
			
			

			
			do {
				System.out.print("Do you want to exit the program? (Y/N) : ");
				try {
					loop = false;
					exitChoice = scan.next();
					exitFlag  = getyn(exitChoice);
				}catch(Exception e) {
					System.out.println(e.getLocalizedMessage());
					scan.nextLine();
					loop = true;
				}
			}while(loop);
			
			System.out.println("[PROGRAME ENDED]");

		}while(!exitFlag);
		

		

//		scheduleList.printAll();
//		LinkedList<Reservation> templist = reservation.getItem(scheduleList.getScheduleList());
//		ReservationList rl = new ReservationList();
//		tempUser.setReservation(templist);
//				
//		
//		System.out.println(		tempUser.getReservation().get(0).getSchedule().toString());
//		
//		
//		for (Reservation s : tempUser.getReservation()) {
//			System.out.println(	s.getSchedule().toString());
//		}

		// Reservation reversation = new Reservation("booked",1,45,schedule);

		// need to add in fkfkfkfkfkfkfkfkfkfkfkfkfkfkfkfk data structure first

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
				scan.nextLine();
				loop = true;
				System.out.println(e.getLocalizedMessage());
			}
		} while (loop);
		return integer;
		
	}
	
	
	
	public static ArrayList<User> readUserFile() {

		System.out.println("Load User Data...");
		String linedata;
		List data;
		List<String> dataline = new ArrayList<String>();
		ArrayList<User> userList = new ArrayList<User>();

		try {
			File file = new File("data/user.txt");
			Scanner scanf = new Scanner(file);
			scanf.useDelimiter(",");

			while (scanf.hasNextLine()) {
				String str = scanf.nextLine();

				List<String> stratt = Arrays.asList(str.split(","));

				User user = new User(stratt.get(0), stratt.get(1), Integer.parseInt(stratt.get(2)),stratt.get(3),stratt.get(4),null);
				userList.add(user);

			}

			scanf.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		return userList;

	}
	
	public static ArrayList<Driver> readDriverFile() {

		System.out.println("Load Driver Data...");
		String linedata;
		List data;
		List<String> dataline = new ArrayList<String>();
		ArrayList<Driver> driverList = new ArrayList<Driver>();

		try {
			File file = new File("data/driver.txt");
			Scanner scanf = new Scanner(file);
			scanf.useDelimiter(",");

			while (scanf.hasNextLine()) {
				String str = scanf.nextLine();

				List<String> stratt = Arrays.asList(str.split(","));

				Driver driver = new Driver(stratt.get(0), Integer.parseInt(stratt.get(1)), stratt.get(2));
				driverList.add(driver);

			}

			scanf.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		return driverList;

	}
	
	public static ArrayList<List<String>> readBusFile() {

		System.out.println("Load Bus Data...");
		String linedata;
		List data;
		List<String> dataline = new ArrayList<String>();
		ArrayList<List<String>> stratt = new ArrayList<List<String>>();
		try {
			File file = new File("data/bus.txt");
			Scanner scanf = new Scanner(file);
			scanf.useDelimiter(",");

			while (scanf.hasNextLine()) {
				String str = scanf.nextLine();

				List<String> temp = Arrays.asList(str.split(","));
				stratt.add(temp);
				
				
			}

			scanf.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		return stratt;

	}
	
	public static ArrayList<List<String>> readScheduleFile() {

		System.out.println("Load Schedule Data...");
		String linedata;
		List data;
		List<String> dataline = new ArrayList<String>();
		ArrayList<List<String>> stratt = new ArrayList<List<String>>();
		try {
			File file = new File("data/schedule.txt");
			Scanner scanf = new Scanner(file);
			scanf.useDelimiter(",");

			while (scanf.hasNextLine()) {
				String str = scanf.nextLine();

				List<String> temp = Arrays.asList(str.split(","));
				stratt.add(temp);
				
				
			}

			scanf.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		return stratt;

	}
	
	public static boolean getyn(String choice) {
		boolean flag = false;
		Scanner scan = new Scanner(System.in);




			if ((choice.equals("Y") || choice.equals("y"))) {
				flag = true;
			} else if ((choice.equals("N") || choice.equals("n"))) {
				flag = false;
			} else if (!(choice.equals("Y") || choice.equals("y") || choice.equals("n") || choice.equals("N"))) {
				throw new IllegalArgumentException("Error,this choice is not valid");
			}

			return flag;

	}

}
