package com.mindtree.EMandi.modules.farmer.controller;

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

import com.mindtree.EMandi.exception.FarmerException;
import com.mindtree.EMandi.modules.farmer.entity.Farmer;
import com.mindtree.EMandi.modules.farmer.service.FarmerService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/farmer")
public class FarmerController {

	@Autowired
	FarmerService farmerService;

	@PostMapping("/login")
	public ResponseEntity<String> sayHello(@RequestBody Map<String, String> map) {
		String id = farmerService.validateLogin(map);
		HttpHeaders header = new HttpHeaders();
		header.add("desc", "Login Request being check");
		header.add("userType", "farmer");
		return new ResponseEntity<>(id, header, HttpStatus.OK);

	}
	
	@PostMapping("/createFarmer")
	public ResponseEntity<Object> addPerson(@RequestBody Farmer farmer) throws FarmerException {
		try {
//			Employee employee = employeeConvertor.dtoToEntity(empDto);
			Farmer farmers = farmerService.createFarmer(farmer);
			HttpHeaders header = new HttpHeaders();
			header.add("desc", "Farmer application");
			return new ResponseEntity<Object>(farmers, header, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println("Data not added to database");
			throw new FarmerException("Farmer not added");
		}

	}
}
