package com.prowings.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.prowings.entity.Student;
import com.prowings.entity.Subject;
import com.prowings.service.StudentSubjectService;

@RestController
public class StudentSubjectController {

	@Autowired
	StudentSubjectService studentSubjectService;

	@PostMapping("/students")
	public Student createStudent(@RequestBody Student std) {
		System.out.println("request received to create student  : " + std);

		if (studentSubjectService.saveStudent(std))
			return std;
		else
			return null;

	}

	@PutMapping("/students")
	public Student updateStudent(@RequestBody Student std) {
		System.out.println("request received to update student  : " + std);

		return studentSubjectService.updateStudent(std);

	}

	@GetMapping("/students/{id}")
	public Student getStudent(@PathVariable("id") int id) {
		System.out.println("request received to get student of id : " + id);

		Student retrivedStudent = studentSubjectService.getStudent(id);

		return retrivedStudent;

	}
	@GetMapping("/students/{id}/subjects/{id}")
	public List<Subject> getSubjects(@PathVariable("id") int id) {
		System.out.println("request received to get subject of student of id : " + id);

		List<Subject> retrivedSubject = studentSubjectService.getSubjects(id);

		return retrivedSubject;

	}


	@GetMapping("/students")
	public List<Student> getAllStudents() {
		System.out.println("request received to get all students");

		return studentSubjectService.getStudents();
	}

	@GetMapping("/subjects")
	public List<Subject> getAllSubjects() {
		System.out.println("request received to get all subjects");

		return studentSubjectService.getAllSubjects();

	}

	@DeleteMapping("/students/{id}")
	public String deleteStudentById(@PathVariable("id") int id) {
		System.out.println("request received to delete student with id : " + id);

		if (studentSubjectService.deleteStudent(id))
			return "Student deleted successfully!!";
		else
			return "error while deleteing specified student!!!";
	}
}
