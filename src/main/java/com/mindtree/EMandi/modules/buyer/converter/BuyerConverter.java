package com.mindtree.EMandi.modules.buyer.converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.mindtree.EMandi.modules.buyer.dto.BuyerDto;
import com.mindtree.EMandi.modules.buyer.entity.Buyer;

@Component
public class BuyerConverter {
	
	public Buyer dtoToEntity(BuyerDto buyerDto) {
		
		ModelMapper mapper=new ModelMapper();
		Buyer buyer=mapper.map(buyerDto, Buyer.class);
		
		return buyer;
	}
	
	public BuyerDto entityToDto(Buyer buyer) {
		
		ModelMapper mapper=new ModelMapper();
		BuyerDto buyerDto = mapper.map(buyer, BuyerDto.class);
		
		return buyerDto;
	}
}
