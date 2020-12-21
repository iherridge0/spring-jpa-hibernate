package za.co.iherridge0.jpa.hibernate.springjpahibernate.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Review {

	@Id
	@GeneratedValue
	private Long id;

	// The ordinal for ONE is 1, TWO is 2 THREE is 3 - by default

	// @Enumerated
	// THE ordinal for TYPEB is 1, TYPEA is 2, TYPEC is 3 - it would be better to
	// use String
	// So the ordinal for TYPEB is TYPEB, TYPEA is TYPEA and TYPEC is TYPEC
	@Enumerated(EnumType.STRING)
	private ReviewRating rating;

	private String description;

	@ManyToOne // always eager fetching
	private Course course;

	protected Review() {

	}

	public Review(String description, ReviewRating rating) {
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

	public ReviewRating getRating() {
		return rating;
	}

	public void setRating(ReviewRating rating) {
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
