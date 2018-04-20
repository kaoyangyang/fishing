package com.sdtz.adlet.entity;

/**
 * Job entity. @author MyEclipse Persistence Tools
 */

public class Job implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private Integer fid;

	// Constructors

	/** default constructor */
	public Job() {
	}

	/** minimal constructor */
	public Job(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Job(Integer id, String name, Integer fid) {
		this.id = id;
		this.name = name;
		this.fid = fid;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getFid() {
		return this.fid;
	}

	public void setFid(Integer fid) {
		this.fid = fid;
	}

}