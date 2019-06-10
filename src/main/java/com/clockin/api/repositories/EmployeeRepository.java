package com.clockin.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.clockin.api.entities.Employee;

@Transactional(readOnly = true)
public interface EmployeeRepository extends JpaRepository<Employee, Long>  {

	Employee findByGovernmentId(String governmentId);
	
	Employee findByEmail(String email);
	
	Employee findByGovernmentIdOrEmail(String governmentId, String email);
}
