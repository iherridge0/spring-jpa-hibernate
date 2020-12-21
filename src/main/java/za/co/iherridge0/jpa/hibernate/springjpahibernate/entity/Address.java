package za.co.iherridge0.jpa.hibernate.springjpahibernate.entity;

import javax.persistence.Embeddable;

/*
 * address is added as part of the student table in the database when using
 * the @embedded annotation on Student and @embeddable annotation on Address
 * 
 * create table student ( id bigint not null, city varchar(255), line1
 * varchar(255), line2 varchar(255), name varchar(255) not null, passport_id
 * bigint, primary key (id) )
 */

@Embeddable
public class Address {

	private String line1;
	private String line2;
	private String city;

	protected Address() {

	}

	public Address(String line1, String line2, String city) {
		super();
		this.line1 = line1;
		this.line2 = line2;
		this.city = city;
	}

	public String getLine1() {
		return line1;
	}

	public void setLine1(String line1) {
		this.line1 = line1;
	}

	public String getLine2() {
		return line2;
	}

	public void setLine2(String line2) {
		this.line2 = line2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "Address [line1=" + line1 + ", line2=" + line2 + ", city=" + city + "]";
	}

}
