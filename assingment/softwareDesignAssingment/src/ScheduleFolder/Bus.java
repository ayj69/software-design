package ScheduleFolder;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Bus {

	private String numberPlate;
	private Driver driver;
	private ArrayList<Seat> seatList = new ArrayList<Seat>();
	String NameRegex = "^[a-zA-Z0-9._-]{3,}$";
	String numberRegex = "^[0-9]+$";

	public Bus(String numberPlate, Driver driver, int maxSeat) {
		this.numberPlate = numberPlate;
		this.driver = driver;
		this.seatList = this.generateSeat(maxSeat);
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public boolean checkStringRegex(String data, String regex) {
		Pattern pat = Pattern.compile(regex);
		Matcher mat;

		mat = pat.matcher(data);

		if (mat.find()) {
			return true;

		} else {
			return false;
		}

	}

	public void setNumberPlate(String numberPlate) {

		if (this.checkStringRegex(numberPlate, this.NameRegex)) {
			this.numberPlate = numberPlate;
		} else {
			throw new IllegalArgumentException("Error,this number plate is not valid");
		}
	}

	public boolean checkSeat(int seat) {

		if (seat < 0 || seat > 40) {
			throw new IllegalArgumentException("Error, Please reenter your seat number, the limit is 0 - 40");
		} else {
			return true;
		}

	}

	public void setSeat(String seat) {

		if (this.checkStringRegex(seat, this.numberRegex)) {
			int tempSeat = Integer.parseInt(seat);
			if (checkSeat(tempSeat))
				this.seatList = this.generateSeat(tempSeat);
		} else {
			throw new IllegalArgumentException("Error,this seat is not valid");
		}

	}

	public ArrayList<Seat> getSeatList() {
		return seatList;
	}

	public ArrayList<Seat> generateSeat(int maxSeat) {

		for (int i = 1; i < maxSeat + 1; i++) {
			this.seatList.add(new Seat(i, "empty"));
		}

		return seatList;

	}

	public boolean checkIfFull() {
		boolean check = true;
		try {
			for (Seat seat : seatList) {
				if (seat.getSeatStatus().equals("empty"))
					check = false;
			}
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}

		return check;
	}

	public int getAvailableSeat() {
		int emptyseat = 0;

		for (Seat seat : seatList) {
			if (seat.getSeatStatus().equals("empty"))
				emptyseat++;
		}

		return emptyseat;

	}

	
	
	public String getNumberPlate() {
		return numberPlate;
	}

	public Seat getLatestSeat() {
		int seatnumber = 0;

		try {
			for (Seat seat : seatList) {
				if (seat.getSeatStatus().equals("empty")) {
					seatnumber = seat.getSeatNumber();
					break;
				}

			}
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}

		return this.getSeatList().get(seatnumber - 1);
	}

	public String toString() {
		return numberPlate + "  " + this.driver.toString() + "  "
				+ this.getAvailableSeat();

	}


}
