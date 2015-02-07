package com.ibao.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="style")
public class Style implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(name="clazz1", nullable=false)
	private Integer clazz1;//如1代表视频类 2代表其他新闻
	
	@Column(name="clazz2", nullable=false)
	private Integer clazz2;//如1代表刷量 2代表刷赞等
	
	@Column(name="series", nullable=false)
	private Integer group; //如1 代表优酷 2 代表 土豆
	
	@Column(name="name", nullable=false)
	private String name;
	
	@Column(name="comment", nullable=false)
	private String comment;

	@Column(name="code", nullable=false)
	private String code;  //唯一键

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getClazz1() {
		return clazz1;
	}

	public void setClazz1(Integer clazz1) {
		this.clazz1 = clazz1;
	}

	public Integer getClazz2() {
		return clazz2;
	}

	public void setClazz2(Integer clazz2) {
		this.clazz2 = clazz2;
	}

	public Integer getGroup() {
		return group;
	}

	public void setGroup(Integer group) {
		this.group = group;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
}
