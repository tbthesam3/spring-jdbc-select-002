package com.example.resultsetextractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

public class StudentAddressResultSetExtractor implements ResultSetExtractor<Map<String, List<String>>>{

	@Override
	public Map<String, List<String>> extractData(ResultSet res) throws SQLException {

		Map<String, List<String>> studentTable = new HashMap<String, List<String>>();
		
		while (res.next()) {
		String studentName = res.getString("STUDENT_NAME");
		String studentAddress = res.getString("STUDEBT_ADDRESS");
		
		List<String> studentsNameList  = studentTable.get(studentAddress);
		
		if(studentsNameList == null) {
			
			ArrayList<String> newStudentList = new ArrayList<String>();
			newStudentList.add(studentName);
			
			studentTable.put(studentAddress, newStudentList);
		}
		else {
			studentsNameList.add(studentName);
		}
		
		}
		return studentTable;
	}

}
