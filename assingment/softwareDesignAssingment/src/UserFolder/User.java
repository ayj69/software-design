package UserFolder;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ReservationFolder.Reservation;
import ScheduleFolder.Bus;
import ScheduleFolder.Seat;

public class User {

	private String userID;// take current location of the index them auto generate should be easy enough fk who the fk say writhing this code in 2 day is gonna be easy gonna compromise this
	private String userName;
	private String phoneNumber;
	private int age;
	private String password;
	private String status;
	private LinkedList<Reservation> reservation;
	private String userNameRegex = "^[a-zA-Z0-9._-]{3,}$";
	private String phoneNumRegex = "^(01)[0-9]*[0-9]{7,8}$";
	private String numberRegex = "^[0-9]+$";
	private String doubleRegex = "^[0-9]+\\.?[0-9]*$";

	public User() {
	
	}
	
	public User(String username, String phoneNumber, int age, String password, String status,
			LinkedList<Reservation> reservation) {
		this.userName = username;
		this.phoneNumber = phoneNumber;
		this.age = age;
		this.password = password;
		this.status = status;
		this.reservation = reservation;
		// this.userID = userlist.autogenerateUserID;
	}

	// User user = new User("test1","0122323142",69,"password1",false,"user",null);

	public boolean checkStringRegex(String data, String regex) {
		Pattern pat = Pattern.compile(regex);
		Matcher mat;

		mat = pat.matcher(data);

		if (mat.find())
			return true;
		else
			return false;

	}
	
	public void setUserName2(String username) {

		if (this.checkStringRegex(	username, this.userNameRegex)) {
			this.userName = username;
		} else {
			throw new IllegalArgumentException("Error,this user name is not valid");
		}
	}
	
	public void setPassword2(String password) {

		if (this.checkStringRegex(password, this.userNameRegex)) {
			this.password = password;
		} else {
			throw new IllegalArgumentException("Error,this password is not valid");
		}
	}

	
	public boolean checkAge(int age) {

		if (age < 18 || age > 150) {
			throw new IllegalArgumentException("Error, Please reenter your age again, the limit is 18 - 150");
		} else {
			return true;
		}

	}

	public void setAge2(String agestr) {

		if (this.checkStringRegex(agestr, this.numberRegex)) {
			int age = Integer.parseInt(agestr);
			if (checkAge(age))
				this.age = age;
		} else {
			throw new IllegalArgumentException("Error,this age is not valid");
		}

	}
	
	
	
	public void setPhoneNum(String phoneNum) {

		if (this.checkStringRegex(phoneNum, this.phoneNumRegex)) {
			this.phoneNumber = phoneNum;
		} else {
			throw new IllegalArgumentException("Error,this phone Number is not valid");
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void deleteReservation() {
		Scanner scan = new Scanner(System.in);
		String choice = "-";

		while (!(choice.equals("x") || choice.equals("X"))) {

			for (Reservation rs : reservation) {
				System.out.println(rs.getSchedule().toString());
			}

			System.out.print("Please enter your choice or press X to exit  :  ");
			choice = scan.next();

			if (!(choice.equals("x") || choice.equals("X")) && isNumeric(choice)) {

				int number = Integer.parseInt(choice);

				if (number <= 0 || number > reservation.size()) {

					System.out.println("out of range");

				} else if (reservation.get(number - 1).getSchedule().getBus().getSeatList().get(0)
						.getSeatStatus() == "booked") {

					System.out.println("sry but this item is unavalible");

				} else {
					reservation.get(number - 1).getSchedule().getBus().getSeatList()
							.get(this.getReservation().get(number - 1).getSeat().getSeatNumber())
							.setSeatStatus("empty");
					reservation.remove(number - 1);

				}
			}
		}

		for (Reservation rs : reservation) {
			System.out.println(rs.getSchedule().toString());
		}
	}
	
	
	

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return status;
	}

	public LinkedList<Reservation> getReservation() {
		return reservation;
	}

	public void setReservation(LinkedList<Reservation> reservation) {
		this.reservation = reservation;
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


}
