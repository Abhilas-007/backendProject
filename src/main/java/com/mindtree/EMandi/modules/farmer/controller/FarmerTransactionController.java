package com.mindtree.EMandi.modules.farmer.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.EMandi.exception.FarmerException;
import com.mindtree.EMandi.modules.farmer.converter.FarmerConverter;
import com.mindtree.EMandi.modules.farmer.dto.FarmerTransactionDto;
import com.mindtree.EMandi.modules.farmer.dto.FarmerTransactionDto1;
import com.mindtree.EMandi.modules.farmer.dto.TransactionDto;
import com.mindtree.EMandi.modules.farmer.entity.Farmer;
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
	private FarmerService farmerService;
	@GetMapping("/getAllCrops/{mandiPincode}")
	public ResponseEntity<List<FarmerTransactionDto>> getAllCrops(@PathVariable (value="mandiPincode") int mandiPincode) {
		List<FarmerTransaction> farmer=rep.findByMandiPincode(mandiPincode);
		List<FarmerTransactionDto> farmerDtos = converter.entityToDtoForListTrans(farmer);
		HttpHeaders header = new HttpHeaders();
		header.add("Description", "Getting all crops");
		return ResponseEntity.status(HttpStatus.OK).headers(header).body(farmerDtos);
	}
	@GetMapping("/{id}")
	public ResponseEntity<List<TransactionDto>> getTransactions(@PathVariable int id) {
		List<FarmerTransaction> transactions=rep.findByFarmerId(id);
		List<TransactionDto> transactionDtos = new ArrayList<>();
		for(FarmerTransaction f:transactions) {
			transactionDtos.add(converter.transactionToDtoTrans(f));
		}
		HttpHeaders header = new HttpHeaders();
		header.add("Description", "Getting all transaction details");
		return ResponseEntity.status(HttpStatus.OK).headers(header).body(transactionDtos);
	}
	
	@GetMapping("/byId")
	public ResponseEntity<List<FarmerTransactionDto1>> getTransactions(@RequestParam("clerkId") String clerkId, @RequestParam("farmerId") int farmerId){
		List<FarmerTransaction> transactions = farmerService.getTransactions(clerkId, farmerId);
		return new ResponseEntity<List<FarmerTransactionDto1>>(converter.entityToDto(transactions),HttpStatus.OK);
	}
	
}
