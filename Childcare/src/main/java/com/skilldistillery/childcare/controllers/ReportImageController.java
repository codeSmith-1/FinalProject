package com.skilldistillery.childcare.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.childcare.entities.ReportImage;
import com.skilldistillery.childcare.services.ReportImageServiceImpl;

@RestController
@RequestMapping("api/")
@CrossOrigin({"*", "http://localhost/"})
public class ReportImageController {
	
	@Autowired
	public ReportImageServiceImpl imageService;
	
	@GetMapping("images")
	public List<ReportImage> listImages(Principal principal){
		List<ReportImage> images = imageService.showAll(principal.getName());
		return images;
	}
	
	@GetMapping("images/{imageId}")
	public ReportImage showImage(@PathVariable("imageId") int imageId) {
		return imageService.show(imageId);
	}
	

}
