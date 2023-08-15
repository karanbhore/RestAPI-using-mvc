package com.prowings.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.prowings.entity.Student;
import com.prowings.entity.Subject;

@Repository
public class StudentSubjectDaoImpl implements StudentSubjectDao {

	@Autowired
	SessionFactory sessionFactory;

	public boolean saveStudent(Student std) {

		boolean result = false;
		try {
			Session session = sessionFactory.openSession();
			Transaction txn = session.beginTransaction();
			session.save(std);
			txn.commit();
			session.close();
			result = true;
		} catch (Exception e) {
			System.out.println("Error occurred while storing the student : " + e.getMessage());
		}

		return result;

	}

	public Student getStudent(int id) {

		Student std = null;

		Session session = sessionFactory.openSession();
		Transaction txn = session.beginTransaction();

		std = session.get(Student.class, id);

		txn.commit();
		session.close();

		return std;

	}

	public List<Student> getStudents() {

		Session session = sessionFactory.openSession();
		Transaction tx = null;
		List<Student> stdList = null;
		try {

			tx = session.beginTransaction();
			Query<Student> query = session.createQuery("from Student");
			stdList = query.getResultList();
			tx.commit();

		} catch (HibernateException ex) {
			if (tx != null) {
				tx.rollback();
			}
			System.out.println("Exception: " + ex.getMessage());
			ex.printStackTrace(System.err);
		} finally {
			session.close();
			return stdList;
		}

	}

	public boolean deleteStudent(int id) {

		Session session = sessionFactory.openSession();
		Transaction tx = null;
		Student retrievedStd = null;
		boolean res = false;
		try {

			tx = session.beginTransaction();
			retrievedStd = session.get(Student.class, id);

			if (retrievedStd != null) {
				session.delete(retrievedStd);
				res = true;
			}
			tx.commit();

		} catch (HibernateException ex) {
			System.out.println("Unable to delete student : " + ex.getMessage());
		}

		return res;
	}

	public Student updateStudent(Student std) {

		Session session = sessionFactory.openSession();
		Transaction tx = null;
		Student retrievedStd = null;
		try {

			tx = session.beginTransaction();
			// get existing std
			retrievedStd = session.get(Student.class, std.getId());

			if (retrievedStd != null) {
				System.out.println("found existing student.. Updating it!!");
				retrievedStd.setRoll(std.getRoll());
				retrievedStd.setName(std.getName());
				retrievedStd.setAddress(std.getAddress());
				session.update(retrievedStd);
				System.out.println("Updated successfully!!");
			} else {
				System.out.println("Since student was not present - creating it!!");
				session.save(std);
			}
			tx.commit();

		} catch (HibernateException ex) {
			System.out.println("Unable to update student : " + ex.getMessage());
		}

		return retrievedStd;
	}

	@Override
	public List<Subject> getAllSubjects() {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		List<Subject> subList = null;
		try {

			tx = session.beginTransaction();
			Query<Subject> query = session.createQuery("from Subject");
			subList = query.getResultList();
			tx.commit();

		} catch (HibernateException ex) {
			if (tx != null) {
				tx.rollback();
			}
			System.out.println("Exception: " + ex.getMessage());
			ex.printStackTrace(System.err);
		} finally {
			session.close();
			return subList;
		}
	}

	@Override
	public List<Subject> getSubjects(int id) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		List<Subject> subList = null;
		try {

			tx = session.beginTransaction();
			String hql = "SELECT s FROM Student st JOIN st.subjects s WHERE st.id = :studentId";
			Query<Subject> query = session.createQuery(hql).setParameter("studentId", id);
			subList = query.getResultList();
			tx.commit();

		} catch (HibernateException ex) {
			if (tx != null) {
				tx.rollback();
			}
			System.out.println("Exception: " + ex.getMessage());
			ex.printStackTrace(System.err);
		} finally {
			session.close();
			return subList;
		}
	}

}
