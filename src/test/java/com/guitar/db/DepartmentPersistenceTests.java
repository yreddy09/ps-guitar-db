package com.guitar.db;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.guitar.db.model.Department;
import com.guitar.db.repository.DepartmentJpaRepository;

@ContextConfiguration(locations={"classpath:com/guitar/db/applicationTests-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class DepartmentPersistenceTests {
	@Autowired
	private DepartmentJpaRepository deptJpaRepository;

	@Test
	public void testFind() throws Exception {
		BigDecimal deptNo = BigDecimal.valueOf(30);
		Department dept = deptJpaRepository.findOne(deptNo);
		System.out.println(dept.toString());
		assertEquals("SALES", dept.getDname());
	}

	@Test
	public void testFindAll() throws Exception {
		List<Department> deptList = deptJpaRepository.findAll();

		for (Department dept : deptList) {
			System.out.println(dept.toString());
		}

		assertEquals(4, deptList.size());
	}
}
