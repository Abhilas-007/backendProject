package com.mindtree.EMandi.modules.superadmin.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.EMandi.modules.superadmin.service.SuperAdminService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/sAdmin")
public class SuperAdminController {

	@Autowired
	SuperAdminService sAdminService;

	@PostMapping("/login")
	public ResponseEntity<String> sayHello(@RequestBody Map<String, String> map) {
		String id = sAdminService.validateLogin(map);
		HttpHeaders header = new HttpHeaders();
		header.add("desc", "Login Request being check");
		header.add("userType", "sAdmin");
		return new ResponseEntity<>(id, header, HttpStatus.OK);

	}
}
