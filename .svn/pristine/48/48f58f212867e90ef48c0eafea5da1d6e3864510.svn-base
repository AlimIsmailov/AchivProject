package com.ita.softserveinc.achiever.tool;

import com.ita.softserveinc.achiever.entity.QuizResult;
import com.ita.softserveinc.achiever.entity.User;

public class UserRatingDto implements Comparable<IDTO>, IDTO{

	private String firstName;
	private String lastName;
	private Double rating;
	
	public UserRatingDto() {
		// TODO Auto-generated constructor stub
	}
	
	public UserRatingDto(String firstName, String lastName, Double rating) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.rating = rating;
	}

	public UserRatingDto(User user) {
		super();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.rating = new Double(0);
		for(QuizResult quizResult : user.getResults()){
			this.rating += quizResult.getTotalGrade();
		}
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Double getRating() {
		return rating;
	}
	public void setRating(Double rating) {
		this.rating = rating;
	}

	@Override
	public int compareTo(IDTO that) {
		UserRatingDto uDto = (UserRatingDto)that;
		if(uDto.getRating() == null){
			return 1;
		}else if(this.rating == null){
			return -1;
		}else if(this.rating < uDto.getRating()){
			return 1;
		}else if(this.rating > uDto.getRating()){
			return -1;
		}else{
			return 0;
		}	
	}
  
}
