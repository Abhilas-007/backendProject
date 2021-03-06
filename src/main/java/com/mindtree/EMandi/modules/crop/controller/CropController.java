package com.mindtree.EMandi.modules.crop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.EMandi.modules.crop.converter.CropConverter;
import com.mindtree.EMandi.modules.crop.converter.CropVarietyConverter;
import com.mindtree.EMandi.modules.crop.dto.CropDto;
import com.mindtree.EMandi.modules.crop.dto.CropVarietyDto;
import com.mindtree.EMandi.modules.crop.entity.Crop;
import com.mindtree.EMandi.modules.crop.entity.CropVariety;
import com.mindtree.EMandi.modules.crop.service.CropService;

@RestController
@RequestMapping("/crop")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CropController {
	@Autowired
	private CropService cropService;
	
	@Autowired
	private CropConverter cropConverter;

	@Autowired CropVarietyConverter cropVarietyConvertor;
	@PostMapping("/addCrop")
	public ResponseEntity<String> addCrop(@RequestBody CropDto cropDto) {
		Crop crop = cropConverter.dtoToEntity(cropDto);
		String message = cropService.addCrop(crop);
		HttpHeaders header = new HttpHeaders();
		header.add("Description", "Adding a crop");
		return ResponseEntity.status(HttpStatus.CREATED).headers(header).body(message);
	}

	@GetMapping("/getAllCrops")
	public ResponseEntity<List<CropDto>> getAllCrops() {
		List<Crop> crops = cropService.getAllCrops();
		List<CropDto> cropsDtos = cropConverter.entityToDtoForList(crops);
		HttpHeaders header = new HttpHeaders();
		header.add("Description", "Getting all crops");
		return ResponseEntity.status(HttpStatus.OK).headers(header).body(cropsDtos);
	}
	
	@GetMapping("/getCropMSP")
	public ResponseEntity<CropDto> getCropMSP(@RequestParam("cropName") String cropName, @RequestParam("adminId") String adminId){
		Crop crop = cropService.getCropMSP(cropName,adminId);
		return new ResponseEntity<CropDto>(cropConverter.entityToDto(crop),HttpStatus.OK);
	}
	
	@PutMapping("/updateMSP")
	public ResponseEntity<CropDto> updateMSP(@RequestBody CropDto cropDto){
		System.out.println(cropDto.getAdminId()+" "+cropDto.getCropMSP()+" "+cropDto.getCropName());
		Crop crop = cropConverter.dtoToEntity(cropDto);
		String message = cropService.updateMSP(crop);
		return new ResponseEntity<CropDto>(cropDto,HttpStatus.OK);
	}
	
	@GetMapping("/getCropPrice")
	public ResponseEntity<CropVarietyDto> getCropPriceForBuyer(@RequestParam("cropName") String cropName, @RequestParam("cropClass") 
	String cropClass,@RequestParam("adminId") String adminId ) {
		CropVariety cropVariety = cropService.getCropCostForBuyer(cropName, cropClass, adminId);
		CropVarietyDto cropVarietyDTO = cropVarietyConvertor.entityToDto(cropVariety);
		return new ResponseEntity<CropVarietyDto>(cropVarietyDTO , HttpStatus.OK);
	}
	
}
