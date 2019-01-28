package com.reflection;

import com.annotation.Def;
import com.annotation.Defaultable;
import com.annotation.Defaultable.Default;

@Defaultable(def = Default.ON, lastModified = "10/10/2018")
public class Student {
	
	
	@Def("Errol")
	private String name;
	
	@Def("25")
	private int age;
	
	@Def("BSIT-4D")
	private String section;
	
	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Student(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	public Student() {
		super();
	}

	@Override
	public String toString() {
		
		return "Student [name=" + name + ", age=" + age + ", section=" + section + "]";
	}

	
}
