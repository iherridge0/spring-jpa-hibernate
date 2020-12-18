package za.co.iherridge0.jpa.hibernate.springjpahibernate.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Review {

	@Id
	@GeneratedValue
	private Long id;

	private String rating;

	private String description;

	@ManyToOne // always eager fetching
	private Course course;

	protected Review() {

	}

	public Review(String description, String rating) {
		super();
		this.rating = rating;
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	@Override
	public String toString() {
		return "Review [rating=" + rating + ", description=" + description + "]";
	}

}
