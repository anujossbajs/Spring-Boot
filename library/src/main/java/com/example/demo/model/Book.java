package com.example.demo.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Book {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	

	public String bookname;
	public String authorname;
	public String email;
	
	public void setBookName(String bookname) {
		this.bookname=bookname;
	}
	public String getBookName() {
		return bookname;
	}
	
	public void setAuthorName(String authorname) {
		this.authorname=authorname;
	}
	public String getAuthorName() {
		return authorname;
	}
	public void setEmail(String email) {
		this.email=email;
	}
	public String getEmail() {
		return email;
	}
	public Integer getId() {
		return id;
	}
	
}