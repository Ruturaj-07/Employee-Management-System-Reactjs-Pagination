package com.usecase.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "Employee")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Max(value = 10000, message = "Employee Id Can't Be More Than 10000")
	@Column(name = "employee_id")
	private int empId;

	@NotEmpty(message = "Employee Name Should Not Be Blank")
	@Size(min = 3,message = "Employee Name Should Be At Least 3 Chars")
	@Column(name = "employee_name")
	private String empName;

	@NotNull(message = "Employee Age Should Not Be Blank")
	@Min(value = 18, message = "Employee Age Can't Be Less Than 18")
	@Max(value = 60, message = "Employee Age Can't Be More Than 60")
	@Column(name = "employee_age")
	private int empAge;

	@NotNull(message = "Employee Salary Should Not Be Blank")
	@Min(value = 10000, message = "Employee Salary Should Not Be Less Than 10000")
	@Digits(fraction = 0, integer = 9)
	@Column(name = "employee_salary")
	private double empSal;
	
	@NotEmpty(message = "Employee Email Should Not Be Blank")
	@Column(name = "employee_email")
	@Email(message = "Email Should Contain Valid Extention")
	private String empEmail;
	
	@PastOrPresent
	@NotNull(message = "Employee Joining Date Should Not Be Blank")
	@JsonFormat(pattern = "yyyy-mm-dd")
	@Column(name = "employee_joining_date")
	private Date joiningDate;

	@ManyToOne
	@JoinColumn(name = "department_id")
	private Department department;
	
//	As stated in problem, to solve this error you MUST use correct annotations. 
//	In above problem, @NotBlank or @NotEmpty annotation must be applied on any String field only.
//	To validate long type field, use annotation @NotNull.

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public int getEmpAge() {
		return empAge;
	}

	public void setEmpAge(int empAge) {
		this.empAge = empAge;
	}

	public double getEmpSal() {
		return empSal;
	}

	public void setEmpSal(double empSal) {
		this.empSal = empSal;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getEmpEmail() {
		return empEmail;
	}

	public void setEmpEmail(String empEmail) {
		this.empEmail = empEmail;
	}

	public Date getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(Date joiningDate) {
		this.joiningDate = joiningDate;
	}

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", empName=" + empName + ", empAge=" + empAge + ", empSal=" + empSal
				+ ", empEmail=" + empEmail + ", joiningDate=" + joiningDate + ", department=" + department + "]";
	}
	

}
