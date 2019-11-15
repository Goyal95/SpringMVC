package com.springmvc.dao;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.springmvc.entity.EmpVO;

public class TestJNDI {
 
	public static void main(String[] args) throws Exception {
		ApplicationContext ac =
		
		new FileSystemXmlApplicationContext("src/main/java/com/springmvc/dao/ApplicationContext.xml");
		
		EmpDAO dao = (EmpDAO) ac.getBean("empDAO");
		System.out.println("Got DAO : " + dao);

		// insert
		EmpVO emps = new EmpVO();
		emps.setEmpno(2341);
		emps.setEname("Ram");
		emps.setJob("MANAGER");
	//	dao.insertEmp(emps);
		
		//insert using SQLUpdate
		DataSource ds = (DataSource)ac.getBean("dataSource");
		EmpDAO.EmpInsert einsert = new EmpDAO().new EmpInsert(ds);
//	   einsert.insert(emps);
		List l = dao.getUsers();
		
			System.out.println("Employees are : ");
		for (int i = 0; i < l.size(); i++) {
			System.out.println(l.get(i));
			Map  m = (Map)l.get(i);
			System.out.println(m.get("ENAME"));
			System.out.println(m.get("JOB"));
 		}
		/*
		List l = dao.getEmps();

		for (int i = 0; i < l.size(); i++) {
			EmpVO emp = (EmpVO) l.get(i);
			System.out.println(emp.getEname() + "," + emp.getHireDate());
		}
		*/
		// using RowMapper
		/*
		System.out.println("------------ RowMapper ------------------");
		List l = dao.getAllEmps();

		for (int i = 0; i < l.size(); i++) {
			EmpVO emp = (EmpVO) l.get(i);
			System.out.println(emp.getEname() + "," + emp.getHireDate());
		}
		*/
		// Simple Queries
		/*
		System.out.println("Total Rows :" + dao.getEmpCount());
		*/
		/*
		System.out.println("Ename for 1 empno : " + dao.getEnameForEmpno(1));
		*/
		/*
		System.out.println("Total Rows for Sales :" + dao.getEmpCountforJobString("ANALYST"));
		*/
		// NamedParameterJdbcTemplate
		//System.out.println("NamedParameterJdbcTemplate");
		//System.out.println("Total Rows for Sales :" + dao.getEmpCountforJob("w"));
	}

}
