package model;

import javax.persistence.*;
@Entity
public class Course {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;


	private String title;

	private String description;

	private boolean published;

	@Override
	public String toString() {
		return "Course [id=" + id + ", title=" + title + ", description=" + description + ", published=" + published
				+ "]";
	}
	public Course() {
		System.out.println("/n/n/n/ncourse mai hun");

	}

	public Course( String title, String description, boolean published) {
		this.title = title;
		this.description = description;
		this.published = published;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isPublished() {
		return published;
	}

	public void setPublished(boolean published) {
		this.published = published;
	}

}

