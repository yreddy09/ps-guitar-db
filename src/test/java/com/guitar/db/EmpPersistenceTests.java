package com.guitar.db;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.guitar.db.model.Emp;
import com.guitar.db.repository.EmpJpaRepository;
import com.guitar.general.util.MyUtils;

@ContextConfiguration(locations={"classpath:com/guitar/db/applicationTests-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class EmpPersistenceTests {

	@Autowired
	private EmpJpaRepository empJpaRepository;

	@Test
	public void testFind() throws Exception {
		BigDecimal empNo = BigDecimal.valueOf(7698);
		Emp emp = empJpaRepository.findOne(empNo);
		System.out.println(
				"Emp num:" + emp.getEmpNo() + " Emp name: " + emp.getEname() + " Emp dept : " + emp.getDeptNo()
						+ " hire date : " + MyUtils.formatDate(emp.getHireDate()));
		System.out.println(emp.toString());
		assertEquals("BLAKE", emp.getEname());
	}

	@Test
	public void testFindAll() throws Exception {
		BigDecimal empNo = BigDecimal.valueOf(7698);
		List<Emp> empList = empJpaRepository.findAll();

		for (Emp emp2 : empList) {
			System.out.println(emp2.toString());
		}

		assertEquals(empJpaRepository.count(), empList.size());
	}
}
