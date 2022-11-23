package com.skilldistillery.childcare.entities;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class GuardianId {
private static final long serialVersionUID = 1L;
	
	@Column(name="kid_id")
	private int kidId;
	
	@Column(name="adult_id")
	private int adultId;

	public int getKidId() {
		return kidId;
	}
	
	

	public GuardianId(int kidId, int adultId) {
		super();
		this.kidId = kidId;
		this.adultId = adultId;
	}



	public void setKidId(int kidId) {
		this.kidId = kidId;
	}

	public int getAdultId() {
		return adultId;
	}

	public void setAdultId(int adultId) {
		this.adultId = adultId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(adultId, kidId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GuardianId other = (GuardianId) obj;
		return adultId == other.adultId && kidId == other.kidId;
	}


	@Override
	public String toString() {
		return "GuardianId [kidId=" + kidId + ", adultId=" + adultId + "]";
	}



	public GuardianId() {
		super();
	}
	
	
}
