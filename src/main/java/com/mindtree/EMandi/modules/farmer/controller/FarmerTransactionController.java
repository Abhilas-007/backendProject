package com.mindtree.EMandi.modules.farmer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.EMandi.exception.ServiceException;
import com.mindtree.EMandi.modules.farmer.converter.FarmerConverter;
import com.mindtree.EMandi.modules.farmer.dto.FarmerTransactionDto;
import com.mindtree.EMandi.modules.farmer.entity.FarmerTransaction;
import com.mindtree.EMandi.modules.farmer.repository.FarmerTransactionRepository;
import com.mindtree.EMandi.modules.farmer.service.FarmerService;



@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/farmerTransaction")
public class FarmerTransactionController {

	@Autowired
	private FarmerConverter converter;
	@Autowired
	private FarmerTransactionRepository rep;
	@Autowired
	private FarmerService service;
	@GetMapping("/getAllCrops/{mandiPincode}")
	public ResponseEntity<List<FarmerTransactionDto>> getAllCrops(@PathVariable (value="mandiPincode") int mandiPincode) {
		List<FarmerTransaction> farmer=null;
		try {
			farmer = service.findByMandiPincode(mandiPincode);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<FarmerTransactionDto> farmerDtos = converter.entityToDtoForListTrans(farmer);
		HttpHeaders header = new HttpHeaders();
		header.add("Description", "Getting all crops");
		return ResponseEntity.status(HttpStatus.OK).headers(header).body(farmerDtos);
	}
}
