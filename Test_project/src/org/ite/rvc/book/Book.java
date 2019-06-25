package org.ite.rvc.book;

import java.io.InputStream;

public class Book {
	private Integer book_id;
	private String author;
	private String title;
	private String publisher;
	private String categories_name;
	private InputStream image_url;
	private String detail;
	
	private String bookdate;
	private boolean executionResult;
	private String owner;
	private Integer categories_id;
	

	public Integer getCategories_id() {
		return categories_id;
	}

	public void setCategories_id(Integer categories_id) {
		this.categories_id = categories_id;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public Integer getBook_id() {
		return book_id;
	}

	public void setBook_id(Integer book_id) {
		this.book_id = book_id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getCategories_name() {
		return categories_name;
	}

	public void setCategories_name(String categories_name) {
		this.categories_name = categories_name;
	}

	public InputStream getImage_url() {
		return image_url;
	}

	public void setImage_url(InputStream image_url) {
		this.image_url = image_url;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}



	public String getBookdate() {
		return bookdate;
	}

	public void setBookdate(String bookdate) {
		this.bookdate = bookdate;
	}

	public boolean isExecutionResult() {
		return executionResult;
	}

	public void setExecutionResult(boolean executionResult) {
		this.executionResult = executionResult;
	}


}
