package com.usecase.repository;

import org.springframework.data.repository.CrudRepository;
import com.usecase.model.Department;

public interface DepartmentRepository extends CrudRepository<Department, Integer>{
	
//	@Query(value="select distinct d.department_id, count(e.employee_id) from Employee e inner join Department d where e.department_id=d.department_id group by department_id;",nativeQuery = true)
//	public List<Employee> getEmpCountByDeptId();
}
