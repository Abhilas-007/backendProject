package com.mindtree.EMandi.modules.buyer.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.EMandi.modules.buyer.converter.BuyerTransactionConverter;
import com.mindtree.EMandi.modules.buyer.dto.BuyerTransactionDto;
import com.mindtree.EMandi.modules.buyer.entity.BuyerTransaction;
import com.mindtree.EMandi.modules.buyer.service.BuyerService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/buyerTransaction")
public class BuyerTransactionController {

	@Autowired
	BuyerService buyerService;

	@Autowired
	BuyerTransactionConverter buyerConvertor;

	@GetMapping("getTransactions/{id}")
	public ResponseEntity<List<BuyerTransactionDto>> getTransactions(@PathVariable int id) {
		List<BuyerTransaction> transactions = buyerService.getTransactions(id);

		List<BuyerTransactionDto> transactionDtos = new ArrayList<>();
		for (BuyerTransaction buyer : transactions) {
			transactionDtos.add(buyerConvertor.transactionToDto(buyer));
		}
		HttpHeaders header = new HttpHeaders();
		header.add("Description", "Getting all previous transaction details");
		return ResponseEntity.status(HttpStatus.OK).headers(header).body(transactionDtos);
	}
}
