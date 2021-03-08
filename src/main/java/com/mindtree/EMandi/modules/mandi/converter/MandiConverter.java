package com.mindtree.EMandi.modules.mandi.converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.mindtree.EMandi.modules.mandi.dto.MandiDto;
import com.mindtree.EMandi.modules.mandi.entity.Mandi;

@Component
public class MandiConverter {
	
	public MandiDto entityToDto(Mandi mandi) {
		ModelMapper mapper=new ModelMapper();
		MandiDto mandiDto = mapper.map(mandi, MandiDto.class);
		return mandiDto;
	}
	
	public Mandi dtoToEntity(MandiDto mandiDto) {
		ModelMapper mapper = new ModelMapper();
		Mandi mandi = mapper.map(mandiDto, Mandi.class);
		return mandi;
	}

}
