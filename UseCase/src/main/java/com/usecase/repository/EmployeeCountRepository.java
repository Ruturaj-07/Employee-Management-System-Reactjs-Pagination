//package com.usecase.repository;
//
//import java.util.List;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;
//import com.usecase.model.EmployeeCount;
//
//@Repository
//public interface EmployeeCountRepository extends JpaRepository<EmployeeCount, Integer> {
//	
//	@Query(value="select distinct department.department_id as dept, count(employee.employee_id) as empCount from Employee inner join Department where employee.department_id=department.department_id group by department_id;",nativeQuery = true)
////	public List<EmployeeCount> getEmpCountByDeptId();
//	public List<Object[]> getEmpCountByDeptId();
//
//}
