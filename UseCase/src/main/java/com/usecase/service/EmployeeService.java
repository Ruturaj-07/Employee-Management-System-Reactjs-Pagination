package com.usecase.service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;
import com.usecase.model.Employee;
import com.usecase.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	//get all the employees
	public List<Employee> getAllEmployees(){
		List<Employee> employees = employeeRepository.findAll(); 
		return employees;
	}

	public Page<Employee> getEmployeeWithPegination(int pageNumber,int pageSize){
		Page<Employee> employee = employeeRepository.findAll(PageRequest.of(pageNumber, pageSize));
		return employee;
	}

	//get
	public Employee getEmployee(int empId){
		return employeeRepository.findById(empId).get();
	}

	//add new employee
	public int addEmployee(Employee employee) {
		employeeRepository.save(employee);
		return 1;
	}

	//update employee by id
	public boolean updateEmployee(Employee employee, int empId){
		Employee update=employeeRepository.findById(empId).get();
		if(empId == employee.getEmpId()) {
			update.setEmpId(employee.getEmpId());
			update.setEmpName(employee.getEmpEmail());
			update.setEmpAge(employee.getEmpAge());
			update.setEmpEmail(employee.getEmpEmail());
			update.setEmpSal(employee.getEmpSal());
			update.setJoiningDate(employee.getJoiningDate());
			employeeRepository.save(employee);
			return true;
		}
		return false;
	}

	//delete employee by id
	public boolean deleteEmployeeByID(int empId){
		employeeRepository.deleteById(empId);
		return true;
	}

	public boolean deleteAllEmployees(){
		employeeRepository.deleteAll();
		return true;
	}

	public List<Employee> sortByEmpNameAsc(){
		List<Employee> employees = getAllEmployees();
		return employees.stream().sorted(Comparator.comparing(Employee::getEmpName)).collect(Collectors.toList());
	}


	public List<Employee> sortByEmpNameDesc(){
		List<Employee> employees = getAllEmployees();
		return employees.stream().sorted(Comparator.comparing(Employee::getEmpName).reversed()).collect(Collectors.toList());
	}

//	public Page<Employee> getEmployeeByPages(Pageable pageable,int page,int size){
//		
//		return employeeRepository.findAll(pageable);
//	}

}
