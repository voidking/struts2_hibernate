package com.voidking.struts2_hibernate.model;
// default package
// Generated 2015-5-23 0:27:57 by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Xsb generated by hbm2java
 */
public class Xsb implements java.io.Serializable {

	private String xh;
	private String xm;
	private boolean xb;
	private Date cssj;
	private Zyb zyb;
	private Integer zxf;
	private String bz;
	private byte[] zp;
	private Set kcs= new HashSet();

	public Xsb() {
	}

	public Xsb(String xh, String xm, boolean xb) {
		this.xh = xh;
		this.xm = xm;
		this.xb = xb;
	}

	public String getXh() {
		return this.xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getXm() {
		return this.xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	public boolean isXb() {
		return this.xb;
	}

	public void setXb(boolean xb) {
		this.xb = xb;
	}

	public Date getCssj() {
		return this.cssj;
	}

	public void setCssj(Date cssj) {
		this.cssj = cssj;
	}

	public Zyb getZyb() {
		return zyb;
	}

	public void setZyb(Zyb zyb) {
		this.zyb = zyb;
	}

	public Integer getZxf() {
		return this.zxf;
	}

	public void setZxf(Integer zxf) {
		this.zxf = zxf;
	}

	public String getBz() {
		return this.bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public byte[] getZp() {
		return this.zp;
	}

	public void setZp(byte[] zp) {
		this.zp = zp;
	}

	public Set getKcs() {
		return kcs;
	}

	public void setKcs(Set kcs) {
		this.kcs = kcs;
	}

}
