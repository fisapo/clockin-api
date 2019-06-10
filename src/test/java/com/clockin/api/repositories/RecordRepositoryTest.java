package com.clockin.api.repositories;
import static org.junit.Assert.assertEquals;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.clockin.api.entities.Company;
import com.clockin.api.entities.Employee;
import com.clockin.api.entities.Record;
import com.clockin.api.enums.ProfileEnum;
import com.clockin.api.enums.TypeEnum;
import com.clockin.api.utils.PasswordUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class RecordRepositoryTest {
	
	@Autowired
	private RecordRepository recordRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private CompanyRepository companyRepository;
	
	private Long employeeId;

	@Before
	public void setUp() throws Exception {
		Company company = this.companyRepository.save(getCompanyData());
		
		Employee employee = this.employeeRepository.save(getEmployeeData(company));
		this.employeeId = employee.getId();
		
		this.recordRepository.save(getRecordData(employee));
		this.recordRepository.save(getRecordData(employee));
	}

	@After
	public void tearDown() throws Exception {
		this.companyRepository.deleteAll();
	}

	@Test
	public void testFindRecordsByEmployeeId() {
		List<Record> records = this.recordRepository.findByEmployeeId(employeeId);
		
		assertEquals(2, records.size());
	}
	
	@Test
	public void testFindRecordsByEmployeeIdPagination() {
		PageRequest page = PageRequest.of(0,10);
		Page<Record> records = this.recordRepository.findByEmployeeId(employeeId, page);
		
		assertEquals(2, records.getTotalElements());
	}
	
	private Record getRecordData(Employee employee) {
		Record record = new Record();
		record.setDate(new Date());
		record.setType(TypeEnum.LUNCH_BREAK_START);
		record.setEmployee(employee);
		return record;
	}

	private Employee getEmployeeData(Company company) throws NoSuchAlgorithmException {
		Employee employee = new Employee();
		employee.setName("Jay Blue");
		employee.setProfile(ProfileEnum.ROLE_USER);
		employee.setPassword(PasswordUtils.gerarBCrypt("123456"));
		employee.setGovernmentId("24291173474");
		employee.setEmail("email@email.com");
		employee.setCompany(company);
		return employee;
	}

	private Company getCompanyData() {
		Company company = new Company();
		company.setName("Company example");
		company.setGovId("1237805234");
		return company;
	}

}