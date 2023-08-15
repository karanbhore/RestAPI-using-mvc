package com.prowings.service;

import java.util.List;

import com.prowings.entity.Student;
import com.prowings.entity.Subject;

public interface StudentSubjectService {

	public boolean saveStudent(Student std);
	
	public Student getStudent(int id);

	public List<Student> getStudents();
	
	public boolean deleteStudent(int id);

	public Student updateStudent(Student std);

	public List<Subject> getAllSubjects();

	public List<Subject> getSubjects(int id);
	
	
	
}
