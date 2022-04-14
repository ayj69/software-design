package ReservationFolder;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import ScheduleFolder.Bus;
import ScheduleFolder.Schedule;
import ScheduleFolder.ScheduleList;
import ScheduleFolder.Seat;

public class Reservation {

	private String status;
	private int seatNumber;
	private Seat seat;
	private double price;
	private Schedule schedule;

	public Reservation() {


	}
	
	public Reservation(Seat seat, Schedule schedule) {
		this.seat = seat;
		this.schedule = schedule;

	}

	// Reservation reversation = new Reservation("booked",1,45,schedule);

	public LinkedList<Reservation> getItem(LinkedList<Schedule> scheduleList) {
		LinkedList<Reservation> tempArr = new LinkedList<Reservation>();
		String choice = "-";
		ScheduleList schedulelist = new ScheduleList();
		Scanner scan = new Scanner(System.in);
		int tempSeatNumber = 0;

		while (!(choice.equals("x") || choice.equals("X"))) {

			schedulelist.printArrayString(scheduleList);

			System.out.print("Please enter your choice or press X to exit  :  ");
			choice = scan.next();

			if (!(choice.equals("x") || choice.equals("X")) && isNumeric(choice)) {

				int number = Integer.parseInt(choice);

				if (number <= 0 || number > scheduleList.size()) {

					System.out.println("out of range");

				} else if (scheduleList.get(number - 1).getStatus() == "full") {

					System.out.println("sry but this item is unavalible");

				} else {
					Bus tempBus = scheduleList.get(number - 1).getBus();
					Seat tempseat = scheduleList.get(number - 1).getBus().getLatestSeat();
					// refactor this line of code
					scheduleList.get(number - 1).getBus().getSeatList().get(tempseat.getSeatNumber())
							.setSeatStatus("booked");

					tempArr.add(new Reservation(tempseat, scheduleList.get(number - 1)));

				}
			}
		}

		if (tempArr.isEmpty())
			System.out.println("the user did not chose any item");
		else
			System.out.println("this is your selected item : ");

		for (Reservation s : tempArr) {
			System.out.println(s.getSchedule().toString());
		}

		return tempArr;
	}

	public Seat getSeat() {
		return seat;
	}

	public void setSeat(Seat seat) {
		this.seat = seat;
	}

	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	public boolean isNumeric(String string) {
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
