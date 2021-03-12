package com.mindtree.EMandi.modules.buyer.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.EMandi.modules.buyer.converter.BuyerConverter;
import com.mindtree.EMandi.modules.buyer.converter.BuyerSignupConverter;
import com.mindtree.EMandi.modules.buyer.dto.BuyerDto;
import com.mindtree.EMandi.modules.buyer.dto.BuyerSignupCode;
import com.mindtree.EMandi.modules.buyer.entity.Buyer;
import com.mindtree.EMandi.modules.buyer.service.BuyerService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/buyer")
public class BuyerController {

	@Autowired
	private BuyerConverter buyerConverter;

	@Autowired
	private BuyerService buyerService;
	@Autowired
	private BuyerSignupConverter signupconverter;
	@PutMapping("/update")
	public ResponseEntity<BuyerDto> updateBuyer(@RequestBody BuyerDto buyer) {

		Buyer buyerEntity = buyerConverter.dtoToEntity(buyer);
		buyerService.updateBuyer(buyerEntity);

		return new ResponseEntity<BuyerDto>(buyer, HttpStatus.OK);

	}

	@GetMapping("/get/{id}")
	public ResponseEntity<BuyerDto> getBuyer(@PathVariable int id) {

		Buyer buyer = buyerService.getBuyer(id);

		return new ResponseEntity<BuyerDto>(buyerConverter.entityToDto(buyer), HttpStatus.OK);
	}

	@PostMapping("/add-buyer")
	public ResponseEntity<Integer> createBuyer(@RequestBody BuyerSignupCode buyer) {

		Buyer buyer1 = signupconverter.dtoToEntity(buyer);
		Buyer buyer2 = buyerService.saveBuyer(buyer1);
		
		HttpHeaders header = new HttpHeaders();
		header.add("desc", "Signup Request being check");
		header.add("userType", "buyer");
		return new ResponseEntity<Integer>(buyer2.getBuyerId(), header, HttpStatus.OK);

		
	}

	@PostMapping("/login")
	public ResponseEntity<String> sayHello(@RequestBody Map<String, String> map) {
		String id = buyerService.validateLogin(map);
		HttpHeaders header = new HttpHeaders();
		header.add("desc", "Login Request being check");
		header.add("userType", "buyer");
		return new ResponseEntity<>(id, header, HttpStatus.OK);

	}

	@GetMapping("/validate/{id}")
	public ResponseEntity<String> validateBuyer(@PathVariable String id) {
		Buyer buyer;
		try{
			 buyer = buyerService.getBuyer(Integer.parseInt(id));
		}catch(Exception e) {HttpHeaders header = new HttpHeaders();
			header.add("desc", "credentials validation");
			header.add("userType", "buyer");
			return new ResponseEntity<>(e.getMessage() + id, header, HttpStatus.OK);
			 
		}
		if (buyer != null) {
			HttpHeaders header = new HttpHeaders();
			header.add("desc", "credentials validation");
			header.add("userType", "buyer");
			return new ResponseEntity<>("" + id, header, HttpStatus.OK);
		} else
			return null;
	}
	@PutMapping("/resetPassword")
	public ResponseEntity<Buyer> resetPassword(@RequestBody Map<String, String> map) {
		
		Buyer buyer = buyerService.updatePassword(map);
		if (buyer != null) {
			HttpHeaders header = new HttpHeaders();
			header.add("desc", "credentials validation");
			header.add("userType", "buyer");
			return new ResponseEntity<>(buyer, header, HttpStatus.OK);
		} else
			return null;
	}
	
}
