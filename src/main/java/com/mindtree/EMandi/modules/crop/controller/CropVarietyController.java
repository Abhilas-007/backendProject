package com.mindtree.EMandi.modules.crop.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.EMandi.exception.ServiceException;
import com.mindtree.EMandi.modules.crop.converter.CropVarietyConverter;
import com.mindtree.EMandi.modules.crop.dto.CropVarietyDto;
import com.mindtree.EMandi.modules.crop.entity.CropVariety;
import com.mindtree.EMandi.modules.crop.service.CropService;

@RestController
@RequestMapping("/cropVariety")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CropVarietyController {

	@Autowired
	CropService cropService;
	@Autowired
	CropVarietyConverter cropVarietyConvertor;

	@PutMapping("/updateCropPrice")
	public ResponseEntity<CropVarietyDto> updateCropPriceForBuyer(@RequestBody Map<String, String> cropDetail)
			throws ServiceException {
        CropVariety cropVariety= null;
		String cropName = cropDetail.get("cropName");
		String cropClass = cropDetail.get("cropClass");
		String cropPrice = cropDetail.get("cropPrice");
		String adminId = cropDetail.get("adminId");
		try {
			 cropVariety = cropService.updateCropCostForBuyer(cropName, cropClass, cropPrice, adminId);
		} catch (Exception e) {
			throw new ServiceException();
		}

		CropVarietyDto cropVarietyDTO = cropVarietyConvertor.entityToDto(cropVariety);
		return new ResponseEntity<CropVarietyDto>(cropVarietyDTO, HttpStatus.OK);
	}
}
//	@PutMapping("/updateMSP")
//	public ResponseEntity<CropVarietyDto> updateMSP(@RequestBody CropVarietyDto cropvarietyDto) {
//		System.out.println(cropVarietyDto.getAdminId() + " " + cropDto.getCropMSP() + " " + cropDto.getCropName());
//		CropVariety crop = cropVarietyConvertor.dtoToEntity(cropDto);
//		String message = cropService.updateMSP(crop);
//		return new ResponseEntity<CropDto>(cropDto, HttpStatus.OK);
//	}
