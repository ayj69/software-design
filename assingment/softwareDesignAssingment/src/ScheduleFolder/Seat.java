package ScheduleFolder;

public class Seat {
	private int seatNumber;
	private String seatStatus;

	public Seat(int seatNumber, String seatStatus) {
		this.seatNumber = seatNumber;
		this.seatStatus = seatStatus;
	}

	public int getSeatNumber() {
		return seatNumber;
	}

	public String getSeatStatus() {
		return seatStatus;
	}

	public void setSeatStatus(String seatStatus) {
		this.seatStatus = seatStatus;
	}

	public boolean isAvailable() {
		if (this.seatStatus.equals("available"))
			return true;
		else
			return false;
	}

}
