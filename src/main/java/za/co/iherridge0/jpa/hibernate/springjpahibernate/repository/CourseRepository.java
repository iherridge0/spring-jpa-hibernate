package za.co.iherridge0.jpa.hibernate.springjpahibernate.repository;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import za.co.iherridge0.jpa.hibernate.springjpahibernate.entity.Course;

@Repository
@Transactional
public class CourseRepository {

	Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EntityManager em;

	public Course findById(Long id) {
		return em.find(Course.class, id);
	}

	public Course save(Course course) {
		if (course.getId() == null) {
			em.persist(course);
		} else {
			em.merge(course);
		}
		return course;
	}

	public void deleteById(Long id) {
		Course course = findById(id);
		em.remove(course);
	}

	public void playWithEntityManager() {
		Course course = new Course("course - Web Services in 100 steps");
		em.persist(course);
		Course course2 = new Course("course2 - Web Services in 100 steps");
		em.persist(course2);
		Course course3 = new Course("course3 - Web Services in 100 steps");
		em.persist(course3);

		em.flush();

		em.detach(course); // changes on course will not be tracked from here

		course.setName("course - Web Services in 100 step - updated");
		em.flush();

		course2.setName("course2 - Web Services in 100 steps - updated");
		em.flush();

		course3.setName("course3 - Web Services in 100 steps - updated");

		// all changed made on course 3 since last flush for this object will not be
		// persisted
		em.refresh(course3);
		em.flush();
	}
}
