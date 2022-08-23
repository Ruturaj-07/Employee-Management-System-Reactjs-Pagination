package com.usecase.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.usecase.exception.ResourceNotFoundException;
import com.usecase.model.Employee;
import com.usecase.model.EmployeeCount;
import com.usecase.repository.EmployeeRepository;
import com.usecase.service.EmployeeCountService;
//import com.usecase.service.EmployeeCountService;
import com.usecase.service.EmployeeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/employee")
@CrossOrigin("http://localhost:3000/")
@Api(value = "Employee Resources", description = "All operation related to Employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private EmployeeCountService employeeCountService;

	@Autowired
	private EmployeeRepository employeeRepository;

	@ApiOperation(value = "Get All the Employees")
	@GetMapping("/get")
	public ResponseEntity<List<Employee>> getAllEmployee() {
		List<Employee> list = employeeService.getAllEmployees();
		try {
			if (list.size() <= 0) {
				throw new ResourceNotFoundException("Employee List is Epty");
			}
		}
		catch(ResourceNotFoundException ex) {
			ex.getMessage();
		}
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}

	@ApiOperation(value = "Get All the Employees")
	@GetMapping("/get/{pageNumber}/{pageSize}")
	public ResponseEntity<Page<Employee>> getEmployeeWithPagination(@PathVariable int pageNumber,@PathVariable int pageSize){

		Page<Employee> list = employeeService.getEmployeeWithPegination(pageNumber, pageSize);
		if (list.getSize() <= 0)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

		return ResponseEntity.status(HttpStatus.OK).body(list);
	}

	@ApiOperation(value = "Get Employees By Id")
	@GetMapping("/get/{id}")
	public ResponseEntity<Employee> getEmployee(@PathVariable ("id") int empId){
		System.out.println(empId);
		Employee emp = employeeService.getEmployee(empId);
		System.out.println(emp);
		if(emp == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

		return ResponseEntity.status(HttpStatus.OK).body(emp);
	}

	@ApiOperation(value = "Add New Employee")
	@PostMapping("/add")
	public ResponseEntity<String> addEmployees(@RequestBody Employee employee) {
		try {

			int num=employeeService.addEmployee(employee);
			if(num != 0)
				return ResponseEntity.status(HttpStatus.OK).body("Employee Added Succesfully");
			else	
				return ResponseEntity.status(HttpStatus.OK).body("Employee Not Added");

		} 
		catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@ApiOperation(value = "Update Employee By Id")
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateEmployee(@RequestBody Employee employee, @PathVariable ("id") int empId) {
		try {

			boolean status =employeeService.updateEmployee(employee, empId);
			if(status == true)
				return ResponseEntity.status(HttpStatus.OK).body("Employee Updated Succesfully");
			else
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Employee Found");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@ApiOperation(value = "Delete Employee By Id")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteEmployeeByID(@PathVariable ("id") int empId){
		try {
			boolean status=employeeService.deleteEmployeeByID(empId);
			if(status == true)
				return ResponseEntity.status(HttpStatus.OK).body("Employee Deleted Succesfully");
			else
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Employee is not Present");

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

	@ApiOperation(value = "Delete All Employee")
	@DeleteMapping("/delete-all")
	public ResponseEntity<?> deleteAllEmployee(){
		try {
			boolean status=employeeService.deleteAllEmployees();
			if(status == true)
				return ResponseEntity.status(HttpStatus.OK).body("All Employee Deleted Succesfully");
			else
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Not Deleted");

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

	@ApiOperation(value = "Employees List By Ascending Order of Name")
	@GetMapping("/asc")
	public ResponseEntity<List<Employee>> sortByEmpNameAsc() {
		List<Employee> list = employeeService.sortByEmpNameAsc();
		if (list.size() <= 0)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

		return ResponseEntity.status(HttpStatus.OK).body(list);
	}

	@ApiOperation(value = "Employees List By Descending Order of Name")
	@GetMapping("/desc")
	public ResponseEntity<List<Employee>> sortByEmpNameDesc(@RequestParam String order) {
		List<Employee> list = employeeService.sortByEmpNameDesc();
		if (list.size() <= 0)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

		return ResponseEntity.status(HttpStatus.OK).body(list);
	}


	@ApiOperation(value = "Get Employees Count By Department Id")
	@GetMapping("/emp-count")
	public ResponseEntity<List<EmployeeCount>> getAllEmployeeByDepartmentId(){
		System.out.println(employeeCountService.getAllEmployeeByDepartmentId());
		List<EmployeeCount> list = employeeCountService.getAllEmployeeByDepartmentId();
		if (list.size() <= 0)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

		return ResponseEntity.status(HttpStatus.OK).body(list);
	}

	@ApiOperation(value = "Get Employees By Pages")
	@GetMapping("/paging/{page}/{size}")
	public ResponseEntity<Map<String, Object>> getAllTutorialsPage( @PathVariable ("page") int page, @PathVariable ("size") int size) {

		try {
			List<Employee> employee = new ArrayList<Employee>();
			Pageable pages = PageRequest.of(page, size);

			Page<Employee> pageData;
			pageData = employeeRepository.findAll(pages);
			employee = pageData.getContent();

			Map<String, Object> response = new TreeMap<>();
			response.put("employees", employee);
			response.put("currentPage", pageData.getNumber());
			response.put("totalItems", pageData.getTotalElements());
			response.put("totalPages", pageData.getTotalPages());

			return new ResponseEntity<>(response, HttpStatus.OK);
		} 
		catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
