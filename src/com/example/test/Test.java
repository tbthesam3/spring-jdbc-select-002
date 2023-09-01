package com.example.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.example.api.Student;
import com.example.dao.StudentDAO;
import com.example.dao.StundentDAOImpl;
import com.example.service.StudentDAOHelper;

public class Test {

	public static void main(String[] args)  {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		System.out.println("application context loaded");
 
		StudentDAOHelper helper = context.getBean("studentDaoHelper",StudentDAOHelper.class);
		helper.setUpStudentTable();
		
		StundentDAOImpl studentDAO = context.getBean("studentDao",StundentDAOImpl.class);
		List<Student> studentsList = studentDAO.findAllStudents();
		helper.printStudents(studentsList);
		
		Student student = studentDAO.findStudentByRoolNo(2);
		System.out.println(student);
		
		List<Student> students  = studentDAO.findStudentByName("Hesam");
		helper.printStudents(students);
		
		Map<String, List<String>> groupStudentsByAddress = studentDAO.groupStudentsByAddress();
		System.out.println(groupStudentsByAddress);
		
		Student abhilash = new Student();
		abhilash.setAddress("Blr");
		abhilash.setRoolNo(1);
		
		Student jhon = new Student();
		jhon.setAddress("Denver");
		jhon.setRoolNo(2);
		
		Student frank = new Student();
		frank.setAddress("New york");
		frank.setRoolNo(7);
//		
//		studentDAO.updateStudent(abhilash);
//		studentDAO.updateStudent(jhon);
//		studentDAO.updateStudent(frank);
	
		List<Student> studentList = new ArrayList<Student>();
		studentList.add(abhilash);
		studentList.add(jhon);
		studentList.add(frank);
		
		studentDAO.updateStudent(studentList);
		
	}


}
