package za.co.iherridge0.jpa.hibernate.springjpahibernate.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class Student {

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private String name;

	/*
	 * address is added as part of the student table in the databse when using
	 * the @embedded annotation
	 * 
	 * create table student ( id bigint not null, city varchar(255), line1
	 * varchar(255), line2 varchar(255), name varchar(255) not null, passport_id
	 * bigint, primary key (id) )
	 */
	@Embedded
	private Address address;

	@OneToOne(fetch = FetchType.LAZY)
	private Passport passport;

	// student is the owning side of the relationship.
	@ManyToMany
	@JoinTable(name = "STUDENT_COURSE", joinColumns = @JoinColumn(name = "STUDENT_ID"), inverseJoinColumns = @JoinColumn(name = "COURSE_ID"))
	private List<Course> courses = new ArrayList<>();

	protected Student() {

	}

	public Student(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public Passport getPassport() {
		return passport;
	}

	public void setPassport(Passport passport) {
		this.passport = passport;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void addCourse(Course course) {
		this.courses.add(course);
	}

	public void removeCourse(Course course) {
		this.courses.remove(course);
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", address=" + address + ", passport=" + passport + "]";
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

}
