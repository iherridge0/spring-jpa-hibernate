package za.co.iherridge0.jpa.hibernate.springjpahibernate.repository;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import za.co.iherridge0.jpa.hibernate.springjpahibernate.entity.Course;

@Repository
public class CourseRepository {

	@Autowired
	EntityManager em;

	public Course findById(Long id) {
		return em.find(Course.class, id);
	}

	/*
	 * public Course save(Course course) { return new Course("new"); }
	 */

}
