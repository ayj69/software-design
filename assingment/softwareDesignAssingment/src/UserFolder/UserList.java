package UserFolder;

import java.util.*;

import ReservationFolder.Reservation;

public class UserList {

	LinkedList<User> userList = new LinkedList<User>();

	public boolean valifyLogin(String username, String password) {
		return valifyLogin(userList, username, password);
	}

	public boolean checkUSerExist(String username) {
		return checkUSerExist(userList, username);

	}

	public boolean checkUSerExist(LinkedList<? extends User> list, String username) {
		if (list == null || username == null)
			throw new IllegalArgumentException();

		boolean flag = false;

		for (User u : list) {
			if (username.equals(u.getUserName())) {
				flag = true;
				break;
			} else {
				flag = false;
			}
		}

		if (flag == false) {
			throw new IllegalArgumentException("account not found");
		}

		return flag;

	}

	public boolean valifyLogin(LinkedList<? extends User> list, String username, String password) {
		if (list == null || username == null || password == null)
			throw new IllegalArgumentException();

		boolean flag = false;
		boolean found = false;

		for (User u : list) {
			if (username.equals(u.getUserName())) {
				if (password.equals(u.getPassword())) {
					flag = true;
					break;
				} else {
					throw new IllegalArgumentException("Incorrect Password");
//					flag = false;
//					break;
				}
			}
		}

		for (User u : list) {
			if (username.equals(u.getUserName())) {
				found = true;
			}
		}

		if (!found) {
			throw new IllegalArgumentException("account not found");
		}

		if (flag == true)
			System.out.println("login successful");

		return flag;
	}

	public User getUser(LinkedList<User> list, String username) {
		if (list == null || username == null)
			throw new IllegalArgumentException();

		boolean found = false;
		User cil = null;
		Optional<User> checkNull = Optional.ofNullable(cil);
		for (User u : list) {
			if (username.equals(u.getUserName())) {
				cil = u;
				found = true;
				break;
			}

			
		}

		
		return cil;

	}
	
	public void addUser() {
		//public User(String username, String phoneNumber, int age, String password, String status,LinkedList<Reservation> reservation) {
		boolean loop = false;
		Scanner scan = new Scanner(System.in);
		User tempUser = new User();
		// User user = new User("test1","0122323142",69,"password1","user",null);

		do {
			System.out.print("Please enter your username :  ");
			try {
				loop = false;
				String username = scan.next();
				tempUser.setUserName2(username);
			}catch(Exception e) {
				System.out.println(e.getLocalizedMessage());
				scan.nextLine();
				loop = true;
			}
		}while(loop);
		
		do {
			System.out.print("Please enter your password :  ");
			try {
				loop = false;
				String password = scan.next();
				tempUser.setPassword2(password);
			}catch(Exception e) {
				System.out.println(e.getLocalizedMessage());
				scan.nextLine();
				loop = true;
			}
		}while(loop);
		
		do {
			System.out.print("Please enter your age :  ");
			try {
				loop = false;
				String age = scan.next();
				tempUser.setAge2(age);
			}catch(Exception e) {
				System.out.println(e.getLocalizedMessage());
				scan.nextLine();
				loop = true;
			}
		}while(loop);
		
		do {
			System.out.print("Please enter your phone number :  ");
			try {
				loop = false;
				String phoneNum = scan.next();
				tempUser.setPhoneNum(phoneNum);
			}catch(Exception e) {
				System.out.println(e.getLocalizedMessage());
				scan.nextLine();
				loop = true;
			}
		}while(loop);

		tempUser.setStatus("user");
		tempUser.setReservation(null);
		
		this.userList.add(tempUser);
		
		
	}
	
	

	public User loginModule() {
		String username = "";
		String password = "";
		boolean loop = false;
		Scanner scan = new Scanner(System.in);

		System.out.println("              LOGIN");
		try {
			do {
				System.out.print("Pls enter your username to login  :  ");
				try {
					loop = false;
					username = scan.next();
				} catch (Exception e) {
					System.out.println(e.getLocalizedMessage());
					scan.nextLine();
					loop = true;
				}
			} while (loop);

			do {
				System.out.print("Pls enter your password to login  :  ");
				try {
					loop = false;
					password = scan.next();
				} catch (Exception e) {
					System.out.println(e.getLocalizedMessage());
					scan.nextLine();
					loop = true;
				}
			} while (loop);
			 if (this.valifyLogin(username, password))
				 return this.getUser(username);
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			System.out.println("[PROGRAME ENDED]");
			System.exit(0);

		}
		return this.getUser(username);
	}

	public LinkedList<User> getClientList() {
		return userList;
	}

	public User getUser(String username) {
		return getUser(this.userList, username);
	}

	public LinkedList<User> getUserList() {
		return userList;
	}

	public void setUserList(LinkedList<User> userList) {
		this.userList = userList;
	}

}
