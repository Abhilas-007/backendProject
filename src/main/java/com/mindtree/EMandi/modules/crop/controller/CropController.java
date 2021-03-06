package com.mindtree.EMandi.modules.crop.controller;

import java.util.List;
import java.util.Map;

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

	@Autowired
	CropVarietyConverter cropVarietyConvertor;

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
<<<<<<< HEAD
	public ResponseEntity<CropDto> getCropMSP(@RequestBody CropDto cropDto) {
		Crop crop = cropConverter.dtoToEntity(cropDto);
		crop = cropService.getCropMSP(crop);
		return new ResponseEntity<CropDto>(cropConverter.entityToDto(crop), HttpStatus.FOUND);
=======
	public ResponseEntity<CropDto> getCropMSP(@RequestParam("cropName") String cropName, @RequestParam("adminId") String adminId){
		Crop crop = cropService.getCropMSP(cropName,adminId);
		return new ResponseEntity<CropDto>(cropConverter.entityToDto(crop),HttpStatus.OK);
>>>>>>> 80fcabc0d32d10cd3c9081e727fb198b5b88971b
	}

	@PutMapping("/updateMSP")
<<<<<<< HEAD
	public ResponseEntity<CropDto> updateMSP(@RequestBody CropDto cropDto) {
=======
	public ResponseEntity<CropDto> updateMSP(@RequestBody CropDto cropDto){
		System.out.println(cropDto.getAdminId()+" "+cropDto.getCropMSP()+" "+cropDto.getCropName());
>>>>>>> 80fcabc0d32d10cd3c9081e727fb198b5b88971b
		Crop crop = cropConverter.dtoToEntity(cropDto);
		String message = cropService.updateMSP(crop);
		return new ResponseEntity<CropDto>(cropDto, HttpStatus.OK);
	}

	@GetMapping("/getCropPrice")
	public ResponseEntity<CropVarietyDto> getCropPriceForBuyer(@RequestBody Map<String, String> cropDetail) {
		String cropName = cropDetail.get("cropName");
		String cropClass = cropDetail.get("cropClass");
		String adminId = cropDetail.get("adminId");
		CropVariety cropVariety = cropService.getCropCostForBuyer(cropName, cropClass, adminId);
		CropVarietyDto cropVarietyDTO = cropVarietyConvertor.entityToDto(cropVariety);
		return new ResponseEntity<CropVarietyDto>(cropVarietyDTO, HttpStatus.OK);
	}
	
	

}