package com.usecase.repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.usecase.model.Employee;

@Repository
//public interface EmployeeRepository extends CrudRepository<Employee, Integer>{
//public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
//	
//	@Query(value="select distinct department.department_id as dept, count(employee.employee_id) as empCount from Employee inner join Department where employee.department_id=department.department_id group by employee.department_id;",nativeQuery = true)
//	public List<Object[]> getEmpCountByDeptId();
//}

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
	Page<Employee> findAll(Pageable pageable);
	
	@Query(value="select distinct department.department_id as dept, count(employee.employee_id) as empCount from Employee inner join Department where employee.department_id=department.department_id group by employee.department_id;",nativeQuery = true)
	public List<Object[]> getEmpCountByDeptId();
	
}