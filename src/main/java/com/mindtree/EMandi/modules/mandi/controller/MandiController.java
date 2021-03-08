package com.mindtree.EMandi.modules.mandi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.EMandi.modules.mandi.converter.MandiConverter;
import com.mindtree.EMandi.modules.mandi.dto.MandiDto;
import com.mindtree.EMandi.modules.mandi.entity.Mandi;
import com.mindtree.EMandi.modules.mandi.service.MandiService;

@RestController
@RequestMapping("/mandi")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MandiController {
	
	@Autowired
	private MandiService mandiService;
	
	@Autowired
	private MandiConverter converter;
	
	@PostMapping("/add")
	public ResponseEntity<MandiDto> addMandi(@RequestBody MandiDto mandiDto){
		Mandi mandi = converter.dtoToEntity(mandiDto);
		mandi = mandiService.addMandi(mandi);
		return new ResponseEntity<MandiDto>(mandiDto,HttpStatus.OK);
	}
	
}
