package com.example.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.example.api.Student;
import com.example.resultsetextractor.StudentAddressResultSetExtractor;
import com.example.resultsetextractor.StudentResultSetExtractor;
import com.example.rowmapper.StudentRowMapper;

@Repository("studentDao")
public class StundentDAOImpl implements StudentDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void insert(Student student)   {

		String sql = "INSERT INTO STUDENT VALUES (?,?,?)";
		Object[] arg = { student.getRoolNo(), student.getName(), student.getAddress() };

		int noOfRowInserted = jdbcTemplate.update(sql, arg);

		System.out.println("No of row inserted is " + noOfRowInserted);
	}

	@Override
	public boolean deleteRecordByRoolNo(int roolNo) {

		String sql = "DELETE FROM STUDENT WHERE ROOL_NO = ?";

		int noOfRowDeleted = jdbcTemplate.update(sql, roolNo);

		System.out.println("No of record deleted is " + noOfRowDeleted);
		return noOfRowDeleted == 1;
	}

	@Override
	public int deleteRecordByStudentNameOrStudentAddress(String studentName, String studentAddress) {

		String sql = "DELETE FROM STUDENT WHERE STUDENT_NAME = ? OR STUDENT_ADDRESS = ?";
		Object[] arguments = { studentName, studentAddress };
		int noOfRowsDeleted = jdbcTemplate.update(sql, arguments);
		System.out.println("No of rows got deleted are " + noOfRowsDeleted);
		return noOfRowsDeleted;
	}

	public void cleanUp() {
		String sql = "TRANCATE TABLE STUDENT";
		jdbcTemplate.execute(sql);
		System.out.println("All rows got deleted are");

	}

	@Override
	public void insert(List<Student> students) {

		String sql = "INSERT INTO STUDENT VALUES (?,?,?)";

		ArrayList<Object[]> sqlArgs = new ArrayList<Object[]>();

		for (Student tempStudent : students) {
			Object[] studentData = { tempStudent.getRoolNo(), tempStudent.getName(), tempStudent.getAddress() };
			sqlArgs.add(studentData);
		}

		jdbcTemplate.batchUpdate(sql, sqlArgs);

		System.out.println("Batch update completed");
	}

	@Override
	public List<Student> findAllStudents()  {

		String selectSql = "SELECT * FROM STUDENT";
		List<Student> studentList = jdbcTemplate.query(selectSql, new StudentRowMapper());
		return studentList;
		
	}

	@Override
	public Student findStudentByRoolNo(int roolNo) {

		String selectSql = "SELECT ROOL_NO as roolNo,STUDENT_NAME as name,STUDENT_ADDRESS as address FROM STUDENT WHERE ROOL_NO = ?";
		Student student = jdbcTemplate.queryForObject(selectSql, new StudentRowMapper(),roolNo);
		return student;
	}

	@Override
	public List<Student> findStudentByName(String name) {
		
		String sql = "SELECT * FROM STUDENT WHERE STUDENT_NAME = ?";
		List<Student> studentList  = jdbcTemplate.query(sql,new StudentResultSetExtractor(),name);
		
		
		
		return studentList;
	}

	@Override
	public Map<String, List<String>> groupStudentsByAddress() {

		String sql = "SELECT * FROM STUDENT";
		Map<String, List<String>> query = jdbcTemplate.query(sql, new StudentAddressResultSetExtractor());
		
		return query;
	}

}
