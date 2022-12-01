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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.childcare.entities.ReportImage;
import com.skilldistillery.childcare.services.ReportImageServiceImpl;

@RestController
@RequestMapping("api/")
@CrossOrigin({ "*", "http://localhost/" })
public class ReportImageController {

	@Autowired
	public ReportImageServiceImpl imageService;

//	@GetMapping("images")
//	public List<ReportImage> listImages(Principal principal, @PathVariable int id, HttpServletResponse res){
//		if (principal.getName().isEmpty()) {
//			res.setStatus(401);
//			return null;
//		}
//		return imageService.showAll(int id);
//	}

	@GetMapping("images/reportId/{reportId}")
	public List<ReportImage> showImagesByReportId(Principal principal, @PathVariable int reportId,
			HttpServletResponse res) {
		if (principal.getName().isEmpty()) {
			res.setStatus(401);
			return null;
		}
		List<ReportImage> images = imageService.imagesByReportId(reportId);
		if (images.isEmpty()) {
			res.setStatus(404);
			return null;
		} else {
			return images;
		}
	}

	@PostMapping("reportImage/reportId/{reportId}/staffId/{staffId}")
	public ReportImage create(Principal principal, @RequestBody ReportImage image, @PathVariable int reportId,
			@PathVariable int staffId, HttpServletResponse res) {
		if (principal.getName().isEmpty()) {
			res.setStatus(401);
			return null;
		}

		return imageService.create(staffId, image, reportId);
	}

	@DeleteMapping("reportImage/imageId/{imageId}")
	public void deleteImage(Principal principal, @PathVariable int imageId, HttpServletResponse res) {
		if (principal.getName().isEmpty()) {
			res.setStatus(401);
		}
		if (imageService.delete(imageId)) {
			res.setStatus(204);
		} else {
			res.setStatus(404);
		}
	}
}
