package com.guitar.db.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "Dept")
public class Department {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private BigDecimal deptNo;

	private String dname;
	private String loc;

	public BigDecimal getDeptNo() {
		return deptNo;
	}

	public void setDeptNo(BigDecimal deptNo) {
		this.deptNo = deptNo;
	}

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public String getLoc() {
		return loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}

	@Override
	public String toString() {
		return "Department [deptNo=" + deptNo + ", dname=" + dname + ", loc=" + loc + "]";
	}

}
