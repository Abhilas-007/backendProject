package com.mindtree.EMandi.modules.buyer.converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.mindtree.EMandi.modules.buyer.dto.BuyerTransactionDto;
import com.mindtree.EMandi.modules.buyer.entity.BuyerTransaction;

@Component
public class BuyerTransactionConverter {
	public BuyerTransactionDto transactionToDto(BuyerTransaction buyerTransaction) 
	{
		ModelMapper mapper = new ModelMapper();
		BuyerTransactionDto TransactionDto = mapper.map(buyerTransaction,BuyerTransactionDto.class);
		return TransactionDto;
	}
}
