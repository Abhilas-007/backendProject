package com.mindtree.EMandi.modules.crop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.EMandi.modules.crop.dto.CropDto;
import com.mindtree.EMandi.modules.crop.service.CropService;

@RestController
@RequestMapping("/crop")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CropController {
	@Autowired
	private CropService cropService;

	@PostMapping("/addCrop")
	public ResponseEntity<String> addCrop(@RequestBody CropDto cropDto) {
		String message = cropService.addCrop(cropDto);
		HttpHeaders header = new HttpHeaders();
		header.add("Description", "Adding a crop");
		return ResponseEntity.status(HttpStatus.CREATED).headers(header).body(message);
	}

	@GetMapping("/getAllCrops")
	public ResponseEntity<List<CropDto>> getAllCrops() {
		List<CropDto> cropsDtos = cropService.getAllCrops();
		HttpHeaders header = new HttpHeaders();
		header.add("Description", "Getting all crops");
		return ResponseEntity.status(HttpStatus.OK).headers(header).body(cropsDtos);
	}
}
