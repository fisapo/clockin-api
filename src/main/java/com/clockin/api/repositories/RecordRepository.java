package com.clockin.api.repositories;
import java.util.List;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.clockin.api.entities.Record;

@Transactional(readOnly = true)
@NamedQueries({
		@NamedQuery(name = "RecordRepository.findByEmployeeId", 
				query = "SELECT rec FROM Record rec WHERE rec.employee.id = :employeeId") })
public interface RecordRepository extends JpaRepository<Record, Long>{
	
	List<Record> findByEmployeeId(@Param("employeeId") Long employeeId);

	Page<Record> findByEmployeeId(@Param("employeeId") Long employeeId, Pageable pageable);
}