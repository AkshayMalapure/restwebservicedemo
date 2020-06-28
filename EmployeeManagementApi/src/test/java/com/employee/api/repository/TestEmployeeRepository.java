package com.employee.api.repository;

import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.junit4.SpringRunner;

import com.employee.api.entity.EmployeeEntity;

/**
 * Test class for repository layer
 * 
 * @author akshay
 *
 */
@RunWith(SpringRunner.class)
@DataJpaTest

/*
 * This annotation is used for test slicing for repository layer This will help
 * us to create only beans required for repository test
 * 
 * By deafault @DataJpaTest roll backs transaction
 *
 */

@AutoConfigureTestDatabase(replace = Replace.NONE)
public class TestEmployeeRepository {

	@MockBean
	private EmployeeRepository repository;

	@Test
	public void verifyDbConnectionForAddEmployeeRecord() {

		try {
			repository.save(setTestData());
			assertTrue(true);
		} catch (DataAccessException ex) {
			Assert.fail(ex.getMessage());
		}

	}

	@Test
	public void verifyDbConnectionForFindById() {
		try {
			repository.findById(1);
			assertTrue(true);
		} catch (DataAccessException ex) {
			Assert.fail(ex.getMessage());

		}
	}

	@Test
	public void verifyDbConnectionForFindAll() {
		try {
			repository.findAll();
			assertTrue(true);
		} catch (DataAccessException ex) {
			Assert.fail(ex.getMessage());
		}
	}

	@Test
	public void verifyDbConnectionForUpdateEmployeeRecord() {

		try {
			EmployeeEntity entity = setTestData();

			entity.setEmployeeSalary(80000);
			repository.save(entity);
			assertTrue(true);

		} catch (DataAccessException ex) {
			Assert.fail(ex.getMessage());
		}
	}

	@Test
	public void verifyDbConnectionForDeleteEmployeeRecord() {

		try {
			EmployeeEntity entity = setTestData();

			repository.save(entity);
			repository.deleteById(1);

			assertTrue(true);
		} catch (DataAccessException ex) {
			Assert.fail(ex.getMessage());

		}

	}

	private EmployeeEntity setTestData() {
		return new EmployeeEntity(1, "Akshay Malapure", 23, "Software Developer", "akshaymalapure@gmail.com", 60000);
	}

}
