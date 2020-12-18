package za.co.iherridge0.jpa.hibernate.springjpahibernate.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import za.co.iherridge0.jpa.hibernate.springjpahibernate.entity.Employee;

@Repository
@Transactional
public class EmployeeRepository {

	Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EntityManager em;

	public Employee findById(Long id) {
		return em.find(Employee.class, id);
	}

	public void insert(Employee employee) {
		em.persist(employee);
	}

	public void deleteById(Long id) {
		Employee employee = findById(id);
		em.remove(employee);
	}

	public List<Employee> retrieveAllEmployees() {
		return em.createNamedQuery("Select e From Employee e", Employee.class).getResultList();
	}

}
