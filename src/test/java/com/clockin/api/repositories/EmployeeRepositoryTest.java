package com.clockin.api.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.security.NoSuchAlgorithmException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.clockin.api.entities.Company;
import com.clockin.api.entities.Employee;
import com.clockin.api.enums.ProfileEnum;
import com.clockin.api.utils.PasswordUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class EmployeeRepositoryTest {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private CompanyRepository companyRepository;

	private static final String EMAIL = "email@email.com";
	private static final String GOVERNMENTID = "24291173474";

	@Before
	public void setUp() throws Exception {
		Company company = this.companyRepository.save(getCompanyData());
		this.employeeRepository.save(getEmployeeData(company));
	}

	@After
	public final void tearDown() {
		this.companyRepository.deleteAll();
	}

	@Test
	public void testFindEmployeeByEmail() {
		Employee employee = this.employeeRepository.findByEmail(EMAIL);

		assertEquals(EMAIL, employee.getEmail());
	}

	@Test
	public void testFindEmployeeByGovernmentId() {
		Employee employee = this.employeeRepository.findByGovernmentId(GOVERNMENTID);

		assertEquals(GOVERNMENTID, employee.getGovernmentId());
	}

	@Test
	public void testFindEmployeeByEmailAndGovernmentId() {
		Employee employee = this.employeeRepository.findByGovernmentIdOrEmail(GOVERNMENTID, EMAIL);

		assertNotNull(employee);
	}

	@Test
	public void testFindEmployeeByEmailOrGovernmentIdWithInvalidEmail() {
		Employee employee = this.employeeRepository.findByGovernmentIdOrEmail(GOVERNMENTID, "email@invalid.com");

		assertNotNull(employee);
	}

	@Test
	public void testFindEmployeeByEmailOrGovernmentIdWithInvalidGovId() {
		Employee employee = this.employeeRepository.findByGovernmentIdOrEmail("23123123", EMAIL);

		assertNotNull(employee);
	}

	private Employee getEmployeeData(Company company) throws NoSuchAlgorithmException {
		Employee employee = new Employee();
		employee.setName("Joe Foo");
		employee.setProfile(ProfileEnum.ROLE_USER);
		employee.setPassword(PasswordUtils.gerarBCrypt("abcdef"));
		employee.setGovernmentId(GOVERNMENTID);
		employee.setEmail(EMAIL);
		employee.setCompany(company);
		return employee;
	}

	private Company getCompanyData() {
		Company company = new Company();
		company.setName("Random Company");
		company.setGovId("011020909");
		return company;
	}

}
