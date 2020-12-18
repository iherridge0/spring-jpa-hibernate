package za.co.iherridge0.jpa.hibernate.springjpahibernate.repository;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import za.co.iherridge0.jpa.hibernate.springjpahibernate.SpringJpaHibernateApplication;
import za.co.iherridge0.jpa.hibernate.springjpahibernate.entity.Course;
import za.co.iherridge0.jpa.hibernate.springjpahibernate.entity.Review;

@SpringBootTest(classes = SpringJpaHibernateApplication.class)
class CourseRepositoryTest {

	Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	CourseRepository repository;

	@Autowired
	EntityManager em;

	@Test
	void findById_basic() {
		Course course = repository.findById(10001L);
		log.info("{}", course.toString());
		assertTrue(course.getName().equals("JPA in 50 Steps"));
	}

	@Test
	@DirtiesContext // ensures that data changed during test is rolled back to initual state. so if
					// other tests might use the same data, they would not fail due to other tests
					// using the same data
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

	@Test
	@Transactional
	void retrieveReviewsForCourse() {
		Course course = repository.findById(10001L);
		log.info("retrieveReviewsForCourse {}", course.getReviews());
	}

	@Test
	@Transactional
	void retrieveCourseForReview() {
		Review review = em.find(Review.class, 50001L);
		log.info("retrieveCourseForReview {}", review.getCourse());
	}

}
