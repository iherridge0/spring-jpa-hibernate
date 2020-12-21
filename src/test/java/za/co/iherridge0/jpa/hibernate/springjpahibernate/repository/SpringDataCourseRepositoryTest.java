package za.co.iherridge0.jpa.hibernate.springjpahibernate.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import za.co.iherridge0.jpa.hibernate.springjpahibernate.SpringJpaHibernateApplication;
import za.co.iherridge0.jpa.hibernate.springjpahibernate.entity.Course;

@SpringBootTest(classes = SpringJpaHibernateApplication.class)
class SpringDataCourseRepositoryTest {

	Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	CourseSpringDataRepository repository;

	@Test
	void findById_CoursePresent() {
		Optional<Course> findById = repository.findById(10001L);
		log.info("{}", findById.isPresent());
		assertTrue(findById.isPresent());

	}

	@Test
	void findById_CourseNotPresent() {
		Optional<Course> findById = repository.findById(20001L);
		log.info("{}", findById.isPresent());
		assertFalse(findById.isPresent());

	}

	@Test
	void playingAroundWithSpringDataRepository() {
		Course course = new Course("Microservices in 100 Steps");
		repository.save(course);

		log.info("Courses -> {} ", repository.findAll());
		log.info("Courses -> {} ", repository.count());

	}
}