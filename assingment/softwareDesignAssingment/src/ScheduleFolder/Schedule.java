package ScheduleFolder;

import java.time.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Schedule {

	private String destination;
	private LocalDateTime departureTime;
	private LocalDateTime arrivalTime;
	private String departurePoint;
	private Bus bus;
	private double price;
	private String status;
	private int duration;
	private String NameRegex = "^[a-zA-Z0-9._-]{3,}$";
	private String numberRegex = "^[0-9]+$";
	private String doubleRegex = "^[0-9]+\\.?[0-9]*$";

	public Schedule() {
	}

	public void setBus(Bus bus) {
		this.bus = bus;
	}

	public Schedule(String destination, LocalDateTime departureTime, String departurePoint, int duration, Bus bus,
			double price, String status) {
		this.destination = destination;
		this.departureTime = departureTime;
		this.arrivalTime = departureTime.plusHours(duration);
		this.departurePoint = departurePoint;
		this.bus = bus;
		this.price = price;
		this.status = status;
	}

	public String toString() {
		return String.format("%-15s",this.destination) + String.format("%-25s",this.departureTime.toString()) + "  " + String.format("%-15s",this.arrivalTime.toString()) + "    "
				+ String.format("%-15s",this.departurePoint) + "  " + String.format("%-15s",this.bus.toString()) + "  " + String.format("%-15s",this.price);
	}

	public double getPrice() {
		return price;
	}

	public Bus getBus() {
		return bus;
	}

	public boolean checkIfFull() {
		return this.bus.checkIfFull();
	}

	public String getStatus() {
		return status;
	}

	public void setDestination(String destination) {

		if (this.checkStringRegex(destination, this.NameRegex)) {
			this.destination = destination;
		} else {
			throw new IllegalArgumentException("Error,this number plate is not valid");
		}
	}

	public void setDeparturePoint(String departurePoint) {

		if (this.checkStringRegex(departurePoint, this.NameRegex)) {
			this.departurePoint = departurePoint;
		} else {
			throw new IllegalArgumentException("Error,this number plate is not valid");
		}
	}

	public boolean checkPrice(double duration) {

		if (duration < 0 || duration > 23) {
			throw new IllegalArgumentException("Error, Please reenter your traval duration, the limit is 0 - 23");
		} else {
			return true;
		}

	}

	public void setPrice(String duration) {

		if (this.checkStringRegex(duration, this.doubleRegex)) {
			double tempPrice = Double.parseDouble(duration);
			if (checkPrice(tempPrice))
				this.price = tempPrice;
		} else {
			throw new IllegalArgumentException("Error,this hour is not valid");
		}

	}

	public String getDeparturePoint() {
		return departurePoint;
	}

	public LocalDateTime getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(LocalDateTime departureTime) {
		this.departureTime = departureTime;
	}

	public boolean checkDuration(int duration) {

		if (duration < 0 || duration > 23) {
			throw new IllegalArgumentException("Error, Please reenter your traval duration, the limit is 0 - 23");
		} else {
			return true;
		}

	}

	public void setDuration(String duration) {

		if (this.checkStringRegex(duration, this.numberRegex)) {
			int tempDuration = Integer.parseInt(duration);
			if (checkDuration(tempDuration))
				this.duration = tempDuration;
		} else {
			throw new IllegalArgumentException("Error,this hour is not valid");
		}

	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean checkStringRegex(String data, String regex) {
		Pattern pat = Pattern.compile(regex);
		Matcher mat;

		mat = pat.matcher(data);

		if (mat.find())
			return true;
		else
			return false;

	}
	// Driver driver = new Driver("abu",12,"69420BRAZEIT");
	// Seat seat = new Seat(1, "empty");
	// Bus newBus = new Bus("RIDE 1984", driver, 40);
	// LocalDateTime temptime =
	// LocalDateTime.of(LocalDate.of(LocalDate.now().getYear(), 6, 9),
	// LocalTime.NOON);
	// RoadSchedule roadSchedule = new
	// RoadSchedule("holand",temptime,"penang",6,newBus);

}
