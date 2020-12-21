package za.co.iherridge0.jpa.hibernate.springjpahibernate.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import za.co.iherridge0.jpa.hibernate.springjpahibernate.SpringJpaHibernateApplication;
import za.co.iherridge0.jpa.hibernate.springjpahibernate.entity.Course;

@SpringBootTest(classes = SpringJpaHibernateApplication.class)
public class CriteriaQueryTest {

	Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EntityManager em;

	@Test
	public void all_courses() {
		// We want to do the following query:
		// Select c From Course c

		// 1. Use Criteria Builder to create a Criteria Query returning the expected
		// result object

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);

		// 2. Define roots for tables which are involved in the query

		Root<Course> courseRoot = cq.from(Course.class);

		// 3. Define Predicates etc using Criteria Builder

		// 4. Add Predicates etc to the Criteria Query

		// 5. Build the TypedQuery using the entity manager and criteria query
		TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
		List<Course> resultList = query.getResultList();
		log.info("all_courses -> {}", resultList);

		/*
		 * OUTPUT Typed Query -> [Course [name=JPA in 50 Steps], Course [name=JPA in 86
		 * Steps], Course [name=JPA in 100 Steps]]
		 */
	}

	@Test
	public void all_courses_having_100Steps() {
		// We want to do the following query:
		// Select c From Course c Where c name like '%100 Steps'

		// 1. Use Criteria Builder to create a Criteria Query returning the expected
		// result object

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);

		// 2. Define roots for tables which are involved in the query

		Root<Course> courseRoot = cq.from(Course.class);

		// 3. Define Predicates etc using Criteria Builder
		Predicate like100Steps = cb.like(courseRoot.get("name"), "%100 Steps");

		// 4. Add Predicates etc to the Criteria Query
		cq.where(like100Steps);

		// 5. Build the TypedQuery using the entity manager and criteria query
		TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
		List<Course> resultList = query.getResultList();
		log.info("all_courses_having_100Steps -> {}", resultList);

	}

	@Test
	public void all_courses_without_students() {
		// We want to do the following query:
		// Select c From Course c where c.students is empty

		// 1. Use Criteria Builder to create a Criteria Query returning the expected
		// result object

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);

		// 2. Define roots for tables which are involved in the query

		Root<Course> courseRoot = cq.from(Course.class);

		// 3. Define Predicates etc using Criteria Builder
		Predicate studentsIsEmpty = cb.isEmpty(courseRoot.get("students"));

		// 4. Add Predicates etc to the Criteria Query
		cq.where(studentsIsEmpty);

		// 5. Build the TypedQuery using the entity manager and criteria query
		TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
		List<Course> resultList = query.getResultList();
		log.info("all_courses_without_students -> {}", resultList);

	}

	@Test
	public void join() {
		// We want to do the following query:
		// Select c From Course c join c.students s

		// 1. Use Criteria Builder to create a Criteria Query returning the expected
		// result object

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);

		// 2. Define roots for tables which are involved in the query
		Root<Course> courseRoot = cq.from(Course.class);

		// 3. Define Predicates etc using Criteria Builder
		Join<Object, Object> join = courseRoot.join("students");

		// 4. Add Predicates etc to the Criteria Query

		// 5. Build the TypedQuery using the entity manager and criteria query
		TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
		List<Course> resultList = query.getResultList();
		log.info("all_courses_without_students -> {}", resultList);

		/*
		 * OUTPUT Typed Query -> [Course [name=JPA in 50 Steps], Course [name=JPA in 86
		 * Steps], Course [name=JPA in 100 Steps]]
		 */
	}

	@Test
	public void left_join() {
		// We want to do the following query:
		// Select c From Course c left join c.students s

		// 1. Use Criteria Builder to create a Criteria Query returning the expected
		// result object
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);

		// 2. Define roots for tables which are involved in the query
		Root<Course> courseRoot = cq.from(Course.class);

		// 3. Define Predicates etc using Criteria Builder
		Join<Object, Object> join = courseRoot.join("students", JoinType.LEFT);

		// 4. Add Predicates etc to the Criteria Query

		// 5. Build the TypedQuery using the entity manager and criteria query
		TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
		List<Course> resultList = query.getResultList();
		log.info("all_courses_without_students -> {}", resultList);

		/*
		 * OUTPUT Typed Query -> [Course [name=JPA in 50 Steps], Course [name=JPA in 86
		 * Steps], Course [name=JPA in 100 Steps]]
		 */
	}

}
