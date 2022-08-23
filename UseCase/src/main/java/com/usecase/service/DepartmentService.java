package com.usecase.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.usecase.model.Department;
import com.usecase.repository.DepartmentRepository;

@Service
public class DepartmentService {
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	//get all departments
	public List<Department> getAllDepartments(){
		List<Department> depts = (List<Department>)departmentRepository.findAll(); 
		return depts;
	}
	
	//add new department
	public int addDepartment(Department department) {
		departmentRepository.save(department);
		return 1;
	}
	
	public Department getDepartment(int department_id){
		return departmentRepository.findById(department_id).get();
	}
	
	//update department by id
	public boolean updateDepartment(Department department, int department_id){
		if(department_id == department.getDeptId()) {
			departmentRepository.save(department);
			return true;
		}
		return false;
	}
	
	//delete all departments
	public boolean deleteAllDepartment(){
		departmentRepository.deleteAll();
		return true;
	}
	
	//delete department by id
	public boolean deleteDepartmentByID(int department_id){
		departmentRepository.deleteById(department_id);
		return true;
	}

}
