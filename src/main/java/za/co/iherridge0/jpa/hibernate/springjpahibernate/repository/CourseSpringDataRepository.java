package za.co.iherridge0.jpa.hibernate.springjpahibernate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import za.co.iherridge0.jpa.hibernate.springjpahibernate.entity.Course;

@RepositoryRestResource(path = "courses")
public interface CourseSpringDataRepository extends JpaRepository<Course, Long> {

	List<Course> findByName(String name);

	// Multiple fields, Spring Data JPA will look at method name findBy*, deleteBy*,
	// InsertBy*
	List<Course> findByNameAndId(String name, long id);

	List<Course> countByName(String name);

	List<Course> findByNameOrderByIdDesc(String name);

	List<Course> deleteByName(String name);

	@Query("Select c From Course c where name like '%100'")
	List<Course> courseWith100StepsInName();

	@Query(value = "Select * From Course where name like '%100 Steps'", nativeQuery = true)
	List<Course> courseWith100StepsInNameUsingNativeQuery();

	@Query(name = "query_get_all_courses")
	List<Course> courseWith100StepsInNameUsingNamedQuery();
}
