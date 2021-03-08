package com.mindtree.EMandi.modules.farmer.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.mindtree.EMandi.modules.farmer.dto.FarmerDto;
import com.mindtree.EMandi.modules.farmer.entity.Farmer;

@Component
public class FarmerConverter 
{
	public FarmerDto entityToDto(Farmer farmer) 
	{
		ModelMapper mapper = new ModelMapper();
		FarmerDto farmerDto = mapper.map(farmer, FarmerDto.class);
		return farmerDto;
	}

	public Farmer dtoToEntity(FarmerDto farmerDto) 
	{
		ModelMapper mapper = new ModelMapper();
		Farmer farmer = mapper.map(farmerDto, Farmer.class);
		return farmer;
	}

	public List<FarmerDto> entityToDtoForList(List<Farmer> farmer) 
	{
		return farmer.stream().map(x -> entityToDto(x)).collect(Collectors.toList());
	}
}
