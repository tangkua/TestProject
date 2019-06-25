package org.ite.rvc.audio;

public class AudioUser {
	private Integer user_id;
	private Integer audio_id;
	private Integer favorite_audio;
	private boolean executeResult;
	private String pin_audio;
	
	public String getPin_audio() {
		return pin_audio;
	}
	public void setPin_audio(String pin_audio) {
		this.pin_audio = pin_audio;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public Integer getAudio_id() {
		return audio_id;
	}
	public void setAudio_id(Integer audio_id) {
		this.audio_id = audio_id;
	}
	public Integer getFavorite_audio() {
		return favorite_audio;
	}
	public void setFavorite_audio(Integer favorite_audio) {
		this.favorite_audio = favorite_audio;
	}
	public boolean isExecuteResult() {
		return executeResult;
	}
	public void setExecuteResult(boolean executeResult) {
		this.executeResult = executeResult;
	}
	
}
