package com.usecase.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RestController;
import com.usecase.model.Department;
import com.usecase.model.Employee;
import com.usecase.service.DepartmentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/department")
@CrossOrigin("http://localhost:3000/")
@Api(value = "Department Resources", description = "All operation related to Department")
public class DepartmentController {

	@Autowired
	private DepartmentService departmentService;

	//	@GetMapping("/get-departments")
	//	public List<Department> getAllDepartment(){
	//		return departmentService.getAllDepartments();
	//	}
	//	
	//	@PostMapping("/add-department")
	//	public void addDepartment(@RequestBody Department department){
	//		departmentService.addDepartment(department);
	//	}
	
	@ApiOperation(value = "Get All Departments")
	@GetMapping("/get")
	public ResponseEntity<List<Department>> getAllDepartment(){
		List<Department> list = departmentService.getAllDepartments();
		if (list.size() <= 0)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

		return ResponseEntity.status(HttpStatus.OK).body(list);
	}
	
	@ApiOperation(value = "Get Department By Id")
	@GetMapping("/get/{id}")
	public ResponseEntity<Department> getDepartment(@PathVariable ("id") int department_id){
		System.out.println(department_id);
		Department department = departmentService.getDepartment(department_id);
		System.out.println(department);
		if(department == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

		return ResponseEntity.status(HttpStatus.OK).body(department);
	}

	@ApiOperation(value = "Add New Department")
	@PostMapping("/add")
	public ResponseEntity<String> addDepartment(@RequestBody Department department){
		departmentService.addDepartment(department);
		try {

			int num=departmentService.addDepartment(department);
			if(num != 0)
				return ResponseEntity.status(HttpStatus.OK).body("Department Added Succesfully");
			else	
				return ResponseEntity.status(HttpStatus.OK).body("Department Not Added");

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@ApiOperation(value = "Update Department By Id")
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateDepartments(@RequestBody Department department, @PathVariable ("id") int department_id) {
		try {
			
			boolean status = departmentService.updateDepartment(department, department_id);
			if(status == true)
				return ResponseEntity.status(HttpStatus.OK).body("Department Updated Succesfully");
			else
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Department Found");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@ApiOperation(value = "Delete Department By Id")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteEmployeeByID(@PathVariable ("id") int department_id){
		try {
			boolean status=departmentService.deleteDepartmentByID(department_id);
			if(status == true)
				return ResponseEntity.status(HttpStatus.OK).body("Department Deleted Succesfully");
			else
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Department is not Present");

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

	@ApiOperation(value = "Delete All Departments")
	@DeleteMapping("/delete-all")
	public ResponseEntity<?> deleteAllDepartments(){
		try {
			boolean status= departmentService.deleteAllDepartment();
			if(status == true)
				return ResponseEntity.status(HttpStatus.OK).body("All Department Deleted Succesfully");
			else
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Not Deleted");

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}
}
