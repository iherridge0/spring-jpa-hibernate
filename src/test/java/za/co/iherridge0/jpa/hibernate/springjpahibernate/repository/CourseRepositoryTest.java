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
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import za.co.iherridge0.jpa.hibernate.springjpahibernate.SpringJpaHibernateApplication;
import za.co.iherridge0.jpa.hibernate.springjpahibernate.entity.Course;
import za.co.iherridge0.jpa.hibernate.springjpahibernate.entity.Review;
import za.co.iherridge0.jpa.hibernate.springjpahibernate.entity.ReviewRating;

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
	@Transactional(isolation = Isolation.READ_COMMITTED)
	void retrieveCourseForReview() {
		Review review = em.find(Review.class, 50001L);
		log.info("retrieveCourseForReview {}", review.getCourse());
	}

	@Test
	@Transactional
	void addReviewToCourse() {
		Course course = repository.findById(10001L);
		course.addReview(new Review("Bananas!", ReviewRating.FIVE));
		log.info("retrieveCourseForReview {}", course.getReviews());
	}

	@Test
	@Transactional // If removed, then two select queries will be fired, each
	// findById call would
	// provide their own transaction
	void findById_firstLevelCacheDemo() {
		Course course = repository.findById(10001L); // SELECT performed
		log.info("First Retrieve {}", course);

		Course course2 = repository.findById(10001L); // Retrieved from Cache
		log.info("Second Retrieve {}", course2);
	}

}
