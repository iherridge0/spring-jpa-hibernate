package za.co.iherridge0.jpa.hibernate.springjpahibernate.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import za.co.iherridge0.jpa.hibernate.springjpahibernate.SpringJpaHibernateApplication;
import za.co.iherridge0.jpa.hibernate.springjpahibernate.entity.Course;

@SpringBootTest(classes = SpringJpaHibernateApplication.class)
class JPQLTest {

	Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EntityManager em;

	@Test
	void jpql_basic() {
		Query query = em.createQuery("Select c From Course c");
		List resultList = query.getResultList();
		log.info("Select c From Course c -> {}", resultList);
	}

	@Test
	void jpql_typed() {
		TypedQuery<Course> query = em.createQuery("Select c From Course c", Course.class);
		List<Course> resultList = query.getResultList();
		log.info("Select c From Course c -> {}", resultList);
	}

	@Test
	void jpql_where() {
		TypedQuery<Course> query = em.createQuery("Select c From Course c where name like '%100 Steps'", Course.class);
		List<Course> resultList = query.getResultList();
		log.info("Select c From Course c -> {}", resultList);
	}

}
