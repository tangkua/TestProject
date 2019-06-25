package org.ite.rvc.chapter;

public class ChapterUser {
	private Integer user_id;
	private Integer chapter_id;
	private Integer favorite_chapter;
	private boolean executeResult;
	private Integer book_id;
	private String pin_chapter;
	
	
	public String getPin_chapter() {
		return pin_chapter;
	}
	public void setPin_chapter(String pin_chapter) {
		this.pin_chapter = pin_chapter;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public Integer getChapter_id() {
		return chapter_id;
	}
	public void setChapter_id(Integer chapter_id) {
		this.chapter_id = chapter_id;
	}
	public Integer getFavorite_chapter() {
		return favorite_chapter;
	}
	public void setFavorite_chapter(Integer favorite_chapter) {
		this.favorite_chapter = favorite_chapter;
	}
	public boolean isExecuteResult() {
		return executeResult;
	}
	public void setExecuteResult(boolean executeResult) {
		this.executeResult = executeResult;
	}
	public Integer getBook_id() {
		return book_id;
	}
	public void setBook_id(Integer book_id) {
		this.book_id = book_id;
	}
	
	
	
}
