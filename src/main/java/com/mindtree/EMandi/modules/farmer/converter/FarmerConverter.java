package com.mindtree.EMandi.modules.farmer.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.mindtree.EMandi.modules.farmer.dto.FarmerDto;
import com.mindtree.EMandi.modules.farmer.dto.FarmerTransactionDto;
import com.mindtree.EMandi.modules.farmer.entity.Farmer;
import com.mindtree.EMandi.modules.farmer.entity.FarmerTransaction;

@Component
public class FarmerConverter 
{
	public static FarmerDto entityToDto(Farmer farmer) 
	{
		ModelMapper mapper = new ModelMapper();
		FarmerDto farmerDto = mapper.map(farmer, FarmerDto.class);
		return farmerDto;
	}

	public static Farmer dtoToEntity(FarmerDto farmerDto) 
	{
		ModelMapper mapper = new ModelMapper();
		Farmer farmer = mapper.map(farmerDto, Farmer.class);
		return farmer;
	}

	public List<FarmerDto> entityToDtoForList(List<Farmer> farmer) 
	{
		return farmer.stream().map(x -> entityToDto(x)).collect(Collectors.toList());
	}
	
	public FarmerTransactionDto entityToDtoTrans(FarmerTransaction farmerTrans) 
	{
		ModelMapper mapper = new ModelMapper();
		FarmerTransactionDto farmerTransDto = mapper.map(farmerTrans, FarmerTransactionDto.class);
		return farmerTransDto;
	}

	public FarmerTransaction dtoToEntityTrans(FarmerTransactionDto farmerTransDto) 
	{
		ModelMapper mapper = new ModelMapper();
		FarmerTransaction farmerTrans = mapper.map(farmerTransDto, FarmerTransaction.class);
		return farmerTrans;
	}
	public List<FarmerTransactionDto> entityToDtoForListTrans(List<FarmerTransaction> farmer1) 
	{
		return farmer1.stream().map(x -> entityToDtoTrans(x)).collect(Collectors.toList());
	}
	
}
