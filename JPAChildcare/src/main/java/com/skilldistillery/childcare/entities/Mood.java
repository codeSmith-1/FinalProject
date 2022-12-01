package com.skilldistillery.childcare.entities;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;




@Entity
public class Mood {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String description;
	
	@OneToMany(mappedBy="mood")
	private List<MoodEntry> moodEntries;
	

	public List<MoodEntry> getMoodEntries() {
		return moodEntries;
	}

	public void setMoodEntries(List<MoodEntry> moodEntries) {
		this.moodEntries = moodEntries;
	}

	public Mood() {
		super();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public void addMoodEntry(MoodEntry moodEntry) {
		if (moodEntries == null) {
			moodEntries = new ArrayList<>();

			if (!moodEntries.contains(moodEntry)) {
				moodEntries.add(moodEntry);
				moodEntry.setMood(this);
			}
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mood other = (Mood) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Mood [id=" + id + ", description=" + description + "]";
	}






	
	
	
}
