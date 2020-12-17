package za.co.iherridge0.jpa.hibernate.springjpahibernate.repository;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import za.co.iherridge0.jpa.hibernate.springjpahibernate.SpringJpaHibernateApplication;

@SpringBootTest(classes = SpringJpaHibernateApplication.class)
class StudentRepositoryTest {

	Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	StudentRepository repository;

	@Test
	@DirtiesContext
	@Transactional
	void saveStudentWithPassport() {
		repository.saveStudentWithPassport();
	}

}
