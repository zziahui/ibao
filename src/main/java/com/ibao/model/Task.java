package com.ibao.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="task")
public class Task implements Serializable{

	private static final long serialVersionUID = -8586335661751631573L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(name="url", nullable=false)
	private String url;
	

	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user", referencedColumnName="id")
	private User user;
	
	@OneToOne
	@JoinColumn(name="code", referencedColumnName="code")
	private Style style;
	
	//0 未开始 1 运行中 2已结束
	@Column(name="total", nullable=false)
	private Integer total;
	
	@Column(name="begin", nullable=false)
	private Integer begin;
	
	@Column(name="end", nullable=false)
	private Integer end;
	
	@Column(name="beginTime")
	private Date beginTime;
	
	@Column(name="endTime")
	private Date endTime;
	
	@Column(name="state")
	private Integer state;
	
	@Column(name="addTime")
	private Date addTime;
	
	@Column(name="server")
	private String server;

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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Style getStyle() {
		return style;
	}

	public void setStyle(Style style) {
		this.style = style;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

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

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}
}

