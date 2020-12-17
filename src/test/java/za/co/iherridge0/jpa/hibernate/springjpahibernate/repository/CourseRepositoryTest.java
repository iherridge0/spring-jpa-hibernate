package za.co.iherridge0.jpa.hibernate.springjpahibernate.repository;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import za.co.iherridge0.jpa.hibernate.springjpahibernate.SpringJpaHibernateApplication;
import za.co.iherridge0.jpa.hibernate.springjpahibernate.entity.Course;

@SpringBootTest(classes = SpringJpaHibernateApplication.class)
class CourseRepositoryTest {

	Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	CourseRepository repository;

	@Test
	void findById_basic() {
		Course course = repository.findById(10001L);
		log.info("{}", course.toString());
		assertTrue(course.getName().equals("JPA in 50 Steps"));
	}

	@Test
	@DirtiesContext
	void deleteById_basic() {
		repository.deleteById(10002L);
		assertNull(repository.findById(10002L));
	}

	@Test
	@DirtiesContext
	void save_basic() {
		// get course
		Course course = repository.findById(10001L);
		assertTrue(course.getName().equals("JPA in 50 Steps"));

		// set cource
		log.info("{}", course.toString());
		course.setName("JPA in 50 Steps - Updated");

		// check cource updated
		repository.save(course);
		assertTrue(course.getName().equals("JPA in 50 Steps - Updated"));
	}

	@Test
	@DirtiesContext
	void playWithEntityManager() {
		repository.playWithEntityManager();
	}

}
