package com.example.dao;

import java.util.List;
import java.util.Map;

import com.example.api.Student;

public interface StudentDAO {
	
	void insert(Student student);
	
	void insert(List<Student> students);
	
	boolean deleteRecordByRoolNo(int roolNo);
	
	int deleteRecordByStudentNameOrStudentAddress(String studentName,String studentAddress);
	
	void cleanUp();
	
	List<Student> findAllStudents();

	Student findStudentByRoolNo(int roolNo);

	List<Student> findStudentByName(String name);
	
	Map<String,List<String>> groupStudentsByAddress();
}