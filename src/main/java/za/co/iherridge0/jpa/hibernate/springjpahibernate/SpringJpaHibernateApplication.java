package za.co.iherridge0.jpa.hibernate.springjpahibernate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import za.co.iherridge0.jpa.hibernate.springjpahibernate.entity.Course;
import za.co.iherridge0.jpa.hibernate.springjpahibernate.repository.CourseRepository;

@SpringBootApplication
public class SpringJpaHibernateApplication implements CommandLineRunner {

	Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	CourseRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(SpringJpaHibernateApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Course course = repository.findById(10001L);
		log.info("{}", course.toString());

		repository.save(new Course("Microservices in 100 steps"));
	}

}
