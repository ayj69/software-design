package ScheduleFolder;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Driver {

	private String name;
	private int age;
	private String licence;
	String NameRegex = "^[a-zA-Z0-9._-]{3,}$";
	String distanceRegex = "^[0-9]+$";

	public Driver(String name, int age, String licence) {
		this.name = name;
		this.age = age;
		this.licence = licence;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public String getLicence() {
		return licence;
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

	public boolean checkAge(int age) {

		if (age <= 18 || age >= 80) {
			throw new IllegalArgumentException("Error, Please enter your distance, the limit is 18 - 100");
		} else {
			return true;
		}

	}

	public void setAge(String age) {

		if (this.checkStringRegex(age, this.distanceRegex)) {
			int tempage = Integer.parseInt(age);
			if (checkAge(tempage))
				this.age = tempage;
		} else {
			throw new IllegalArgumentException("Error,this age is not valid");
		}

	}

	public void setName(String name) {

		if (this.checkStringRegex(name, this.NameRegex)) {
			this.name = name;
		} else {
			throw new IllegalArgumentException("Error,this name is not valid");
		}

	}

	public void setLicense(String license) {

		if (this.checkStringRegex(license, this.NameRegex)) {
			this.licence = license;
		} else {
			throw new IllegalArgumentException("Error,this license is not valid");
		}

	}

	public String toString() {
		return this.name + "  " + this.age + "  " + this.licence;
	}

}
