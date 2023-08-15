package com.prowings.dao;

import java.util.List;

import com.prowings.entity.Student;
import com.prowings.entity.Subject;

public interface StudentSubjectDao {
	
	public boolean saveStudent(Student std);
	
	public Student getStudent(int id);

	public List<Student> getStudents();
	
	public List<Subject> getAllSubjects();
	
	public boolean deleteStudent(int id);

	public Student updateStudent(Student std);

	public List<Subject> getSubjects(int id);


}
