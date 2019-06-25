package org.ite.rvc.audio;

import java.io.InputStream;

public class Audio {

	private Integer id;
	private Integer chapter_id;
	private Integer user_id;
	private InputStream fileaudio;
	private String name;
	private long size;
	private String detail;
	private Integer categories_id;
	private String audiodate;
	private String countview;
	private String countfavorite;
	private boolean executionResult;

	public boolean isExecutionResult() {
		return executionResult;
	}

	public void setExecutionResult(boolean executionResult) {
		this.executionResult = executionResult;
	}

	public String getCountview() {
		return countview;
	}

	public void setCountview(String countview) {
		this.countview = countview;
	}

	public String getCountfavorite() {
		return countfavorite;
	}

	public void setCountfavorite(String countfavorite) {
		this.countfavorite = countfavorite;
	}

	public String getAudiodate() {
		return audiodate;
	}

	public void setAudiodate(String audiodate) {
		this.audiodate = audiodate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getChapter_id() {
		return chapter_id;
	}

	public void setChapter_id(Integer chapter_id) {
		this.chapter_id = chapter_id;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public InputStream getFileaudio() {
		return fileaudio;
	}

	public void setFileaudio(InputStream fileaudio) {
		this.fileaudio = fileaudio;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long l) {
		this.size = l;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Integer getCategories_id() {
		return categories_id;
	}

	public void setCategories_id(Integer categories_id) {
		this.categories_id = categories_id;
	}

}
