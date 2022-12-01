package com.skilldistillery.childcare.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.childcare.entities.DailyReport;
import com.skilldistillery.childcare.entities.ReportImage;
import com.skilldistillery.childcare.entities.Staff;
import com.skilldistillery.childcare.repositories.DailyReportRepository;
import com.skilldistillery.childcare.repositories.ReportImageRepository;
import com.skilldistillery.childcare.repositories.StaffRepository;

@Service
public class ReportImageServiceImpl implements ReportImageService {

	@Autowired
	public ReportImageRepository imageRepo;
	@Autowired
	public DailyReportRepository reportRepo;
	@Autowired
	public StaffRepository staffRepo;

	@Override
	public ReportImage show(int imageId) {
		return imageRepo.queryById(imageId);
	}

	@Override
	public List<ReportImage> imagesByReportId(int reportId) {
		List<ReportImage> images = imageRepo.findByDailyReportId(reportId);
		if (images != null) {
			return imageRepo.findByDailyReportId(reportId);
		}
		return null;
	}

	@Override
	public List<ReportImage> showAll(int id) {
		DailyReport dr = reportRepo.queryById(id);
		List<ReportImage> reportImages = imageRepo.findByDailyReportId(id);
		if (reportImages != null) {
			return reportImages;
		}
		return null;
	}

//	@Override
//	public ReportImage create(ReportImage ri, int dailyReportId) {
//		DailyReport dr = reportRepo.queryById(dailyReportId);
//		List<ReportImage> images = imageRepo.findByDailyReportId(dailyReportId);
//		ri.setDailyReport(dr);
//		
//		images.add(ri);
//		dr.setImages(images);
//		
//		return ri;
//	}

	@Override
	public boolean delete(int rid) {
		ReportImage reportImage = imageRepo.queryById(rid);
		if (reportImage != null) {
			imageRepo.delete(reportImage);
			return true;
		}
		return false;
	}

	@Override
	public ReportImage create(int staffId, ReportImage ri, int dailyReportId) {
		DailyReport dr = reportRepo.queryById(dailyReportId);
		Staff staff = staffRepo.queryById(staffId);
		ri.setDailyReport(dr);
		ri.setStaff(staff);
		try {
			ri = imageRepo.saveAndFlush(ri);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("------------------ error imageServieCreate();");
			ri = null;
		}
		return ri;
	}

}
