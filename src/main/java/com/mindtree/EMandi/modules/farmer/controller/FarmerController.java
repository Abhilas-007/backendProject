package com.mindtree.EMandi.modules.farmer.controller;

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

import com.mindtree.EMandi.exception.FarmerException;
import com.mindtree.EMandi.exception.ServiceException;
import com.mindtree.EMandi.modules.clerk.entity.Clerk;
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
	public ResponseEntity<Integer> addPerson(@RequestBody Farmer farmer) throws FarmerException {
		try {
//			Employee employee = employeeConvertor.dtoToEntity(empDto);
			Farmer farmers = farmerService.createFarmer(farmer);
			HttpHeaders header = new HttpHeaders();
			header.add("desc", "Farmer application");
			return new ResponseEntity<Integer>(farmers.getFarmerId(), header, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println("Data not added to database");
			throw new FarmerException("Farmer not added");
		}

	}

	@GetMapping("/validate/{id}")
	public ResponseEntity<String> validateFarmer(@PathVariable int id) {
		Farmer farmer;
		try {
			farmer = farmerService.getFarmer(id);
		} catch (FarmerException e) {
			return null;
		}
		if (farmer != null) {
			HttpHeaders header = new HttpHeaders();
			header.add("desc", "credentials validation");
			header.add("userType", "farmer");
			return new ResponseEntity<>("" + id, header, HttpStatus.OK);
		} else
			return null;
	}

	@PutMapping("/resetPassword")
	public ResponseEntity<Farmer> resetPassword(@RequestBody Map<String, String> map) {

		Farmer farmer;
		try {
			farmer = farmerService.updatePassword(map);
		} catch (FarmerException e) {
			return null;
		}
		if (farmer != null) {
			HttpHeaders header = new HttpHeaders();
			header.add("desc", "credentials validation");
			header.add("userType", "farmer");
			return new ResponseEntity<>(farmer, header, HttpStatus.OK);
		} else
			return null;
	}

	@PostMapping("/sqCheck")
	public ResponseEntity<String> securityCheck(@RequestBody Map<String, String> map) {
		String msg = null;
		HttpHeaders header = new HttpHeaders();
		header.add("desc", "credentials validation");
		header.add("userType", "farmer");
		try {
			msg = farmerService.validateQA(map);
		} catch (Exception e) {
			return new ResponseEntity<>(null, header, HttpStatus.BAD_REQUEST);
		}
		if(msg==null)
			return new ResponseEntity<>("security question didnt match", header, HttpStatus.NOT_FOUND);
		else
		return new ResponseEntity<>(msg, header, HttpStatus.OK);
	}
}
