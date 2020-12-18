package za.co.iherridge0.jpa.hibernate.springjpahibernate.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import za.co.iherridge0.jpa.hibernate.springjpahibernate.entity.Course;
import za.co.iherridge0.jpa.hibernate.springjpahibernate.entity.Review;

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

		Course course2 = findById(10001L);
		course2.setName("JPA in 50 Steps - Updated");

	}

	public void addReviewsForCourse() {
		// get the course 10003
		Course course = findById(10003L);

		log.info("course.getReviews() -> {}", course.getReviews());

		Review review = new Review("Befok!", "5");

		// Setting the relationship
		course.addReview(review);
		review.setCourse(course);

		log.info("course.getReviews() -> {}", course.getReviews());

		// save to database
		em.persist(review);

	}

	public void addReviewsForCourse(Long courseId, List<Review> reviews) {
		Course course = findById(courseId);

		log.info("course.getReviews() -> {}", course.getReviews());

		for (Review review : reviews) {
			course.addReview(review);
			review.setCourse(course);
			em.persist(review);
		}
	}
}
