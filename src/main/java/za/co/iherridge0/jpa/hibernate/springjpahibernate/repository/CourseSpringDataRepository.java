package za.co.iherridge0.jpa.hibernate.springjpahibernate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import za.co.iherridge0.jpa.hibernate.springjpahibernate.entity.Course;

public interface CourseSpringDataRepository extends JpaRepository<Course, Long> {

}
