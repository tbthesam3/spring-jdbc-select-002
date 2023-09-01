package com.example.resultsetextractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.example.api.Student;

public class StudentResultSetExtractor implements ResultSetExtractor<List<Student>> {

	@Override
	public List<Student> extractData(ResultSet res) throws SQLException {

		List<Student> studentList = new ArrayList<Student>();
		
		while(res.next()) {
			
			Student student = new Student();
			
		student.getRoolNo(res.getInt("ROOL_NO"));
		student.getName(res.getString("STUDENT_NAME"));
		student.getAddress(res.getString("STUDENT_ADDRESS"));
		
		studentList.add(student);
		}
		return studentList;
	}


	}


