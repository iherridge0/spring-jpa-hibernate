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
public class JPQLTest {

	Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EntityManager em;

	@Test
	public void jpql_basic() {
		Query query = em.createQuery("Select c From Course c");
		List resultList = query.getResultList();
		log.info("Select c From Course c -> {}", resultList);
	}

	@Test
	public void jpql_typed() {
		TypedQuery<Course> query = em.createQuery("Select c From Course c", Course.class);
		List<Course> resultList = query.getResultList();
		log.info("Select c From Course c -> {}", resultList);
	}

	@Test
	public void jpql_where() {
		TypedQuery<Course> query = em.createQuery("Select c From Course c where name like '%100 Steps'", Course.class);
		List<Course> resultList = query.getResultList();
		log.info("Select c From Course c -> {}", resultList);
	}

	@Test
	public void jpql_basic_named_query() {
		Query query = em.createNamedQuery("query_get_all_courses");
		List resultList = query.getResultList();
		log.info("Named Query: query_get_all_courses -> {}", resultList);
	}

	@Test
	public void jpql_where_named_query() {
		TypedQuery<Course> query = em.createNamedQuery("query_where_clause", Course.class);
		List<Course> resultList = query.getResultList();
		log.info("Named Query: query_where_clause -> {}", resultList);
	}

	@Test
	public void jpql_courses_without_students() {

		TypedQuery<Course> createQuery = em.createQuery("Select c From Course c where c.students is empty",
				Course.class);
		List<Course> list = createQuery.getResultList();
		log.info("jpql_courses_without_students -> {}", list);

	}

	@Test
	public void jpql_courses_with_atleast_2students() {

		TypedQuery<Course> createQuery = em.createQuery("Select c From Course c where size(c.students) >= 2",
				Course.class);
		List<Course> list = createQuery.getResultList();
		log.info("jpql_courses_with_atleast_2students -> {}", list);

	}

	@Test
	public void jpql_courses_ordered_by_students() {

		TypedQuery<Course> createQuery = em.createQuery("Select c From Course c order by size(c.students) desc",
				Course.class);
		List<Course> list = createQuery.getResultList();
		log.info("jpql_courses_ordered_by_students -> {}", list);

	}

}
