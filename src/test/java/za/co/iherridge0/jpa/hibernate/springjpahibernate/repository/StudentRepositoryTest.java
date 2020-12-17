package za.co.iherridge0.jpa.hibernate.springjpahibernate.repository;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import za.co.iherridge0.jpa.hibernate.springjpahibernate.SpringJpaHibernateApplication;
import za.co.iherridge0.jpa.hibernate.springjpahibernate.entity.Passport;
import za.co.iherridge0.jpa.hibernate.springjpahibernate.entity.Student;

@SpringBootTest(classes = SpringJpaHibernateApplication.class)
class StudentRepositoryTest {

	Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	StudentRepository repository;

	@Autowired
	EntityManager em;

	@Test
	@DirtiesContext
	void saveStudentWithPassport() {
		repository.saveStudentWithPassport();
	}

	@Test
	@Transactional // Persistance context
	void retrieveStudentAndPassport() {
		Student student = em.find(Student.class, 20001L);
		log.info("student -> {}", student);
		log.info("passport -> {}", student.getPassport());
	}

	@Test
	@Transactional // Persistance context
	void retrievePassportAndAssociatedStudent() {
		Passport passport = em.find(Passport.class, 40001L);
		log.info("passport -> {}", passport);
		log.info("student -> {}", passport.getStudent());
	}

	@Test
	void someOperationToUnderstandPersistenceContext() {
		repository.someOperationToUnderstandPersistenceContext();
	}

}
