package za.co.iherridge0.jpa.hibernate.springjpahibernate.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import za.co.iherridge0.jpa.hibernate.springjpahibernate.SpringJpaHibernateApplication;
import za.co.iherridge0.jpa.hibernate.springjpahibernate.entity.Course;

@SpringBootTest(classes = SpringJpaHibernateApplication.class)
class NativeQueriesTest {

	Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EntityManager em;

	@Test
	void native_queries_basic() {
		List<Course> resultList = em.createNativeQuery("SELECT * FROM COURSE where is_deleted=0", Course.class)
				.getResultList();
		log.info("SELECT * FROM COURSE -> {}", resultList);
	}

	@Test
	void native_queries_with_parameters() {
		Query createNativeQuery = em.createNativeQuery("SELECT * FROM COURSE WHERE id = ?", Course.class);
		createNativeQuery.setParameter(1, 10001L);
		List<Course> resultList = createNativeQuery.getResultList();
		log.info("SELECT * FROM COURSE WHERE id = " + 10001L + " -> {}", resultList);
	}

	@Test
	void native_queries_with_named_parameters() {
		Query createNativeQuery = em.createNativeQuery("SELECT * FROM COURSE WHERE id = :id", Course.class);
		createNativeQuery.setParameter("id", 10001L);
		List<Course> resultList = createNativeQuery.getResultList();
		log.info("NAMED PARAMETER: SELECT * FROM COURSE WHERE id = " + 10001L + " -> {}", resultList);
	}

	@Test
	@Transactional // because we are deleting from the database, it needs to be transactional
	void native_queries_to_update() {
		Query query = em.createNativeQuery("UPDATE course SET LAST_UPDATED_DATE=sysdate() ");
		int noOfRowsUpdated = query.executeUpdate();
		log.info("Number of rows updated -> {}", noOfRowsUpdated);
	}

}
