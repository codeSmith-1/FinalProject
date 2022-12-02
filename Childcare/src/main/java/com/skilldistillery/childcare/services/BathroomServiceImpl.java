package com.skilldistillery.childcare.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.childcare.entities.Bathroom;
import com.skilldistillery.childcare.entities.BathroomType;
import com.skilldistillery.childcare.entities.DailyReport;
import com.skilldistillery.childcare.entities.Staff;
import com.skilldistillery.childcare.entities.User;
import com.skilldistillery.childcare.repositories.BathroomRepository;
import com.skilldistillery.childcare.repositories.BathroomTypeRepository;
import com.skilldistillery.childcare.repositories.DailyReportRepository;
import com.skilldistillery.childcare.repositories.StaffRepository;
import com.skilldistillery.childcare.repositories.UserRepository;

@Service
public class BathroomServiceImpl implements BathroomService {

	@Autowired
	private BathroomRepository bathroomRepo;
	@Autowired
	private DailyReportRepository reportRepo;
	@Autowired
	private StaffRepository staffRepo;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private BathroomTypeRepository bathroomTypeRepo;

	@Override
	public List<BathroomType> listAllBathroomTypes() {
		List<BathroomType> bathrooms = bathroomTypeRepo.findAll();
		if (bathrooms != null) {
			return bathroomTypeRepo.findAll();
		}
		return null;
	}

	@Override
	public Bathroom create(String username, Bathroom bathroom) {
		System.err.println("-----------");
		System.out.println(bathroom.getStaff().getId());
		Staff staff = staffRepo.queryById(bathroom.getStaff().getId());
		System.out.println(bathroom.getDay().getId());
		DailyReport report = reportRepo.queryById(bathroom.getDay().getId());

		bathroom.setDay(report);
		bathroom.setStaff(staff);
		System.out.println(bathroom);
		return bathroomRepo.saveAndFlush(bathroom);
	}
	

	@Override
	public Bathroom update(String username, Bathroom bathroom, int bathroomId) {
		User user = userRepo.findByUsername(username);
		if (user.getRole().equals("staff")) {
			Bathroom bathroomToUpdate = bathroomRepo.queryById(bathroomId);
			bathroomToUpdate.setDescription(bathroom.getDescription());
			bathroomToUpdate.setBathroomTime(bathroom.getBathroomTime());
			bathroomToUpdate.setDay(bathroom.getDay());
			bathroomToUpdate.setStaff(bathroom.getStaff());
			bathroomToUpdate.setType(bathroom.getType());
			return bathroomToUpdate;
		} else {
			return null;
		}
	}

	@Override
	public boolean delete(int bathroomId) {
		Bathroom bathroomToDelete = bathroomRepo.queryById(bathroomId);
		if (bathroomToDelete != null) {
			bathroomRepo.delete(bathroomToDelete);
			return true;
		}
		return false;
	}

	@Override
	public List<Bathroom> bathroomsByReportId(int reportId) {
		List<Bathroom> bathrooms = bathroomRepo.findByDayId(reportId);
		return bathrooms;
	}

	@Override
	public List<Bathroom> listAllBathrooms() {
		// TODO Auto-generated method stub
		return null;
	}

}
