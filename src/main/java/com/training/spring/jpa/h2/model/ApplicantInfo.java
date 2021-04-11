package com.training.spring.jpa.h2.model;

import javax.persistence.*;

@Entity
@Table(name = "Info")
public class ApplicantInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "name")
	private String name;

	@Column(name = "comments")
	private String comments;

	@Column(name = "flagged")
	private boolean flagged;
	
	
	@Column(name = "Travel_History")
	public String travelHistory;
	
	@Column(name = "Passport_Number")
	public String passportNum;

	@Column(name = "Profession")
	public String profession;
	
	
	

	public ApplicantInfo() {

	}



	public ApplicantInfo(String name, String comments, boolean flagged, String travelHistory,
			String passportNum, String profession) {

		this.name = name;
		this.comments = comments;
		this.flagged = flagged;
		this.travelHistory = travelHistory;
		this.passportNum = passportNum;
		this.profession = profession;
	}




	public ApplicantInfo(ApplicantInfo applicantInfo) {
		this.name=applicantInfo.name;
		this.comments=applicantInfo.comments;
		this.flagged=applicantInfo.flagged;
		this.travelHistory=applicantInfo.travelHistory;
		this.passportNum=applicantInfo.passportNum;
		this.profession=applicantInfo.profession;
		// TODO Auto-generated constructor stub
	}




	public long getId() {
		return id;
	}




	public void setId(long id) {
		this.id = id;
	}




	public String getName() {
		return name;
	}




	public void setName(String name) {
		this.name = name;
	}




	public String getComments() {
		return comments;
	}




	public void setComments(String comments) {
		this.comments = comments;
	}




	public boolean isFlagged() {
		return flagged;
	}




	public void setFlagged(boolean flagged) {
		this.flagged = flagged;
	}




	public String getTravelHistory() {
		return travelHistory;
	}




	public void setTravelHistory(String travelHistory) {
		this.travelHistory = travelHistory;
	}




	public String getPassportNum() {
		return passportNum;
	}




	public void setPassportNum(String passportNum) {
		this.passportNum = passportNum;
	}




	public String getProfession() {
		return profession;
	}




	public void setProfession(String profession) {
		this.profession = profession;
	}




	@Override
	public String toString() {
		return "ApplicantInfo [id=" + id + ", name=" + name + ", comments=" + comments + ", flagged=" + flagged
				+ ", travelHistory=" + travelHistory + ", passportNum=" + passportNum + ", profession=" + profession
				+ "]";
	}
	
	
	
	

}
