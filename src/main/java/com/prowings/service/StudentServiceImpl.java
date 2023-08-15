package com.prowings.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prowings.dao.StudentSubjectDao;
import com.prowings.entity.Student;
import com.prowings.entity.Subject;

@Service
public class StudentServiceImpl implements StudentSubjectService{

	@Autowired
	StudentSubjectDao studentSubjectDao;
	
	public boolean saveStudent(Student std) {
		return studentSubjectDao.saveStudent(std);
	}

	public Student getStudent(int id) {
		return studentSubjectDao.getStudent(id);
	}

	public List<Student> getStudents() {
		return studentSubjectDao.getStudents();
	}

	public boolean deleteStudent(int id) {
		return studentSubjectDao.deleteStudent(id);
	}

	public Student updateStudent(Student std) {
		return studentSubjectDao.updateStudent(std);
	}

	@Override
	public List<Subject> getAllSubjects() {
		// TODO Auto-generated method stub
		return studentSubjectDao.getAllSubjects();
	}

	@Override
	public List<Subject> getSubjects(int id) {
		return studentSubjectDao.getSubjects(id);
	}

}
