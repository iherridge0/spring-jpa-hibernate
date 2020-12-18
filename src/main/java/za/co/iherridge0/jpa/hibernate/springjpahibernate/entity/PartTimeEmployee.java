package za.co.iherridge0.jpa.hibernate.springjpahibernate.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;

@Entity
public class PartTimeEmployee extends Employee {

	private BigDecimal hourlyWage;

	protected PartTimeEmployee() {

	}

	public PartTimeEmployee(String name, BigDecimal hourlyWage) {
		super.setName(name);
		this.setHourlyWage(hourlyWage);
	}

	public BigDecimal getHourlyWage() {
		return hourlyWage;
	}

	public void setHourlyWage(BigDecimal hourlyWage) {
		this.hourlyWage = hourlyWage;
	}
}
