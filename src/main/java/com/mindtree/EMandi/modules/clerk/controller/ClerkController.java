package com.mindtree.EMandi.modules.clerk.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.EMandi.exception.ServiceException;
import com.mindtree.EMandi.modules.admin.entity.Admin;
import com.mindtree.EMandi.modules.clerk.entity.Clerk;
import com.mindtree.EMandi.modules.clerk.service.ClerkService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/clerk")
public class ClerkController {

	@Autowired
	ClerkService clerkService;

	@PostMapping("/login")
	public ResponseEntity<String> sayHello(@RequestBody Map<String, String> map) {
		String id = clerkService.validateLogin(map);
		HttpHeaders header = new HttpHeaders();
		header.add("desc", "Login Request being check");
		header.add("userType", "clerk");
		return new ResponseEntity<>(id, header, HttpStatus.OK);

	}

	@GetMapping("/validate/{id}")
	public ResponseEntity<String> validateClerk(@PathVariable String id) {
		Clerk clerk;
		try {
			clerk = clerkService.getClerk(id);
		} catch (ServiceException e) {
			return null;
		}
		if (clerk != null) {
			HttpHeaders header = new HttpHeaders();
			header.add("desc", "credentials validation");
			header.add("userType", "clerk");
			return new ResponseEntity<>(id, header, HttpStatus.OK);
		} else
			return null;
	}

	@PutMapping("/resetPassword")
	public ResponseEntity<Clerk> resetPassword(@RequestBody Map<String, String> map) {

		Clerk clerk;
		try {
			clerk = clerkService.updatePassword(map);
		} catch (ServiceException e) {
			return null;
		}
		if(clerk != null) {
			HttpHeaders header = new HttpHeaders();
			header.add("desc", "credentials validation");
			header.add("userType", "clerk");
			return new ResponseEntity<>(clerk, header, HttpStatus.OK);
		} else
			return null;
	}
	
	@PostMapping("/validateFarmer")
	public boolean validateFarmerId(@RequestBody int farmerId)
	{
		return clerkService.validateFarmerId(farmerId);
	}
}
