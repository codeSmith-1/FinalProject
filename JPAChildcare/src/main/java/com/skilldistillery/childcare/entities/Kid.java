package com.skilldistillery.childcare.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Kid {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	private String birthday;

	@Column(name = "image_url")
	private String imageUrl;

	@JoinColumn(name = "classroom_id")
	@ManyToOne
	private Classroom classroom;

	@JsonIgnoreProperties({"kid"})
	@OneToMany(mappedBy = "kid")
	private List<Guardian> guardians;

	@OneToMany(mappedBy = "kid")
	private List<DailyReport> dailyReport;

	public void addGuardian(Guardian guardian) {
		if (guardians == null) {
			guardians = new ArrayList<>();

			if (!guardians.contains(guardian)) {
				guardians.add(guardian);
				guardian.setKid(this);
			}
		}
	}
	
	// potentially removeGuardian

	public int getId() {
		return id;
	}

	public Classroom getClassroom() {
		return classroom;
	}

	public List<Guardian> getGuardians() {
		return guardians;
	}

	public void setGuardians(List<Guardian> guardians) {
		this.guardians = guardians;
	}

	public void setClassroom(Classroom classroom) {
		this.classroom = classroom;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	public List<DailyReport> getDailyReport() {
		return dailyReport;
	}

	public void setDailyReport(List<DailyReport> dailyReport) {
		this.dailyReport = dailyReport;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Kid other = (Kid) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Kid [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", birthday=" + birthday
				+ ", imageUrl=" + imageUrl + "]";
	}

	public Kid() {

	};

}
