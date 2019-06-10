package com.clockin.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.clockin.api.entities.Company;

public interface CompanyRepository extends JpaRepository<Company, Long>  {

	@Transactional(readOnly = true)
	Company findByGovId(String cnpj);

}

