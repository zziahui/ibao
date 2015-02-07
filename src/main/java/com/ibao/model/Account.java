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
@Table(name="account")
public class Account implements Serializable{



	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="user", referencedColumnName="id")
	private User user;
	
	@Column(name="total")
	private Integer total;
	
	@Column(name="locked")
	private Integer locked;
	
	@Column(name="code")
	private String code;
	
	@Column(name="series")
	private Integer group;
	
	@Column(name="clazz1")
	private Integer clazz1;
	
	@Column(name="clazz2")
	private Integer clazz2;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="createBy", referencedColumnName="id")
	private User createBy;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="updateBy", referencedColumnName="id")
	private User updateBy;
	
	@Column(name="createdDate")
	private Date createdDate;
	
	@Column(name="updatedDate")
	private Date updatedDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getLocked() {
		return locked;
	}

	public void setLocked(Integer locked) {
		this.locked = locked;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getGroup() {
		return group;
	}

	public void setGroup(Integer group) {
		this.group = group;
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

	public User getCreateBy() {
		return createBy;
	}

	public void setCreateBy(User createBy) {
		this.createBy = createBy;
	}

	public User getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(User updateBy) {
		this.updateBy = updateBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
}
