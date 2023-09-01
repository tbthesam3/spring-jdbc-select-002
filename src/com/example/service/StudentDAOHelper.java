package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.api.Student;
import com.example.dao.StudentDAO;

@Service("studentDaoHelper")
public class StudentDAOHelper {
	
	@Autowired
	private StudentDAO studentDAOImpl;
	
	public void setUpStudentTable() {
		
		Student student1 = new Student();
		student1.setRoolNo(1);
		student1.setName("Hesam");
		student1.setAddress("Tehran");
		
		Student student2 = new Student();
		student1.setRoolNo(2);
		student1.setName("Ali");
		student1.setAddress("Isfahan");
		
		Student student3 = new Student();
		student1.setRoolNo(3);
		student1.setName("Saman");
		student1.setAddress("Shoosh");
		
		ArrayList<Student> studentList = new ArrayList<Student>();
		studentList.add(student1);
		studentList.add(student2);
		studentList.add(student3);
		
		studentDAOImpl.insert(studentList);
	}
	public void printStudents(List<Student> students) {
		for(Student tempStudet : students) {
			System.out.println(tempStudet);
		}
		
	}

}
