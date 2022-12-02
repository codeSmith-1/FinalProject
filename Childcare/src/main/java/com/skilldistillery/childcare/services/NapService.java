package com.skilldistillery.childcare.services;

import com.skilldistillery.childcare.entities.Nap;

public interface NapService {

	public Nap napByReportId(int napId);
	
	public Nap create(Nap nap, int reportId);
	
	public boolean delete(int napId);
}
