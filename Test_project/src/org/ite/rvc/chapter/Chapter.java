package org.ite.rvc.chapter;

import java.io.InputStream;

public class Chapter {
	private Integer chapter_id;
	private String chapter_name;
	private String chapter_description;
	private long chapter_size;
	private Integer count_view;
	private String chapter_datetime;
	private InputStream chapter_path;
	private Integer user_id;
	private Integer book_id;
	private boolean executionResult;
	
	
	public boolean isExecutionResult() {
		return executionResult;
	}
	public void setExecutionResult(boolean executionResult) {
		this.executionResult = executionResult;
	}
	public Integer getChapter_id() {
		return chapter_id;
	}
	public void setChapter_id(Integer chapter_id) {
		this.chapter_id = chapter_id;
	}
	public String getChapter_name() {
		return chapter_name;
	}
	public void setChapter_name(String chapter_name) {
		this.chapter_name = chapter_name;
	}
	public String getChapter_description() {
		return chapter_description;
	}
	public void setChapter_description(String chapter_description) {
		this.chapter_description = chapter_description;
	}
	public long getChapter_size() {
		return chapter_size;
	}
	public void setChapter_size(long l) {
		this.chapter_size = l;
	}
	public Integer getCount_view() {
		return count_view;
	}
	public void setCount_view(Integer count_view) {
		this.count_view = count_view;
	}
	public String getChapter_datetime() {
		return chapter_datetime;
	}
	public void setChapter_datetime(String chapter_datetime) {
		this.chapter_datetime = chapter_datetime;
	}
	public InputStream getChapter_path() {
		return chapter_path;
	}
	public void setChapter_path(InputStream chapter_path) {
		this.chapter_path = chapter_path;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public Integer getBook_id() {
		return book_id;
	}
	public void setBook_id(Integer book_id) {
		this.book_id = book_id;
	}
	
	
}
 