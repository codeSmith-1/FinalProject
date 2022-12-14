package com.skilldistillery.childcare.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.childcare.entities.DailyReport;
import com.skilldistillery.childcare.entities.Mood;
import com.skilldistillery.childcare.entities.MoodEntry;
import com.skilldistillery.childcare.entities.Nap;
import com.skilldistillery.childcare.services.DailyReportService;
import com.skilldistillery.childcare.services.MoodEntryService;
import com.skilldistillery.childcare.services.MoodService;
import com.skilldistillery.childcare.services.NapService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost/" })
public class DailyReportController {

	@Autowired
	public DailyReportService reportSvc;
	@Autowired
	public MoodEntryService moodEntryService;
	@Autowired
	public NapService napService;
	@Autowired
	public MoodService moodSvc;

	@GetMapping("reports")
	public List<DailyReport> listReports(Principal principal, HttpServletResponse res) {
		if (principal.getName() == null) {
			res.setStatus(401);
			return null;
		}
		List<DailyReport> reports = reportSvc.listAllReports(principal.getName());
		return reports;

	}

	@GetMapping("reports/mood/{reportId}")
	public List<MoodEntry> listMoodByReport(Principal principal, @PathVariable int reportId, HttpServletResponse res) {
		if (principal.getName() == null) {
			res.setStatus(401);
			return null;
		}
		List<MoodEntry> moods = moodEntryService.findByDailyReportId(reportId);
		if (moods.isEmpty()) {
			res.setStatus(400);
			return null;
		} else {
			return moods;
		}
	}

	@GetMapping("reports/moods")
	public List<Mood> indexMoods(Principal principal, HttpServletResponse res) {
		if (principal.getName() == null) {
			res.setStatus(401);
			return null;
		}
		List<Mood> moods = moodSvc.index();
		if (moods.isEmpty()) {
			res.setStatus(404);
			return null;
		} else {
			res.setStatus(200);
			return moods;
		}
	}

//	@GetMapping("naps/{reportId}")
//	public Nap showNapByReport(@PathVariable int reportId, Principal principal, HttpServletResponse res) {
//		if (principal.getName() == null) {
//			res.setStatus(401);
//			return null;
//		}
//		Nap nap = napService.napByReportId(reportId);
//		return nap;
//	}

	@GetMapping("reports/{reportId}")
	public DailyReport loadReportToEdit(@PathVariable int reportId) {
		DailyReport reportToEdit = reportSvc.findById(reportId);
		return reportToEdit;
	}

	@PostMapping("reports/{kidId}")
	public DailyReport create(@RequestBody DailyReport dailyreport, @PathVariable int kidId, Principal principal,
			HttpServletResponse res) {
		if (principal.getName().isEmpty()) {
			res.setStatus(401);
			return null;
		}
		res.setStatus(201);
		return reportSvc.create(kidId, principal.getName());
	}

	@PutMapping("reports")
	public DailyReport update(@RequestBody DailyReport dailyReport, Principal principal, HttpServletResponse res) {
		if (principal.getName().isEmpty()) {
			res.setStatus(400);
			return null;
		}
		res.setStatus(200);
		DailyReport report = reportSvc.update(dailyReport);
		if (report == null) {
			res.setStatus(400);
			return null;
		} else {
			res.setStatus(201);
			return report;
		}
	}

	@PostMapping("reports/reportId/{reportId}/moodId/{moodId}")
	public Mood createMood(@RequestBody MoodEntry moodEntry, @PathVariable int moodId, @PathVariable int reportId,
			Principal principal, HttpServletResponse res) {
		if (principal.getName().isEmpty()) {
			res.setStatus(401);
			return null;
		}
		res.setStatus(201);
		return moodSvc.create(moodEntry, reportId, moodId);
	}

	@DeleteMapping("reports/moods/{moodId}/reportId/{reportId}")
	public void deleteMood(@PathVariable int moodId, @PathVariable int reportId, Principal principal,
			HttpServletResponse res) {
		if (principal.getName().isEmpty()) {
			res.setStatus(401);
		}
		if (!moodSvc.delete(moodId, reportId)) {
			res.setStatus(204);
		} else {
			res.setStatus(404);
		}
	}

//	@PutMapping("reports/{reportId}/naps")
//	public Nap createNap(@PathVariable int reportId, @RequestBody Nap nap, Principal principal, HttpServletResponse res) {
//		if (principal.getName().isEmpty()) {
//			res.setStatus(401);
//			return null;
//		}
//		res.setStatus(201);
//		return napService.create(nap, reportId);
//	}

}
