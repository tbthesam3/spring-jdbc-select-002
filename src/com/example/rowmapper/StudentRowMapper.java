package com.example.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.api.Student;

public class StudentRowMapper implements RowMapper<Student> {

	@Override
	public Student mapRow(ResultSet res, int rowNum) throws SQLException {
		Student newStudent = new Student();

		newStudent.setRoolNo(res.getInt("ROOL_NO"));
		newStudent.setName(res.getString("STUDENT_NAME"));
		newStudent.setAddress(res.getString("STUDENT_ADDRESS"));
		
		//System.out.println("MapRow method called..");
		return newStudent;
	}

}
