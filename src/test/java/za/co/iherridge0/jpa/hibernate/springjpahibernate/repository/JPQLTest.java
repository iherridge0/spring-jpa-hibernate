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
import za.co.iherridge0.jpa.hibernate.springjpahibernate.entity.Student;

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

	@Test
	public void jpql_students_with_passports_in_a_certain_pattern() {

		TypedQuery<Student> createQuery = em.createQuery("Select s From Student s Where s.passport.number like '%123%'",
				Student.class);
		List<Student> list = createQuery.getResultList();
		log.info("jpql_students_with_passports_in_a_certain_pattern -> {}", list);

		// like
		// BETWEEEN 100 and 1000
		// IS NULL
		// upper, lower, trim, lenght

	}

	// JOIN => Select c, s from Course c JOIN c.students s
	// -> If there are courses without students the JOIN will not return those
	// courses
	@Test
	public void join() {
		Query createQuery = em.createQuery("Select c, s from Course c JOIN c.students s");
		List<Object[]> resultList = createQuery.getResultList();
		log.info("join result size -> {}", resultList.size());

		for (Object[] result : resultList) {
			log.info("{} {}", result[0], result[1]);
		}
		// join result size -> 4
		// Course [name=JPA in 50 Steps] Student [name=John]
		// Course [name=JPA in 50 Steps] Student [name=Pieter]
		// Course [name=JPA in 50 Steps] Student [name=Jackson]
		// Course [name=JPA in 100 Steps] Student [name=John]
	}

	// LEFT JOIN => Select c, s from Course c LEFT JOIN c.students s
	@Test
	public void left_join() {
		Query createQuery = em.createQuery("Select c, s from Course c LEFT JOIN c.students s");
		List<Object[]> resultList = createQuery.getResultList();
		log.info("left join result size -> {}", resultList.size());

		for (Object[] result : resultList) {
			log.info("{} {}", result[0], result[1]);
		}
		// left join result size -> 5
		// Course [name=JPA in 50 Steps] Student [name=John]
		// Course [name=JPA in 50 Steps] Student [name=Pieter]
		// Course [name=JPA in 50 Steps] Student [name=Jackson]
		// Course [name=JPA in 86 Steps] null
		// Course [name=JPA in 100 Steps] Student [name=John]
	}

	// CROSS JOIN => Select c, s from Course c, Student s
	@Test
	public void cross_join() {
		Query createQuery = em.createQuery("Select c, s from Course c, Student s");
		List<Object[]> resultList = createQuery.getResultList();
		log.info("cross join result size -> {}", resultList.size());

		for (Object[] result : resultList) {
			log.info("{} {}", result[0], result[1]);
		}
		// cross join result size -> 9
		// Course [name=JPA in 50 Steps] Student [name=John]
		// Course [name=JPA in 50 Steps] Student [name=Pieter]
		// Course [name=JPA in 50 Steps] Student [name=Jackson]
		// Course [name=JPA in 86 Steps] Student [name=John]
		// Course [name=JPA in 86 Steps] Student [name=Pieter]
		// Course [name=JPA in 86 Steps] Student [name=Jackson]
		// Course [name=JPA in 100 Steps] Student [name=John]
		// Course [name=JPA in 100 Steps] Student [name=Pieter]
		// Course [name=JPA in 100 Steps] Student [name=Jackson]
	}

}
