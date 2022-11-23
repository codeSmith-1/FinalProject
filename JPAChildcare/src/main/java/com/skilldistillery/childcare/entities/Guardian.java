package com.skilldistillery.childcare.entities;

import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
public class Guardian {

	@EmbeddedId
	private GuardianId id;
	
	private String relationship;
	
	@ManyToOne
	@MapsId(value = "adultId")
	@JoinColumn(name="adult_id")
	private Adult adult;
	
	@ManyToOne
	@MapsId(value = "kidId")
	@JoinColumn(name="kid_id")
	private Kid kid;
	

	public Guardian() {
		super();
	}

	public GuardianId getId() {
		return id;
	}

	public void setId(GuardianId id) {
		this.id = id;
	}

	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
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
		Guardian other = (Guardian) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Guardian [id=" + id + ", relationship=" + relationship + "]";
	}
	
	
}
