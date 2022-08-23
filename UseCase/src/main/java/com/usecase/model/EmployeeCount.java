package com.usecase.model;

public class EmployeeCount {
	
	private int dept;
	private int empCount;
	
	public int getDept() {
		return dept;
	}
	public void setDept(int dept) {
		this.dept = dept;
	}
	public int getEmpCount() {
		return empCount;
	}
	public void setEmpCount(int empCount) {
		this.empCount = empCount;
	}
	
	@Override
	public String toString() {
		return "EmployeeCount [dept=" + dept + ", empCount=" + empCount + "]";
	}
	
}
