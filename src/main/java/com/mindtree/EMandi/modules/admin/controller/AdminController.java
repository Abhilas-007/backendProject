package com.mindtree.EMandi.modules.admin.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.EMandi.exception.ServiceException;
import com.mindtree.EMandi.modules.admin.converter.AdminConverter;
import com.mindtree.EMandi.modules.admin.dto.AdminDto;
import com.mindtree.EMandi.modules.admin.entity.Admin;
import com.mindtree.EMandi.modules.admin.service.AdminService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	AdminService adminService;
	
	@Autowired 
	private AdminConverter adminConverter;

	@PostMapping("/login")
	public ResponseEntity<String> sayHello(@RequestBody Map<String, String> map) {
		String id = adminService.validateLogin(map);
		HttpHeaders header = new HttpHeaders();
		header.add("desc", "Login Request being check");
		header.add("userType", "admin");
		return new ResponseEntity<>(id, header, HttpStatus.OK);
		
	}
	
	@PostMapping("/addAdmin")
	public ResponseEntity<String> addAdmin(@RequestBody Admin admin)
	{
		String message = "";
		try
		{
			message = adminService.addAdmin(admin);
		}
		catch(ServiceException e)
		{
			message = "Failed to add admin.";
			HttpHeaders header = new HttpHeaders();
			header.add("Description","Adding an admin");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).headers(header).body(message);
		}
		HttpHeaders header = new HttpHeaders();
		header.add("Description","Adding an admin");
		return ResponseEntity.status(HttpStatus.CREATED).headers(header).body(message);
	}
	
	@GetMapping("/getAllAdmins")
	public ResponseEntity<List<AdminDto>> getAllAdmins()
	{
		List<Admin> admins = null;
		try
		{
			admins = adminService.getAllAdmins();
		}
		catch(ServiceException e)
		{
			HttpHeaders header = new HttpHeaders();
			header.add("Description","Getting all admins");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).headers(header).body(null);
		}
		List<AdminDto> adminsDtos = adminConverter.entityToDto(admins);
		HttpHeaders header = new HttpHeaders();
		header.add("Description","Getting all admins");
		return ResponseEntity.status(HttpStatus.OK).headers(header).body(adminsDtos);
	}
}
