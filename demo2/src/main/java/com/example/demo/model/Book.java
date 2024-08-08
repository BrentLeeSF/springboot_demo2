package com.example.demo.model;

import jakarta.persistence.*; // for columns, generatedValue, table, entity
/**
 * Jakarta Persistence defines a standard for management of persistence and object/relational mapping in Java(R) environments.
 * 
 * https://jakarta.ee/specifications/persistence/3.2/
 * 
 */
@Entity
@Table(name = "books")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "title")
	private String title;

	@Column(name = "description")
	private String description;

	@Column(name = "publisher")
	private String publisher;

	public Book() {

	}

	public Book(String title, String description, String publisher) {
		this.title = title;
		this.description = description;
		this.publisher = publisher;
	}

	public long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

    public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", desc=" + description + ", publisher=" + publisher + "]";
	}
}
