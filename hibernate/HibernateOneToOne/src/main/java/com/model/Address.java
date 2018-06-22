package com.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
@Entity
@Table(name="ADDRESS")
public class Address extends BaseEntity {
	
	@Id 
	@GeneratedValue(generator="FK_student_generator")
	@GenericGenerator(name = "FK_student_generator" , strategy = "foreign", parameters = {@Parameter(value = "student", name = "property")})
	@Column(name="STUDENT_ID")
	private String studentId;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="STUDENT_ID")
	private Student student;
	
	@Column(name="ADDRESS")
	private String address;

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Address [studentId=" + studentId + ", address=" + address + "]";
	}

	public Address() {
		super();
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
	
	
	
}
