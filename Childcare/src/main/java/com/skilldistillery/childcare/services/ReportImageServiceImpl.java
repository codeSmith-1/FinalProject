package com.skilldistillery.childcare.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.childcare.entities.ReportImage;
import com.skilldistillery.childcare.repositories.ReportImageRepository;

@Service
public class ReportImageServiceImpl implements ReportImageService{
	
	@Autowired
	public ReportImageRepository imageRepo;

	@Override
	public List<ReportImage> showAll(String username) {
		return imageRepo.findAll();
	}

	@Override
	public ReportImage show(int imageId) {
		return imageRepo.queryById(imageId);
	}

	@Override
	public ReportImage create(int username, ReportImage ri, int dailyReportId) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void delete(int username, int rid) {
		// TODO Auto-generated method stub
	}

}
