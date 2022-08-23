package com.usecase.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.usecase.model.EmployeeCount;
import com.usecase.repository.EmployeeRepository;

@Service
public class EmployeeCountService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	public List<EmployeeCount> getAllEmployeeByDepartmentId(){
		List<Object[]> result = employeeRepository.getEmpCountByDeptId();
//		System.out.println(result);
		List<EmployeeCount> list=new ArrayList<EmployeeCount>();
	    if(result != null && !result.isEmpty()){
	       for (Object[] object : result) {
	    	   EmployeeCount emp=new EmployeeCount();
	    	   emp.setDept(Integer.parseInt(object[0].toString()));
//	    	   System.out.println("Dept : "+Integer.parseInt(object[0].toString()));
	    	   emp.setEmpCount(new BigInteger(object[1].toString()).intValue());
//	    	   System.out.println("Empcount : "+new BigInteger(object[1].toString()).intValue());
	    	   list.add(emp);
	       }
	    }
//	    System.out.println(list);
		return list;
	}
	
	
}
