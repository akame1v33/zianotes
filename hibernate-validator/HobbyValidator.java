package com.student;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class HobbyValidator implements ConstraintValidator<IsValidHobby, String> {
	
	private String listOfHobbies;
	
	@Override
	public void initialize(IsValidHobby arg0) {
		// TODO Auto-generated method stub
//		System.out.println(arg0);
		this.listOfHobbies = arg0.listOfHobbies();
	}

	@Override
	public boolean isValid(String hobby, ConstraintValidatorContext ctx) {
		// TODO Auto-generated method stub
		if( hobby == null) {
			return false;
		} 
		if (hobby.matches(this.listOfHobbies)) {
			return true;
		}
		return false;
	}

}
