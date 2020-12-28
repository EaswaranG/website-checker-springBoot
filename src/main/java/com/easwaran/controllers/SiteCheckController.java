package com.easwaran.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.easwaran.services.ControllerService;


@RestController
public class SiteCheckController {

	@Autowired
	ControllerService controllerService;
	
	@GetMapping(path = "/check")
	public String isSiteUp(@RequestParam String url) {
		return controllerService.pingSite(url);
	}
}
