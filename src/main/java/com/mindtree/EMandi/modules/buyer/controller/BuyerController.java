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
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.EMandi.modules.buyer.converter.BuyerConverter;
import com.mindtree.EMandi.modules.buyer.dto.BuyerDto;
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

	@PutMapping("/update")
	public ResponseEntity<BuyerDto> updateBuyer(@RequestBody BuyerDto buyer) {

		Buyer buyerEntity = buyerConverter.dtoToEntity(buyer);
		buyerService.updateBuyer(buyerEntity);

		return new ResponseEntity<BuyerDto>(buyer, HttpStatus.OK);

	}

	@GetMapping("/get/{id}")
	public BuyerDto getBuyer(@PathVariable int id) {

		Buyer buyer = buyerService.getBuyer(id);

		return buyerConverter.entityToDto(buyer);
	}

	@PostMapping("/add-buyer")
	public String createEmployee(@RequestBody BuyerDto buyer) {

		Buyer buyer1 = buyerConverter.dtoToEntity(buyer);
		Buyer buyer2 = buyerService.saveBuyer(buyer1);
		return "Data saved";

	}
	@PostMapping("/login")
	public ResponseEntity<String> sayHello(@RequestBody Map<String, String> map) {
		String id = buyerService.validateLogin(map);
		HttpHeaders header = new HttpHeaders();
		header.add("desc", "Login Request being check");
		header.add("userType", "buyer");
		return new ResponseEntity<>(id, header, HttpStatus.OK);

	}

}
