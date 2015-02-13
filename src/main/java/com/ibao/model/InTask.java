package com.ibao.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="intask")
public class InTask implements Serializable{

	private static final long serialVersionUID = -3148615461920963515L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(name="task", unique=true)
	private Integer task;
	
	@Column(name="url")
	private String url;
	
	@Column(name="code")
	private String code;
	
	@Column(name="state")
	private Integer state;
	
	@Column(name="flag")
	private Integer flag;
	
	@Column(name="videoId")
	private String videoId;
	
	@Column(name="videoId2")
	private String videoId2;
	
	@Column(name="playmode")
	private Integer playmode;
	
	@Column(name="catId")
	private Integer catId;
	
	@Column(name="showId")
	private Integer showId;
	
	@Column(name="sort")
	private Integer sort;
	
	@Column(name="server")
	private String server;
	
	@Column(name="begin")
	private Integer begin;
	
	@Column(name="end")
	private Integer end;

	public Integer getBegin() {
		return begin;
	}

	public void setBegin(Integer begin) {
		this.begin = begin;
	}

	public Integer getEnd() {
		return end;
	}

	public void setEnd(Integer end) {
		this.end = end;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTask() {
		return task;
	}

	public void setTask(Integer task) {
		this.task = task;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
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

	public Integer getPlaymode() {
		return playmode;
	}

	public void setPlaymode(Integer playmode) {
		this.playmode = playmode;
	}

	public Integer getCatId() {
		return catId;
	}

	public void setCatId(Integer catId) {
		this.catId = catId;
	}

	public Integer getShowId() {
		return showId;
	}

	public void setShowId(Integer showId) {
		this.showId = showId;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}
}
