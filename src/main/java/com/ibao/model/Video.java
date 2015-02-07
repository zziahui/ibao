package com.ibao.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="video")
public class Video implements Serializable{

	private static final long serialVersionUID = 8358415214978387320L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(name="url", unique=true, nullable=false)
	private String url;
	
	@Column(name="videoId")
	private String videoId;
	
	@Column(name="videoId2")
	private String videoId2;
	
	@Column(name="catId")
	private Integer catId;
	
	@Column(name="playmode")
	private Integer playmode;
	
	@Column(name="showId")
	private Integer showId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getVideoId() {
		return videoId;
	}

	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}

	public String getVideoId2() {
		return videoId2;
	}

	public void setVideoId2(String videoId2) {
		this.videoId2 = videoId2;
	}

	public Integer getCatId() {
		return catId;
	}

	public void setCatId(Integer catId) {
		this.catId = catId;
	}

	public Integer getPlaymode() {
		return playmode;
	}

	public void setPlaymode(Integer playmode) {
		this.playmode = playmode;
	}

	public Integer getShowId() {
		return showId;
	}

	public void setShowId(Integer showId) {
		this.showId = showId;
	}
}
