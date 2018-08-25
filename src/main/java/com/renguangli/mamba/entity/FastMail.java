package com.renguangli.mamba.entity;

/**
 * 快递实体类
 * @author renguangli
 * @since jdk1.8
 */
public class FastMail {

	private Integer id;
	
	private String name;
	
	private String code;

	/**
	 * Create a new instance of FastMail
	 */
	public FastMail() {
		super();
	}

	/**
	 * Create a new instance of FastMail
	 */
	public FastMail(Integer id, String name, String code) {
		super();
		this.id = id;
		this.name = name;
		this.code = code;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FastMail [id=" + id + ", name=" + name + ", code=" + code + "]";
	}
	
	
}
