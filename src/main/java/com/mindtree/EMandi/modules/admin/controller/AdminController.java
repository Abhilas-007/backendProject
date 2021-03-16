package com.mindtree.EMandi.modules.admin.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.EMandi.exception.ServiceException;
import com.mindtree.EMandi.modules.admin.converter.AdminConverter;
import com.mindtree.EMandi.modules.admin.dto.AdminDto;
import com.mindtree.EMandi.modules.admin.entity.Admin;
import com.mindtree.EMandi.modules.admin.service.AdminService;
import com.mindtree.EMandi.modules.buyer.converter.BuyerConverter;
import com.mindtree.EMandi.modules.buyer.dto.BuyerDto;
import com.mindtree.EMandi.modules.buyer.entity.Buyer;
import com.mindtree.EMandi.modules.clerk.converter.ClerkConverter;
import com.mindtree.EMandi.modules.clerk.dto.ClerkDto;
import com.mindtree.EMandi.modules.clerk.entity.Clerk;
import com.mindtree.EMandi.modules.clerk.repository.ClerkRepository;
import com.mindtree.EMandi.modules.clerk.service.ClerkService;
import com.mindtree.EMandi.modules.crop.converter.CropConverter;
import com.mindtree.EMandi.modules.crop.dto.CropDto;
import com.mindtree.EMandi.modules.crop.entity.Crop;
import com.mindtree.EMandi.modules.crop.service.CropService;
import com.mindtree.EMandi.modules.farmer.converter.FarmerConverter;
import com.mindtree.EMandi.modules.farmer.dto.FarmerDto;
import com.mindtree.EMandi.modules.mandi.repository.MandiRepository;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	AdminService adminService;
	@Autowired
	private AdminConverter adminConverter;
    @Autowired
    private CropConverter cropConverter;
    @Autowired
    private CropService cropService;
    @Autowired
    private MandiRepository mandiRepository;
    @Autowired
    private ClerkRepository clerkRepository;
	@Autowired
	private FarmerConverter farmerConverter;
	@Autowired
	private BuyerConverter buyerConverter;
	@Autowired
	private ClerkService clerkservice;
	@Autowired
	private ClerkConverter clerkConverter;
	@PostMapping("/login")
	public ResponseEntity<String> sayHello(@RequestBody Map<String, String> map) {
		String id = adminService.validateLogin(map);
		HttpHeaders header = new HttpHeaders();
		header.add("desc", "Login Request being check");
		header.add("userType", "admin");
		return new ResponseEntity<>(id, header, HttpStatus.OK);

	}

	@PostMapping("/addAdmin")
	public ResponseEntity<String> addAdmin(@RequestBody Admin admin) {
		String message = "";
		try {
			message = adminService.addAdmin(admin);
		} catch (ServiceException e) {
			message = "Failed to add admin.";
			HttpHeaders header = new HttpHeaders();
			header.add("Description", "Adding an admin");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).headers(header).body(message);
		}
		HttpHeaders header = new HttpHeaders();
		header.add("Description", "Adding an admin");
		return ResponseEntity.status(HttpStatus.CREATED).headers(header).body(message);
	}

	@GetMapping("/getAllAdmins")
	public ResponseEntity<List<AdminDto>> getAllAdmins() {
		List<Admin> admins = null;
		try {
			admins = adminService.getAllAdmins();
		} catch (ServiceException e) {
			HttpHeaders header = new HttpHeaders();
			header.add("Description", "Getting all admins");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).headers(header).body(null);
		}
		List<AdminDto> adminsDtos = adminConverter.entityToDto(admins);
		HttpHeaders header = new HttpHeaders();
		header.add("Description", "Getting all admins");
		return ResponseEntity.status(HttpStatus.OK).headers(header).body(adminsDtos);
	}


	@GetMapping("/validate/{id}")
	public ResponseEntity<String> validateAdmin(@PathVariable String id) {
		Admin admin;
		try {
			admin = adminService.getAdmin(id);
		} catch (ServiceException e) {
			return null;
		}
		if (admin != null) {
			HttpHeaders header = new HttpHeaders();
			header.add("desc", "credentials validation");
			header.add("userType", "admin");
			return new ResponseEntity<>(id, header, HttpStatus.OK);
		} else
			return null;
	}

	@PutMapping("/resetPassword")
	public ResponseEntity<Admin> resetPassword(@RequestBody Map<String, String> map) {
		Admin admin;
		try {
			admin = adminService.updatePassword(map);
		} catch (ServiceException e) {
			return null;
		}
		if (admin != null) {
			HttpHeaders header = new HttpHeaders();
			header.add("desc", "credentials validation");
			header.add("userType", "admin");
			return new ResponseEntity<>(admin, header, HttpStatus.OK);
		} else
			return null;
	}
	@GetMapping("/getAllCrops/{adminId}")
	public ResponseEntity<List<CropDto>> getCropByAdminId(@PathVariable (value="adminId") String adminId){
	
		List<Crop>	crops=cropService.findCropByAdminId(adminId);
		HttpHeaders header = new HttpHeaders();
		header.add("Description", "Getting all crops");
		List<CropDto> crop=cropConverter.entityToDto(crops);
		return ResponseEntity.status(HttpStatus.OK).headers(header).body(crop);
	}
	 
	@GetMapping("/getAllClerks/{adminId}")
	public List<Clerk> getAll(@PathVariable (value="adminId") String adminId){
		
		List<String> mandi=mandiRepository.findByAdminId(adminId);
	List<Clerk> clerk=clerkRepository.findAllById(mandi);
		return clerk;
	}
	
	@PostMapping("/getAllFarmersByAdminIdAndMandiPincode")
	public ResponseEntity<List<FarmerDto>> getFarmerListByAdminIdAndMandiPincode(@RequestBody Map<String,String> input)
	{
		String adminId = input.get("adminId");
		String pincode = input.get("mandiPincode");
		int mandiPincode = Integer.parseInt(pincode);
		List<FarmerDto> farmersDtos = null;
		HttpHeaders header = new HttpHeaders();
		try
		{
			farmersDtos = farmerConverter.entityToDtoForList(adminService.getFarmersByAdminIdAndMandiPincode(adminId, mandiPincode));
		}
		catch (ServiceException e) 
		{
			header.add("Description", "Error in getting all farmers");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).headers(header).body(null);
		}
		header.add("Description", "Getting all farmers");
		return ResponseEntity.status(HttpStatus.OK).headers(header).body(farmersDtos);
	}
	
	@PostMapping("/getAllBuyersByAdminIdAndMandiPincode")
	public ResponseEntity<List<BuyerDto>> getBuyerListByAdminIdAndMandiPincode(@RequestBody Map<String,String> input)
	{
		String adminId = input.get("adminId");
		String pincode = input.get("mandiPincode");
		int mandiPincode = Integer.parseInt(pincode);
		List<BuyerDto> buyersDtos = null;
		HttpHeaders header = new HttpHeaders();
		try
		{
			buyersDtos = buyerConverter.entityToDtoForList(adminService.getBuyersByAdminIdAndMandiPincode(adminId, mandiPincode));
		}
		catch (ServiceException e) 
		{
			header.add("Description", "Error in getting all buyers");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).headers(header).body(null);
		}
		header.add("Description", "Getting all buyers");
		return ResponseEntity.status(HttpStatus.OK).headers(header).body(buyersDtos);
	}
	
	@PostMapping("/passwordMail")
	public ResponseEntity<String> mailPassword(@RequestBody Map<String, String> map){
		HttpHeaders header = new HttpHeaders();
		header.add("desc", "sending password in mail");
		header.add("userType", "Admin");
		String msg;
		try {
			msg = adminService.passwordMail(map);
		} catch (ServiceException e) {
			return new ResponseEntity<>(null, header, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(msg, header, HttpStatus.OK);

	}
	@GetMapping("/getClerkById/{clerkId}")
	public ResponseEntity<ClerkDto> getClerk(@PathVariable String clerkId ) {

		Clerk clerk=null;
		HttpHeaders header = new HttpHeaders();
		try {
			clerk = clerkservice.getClerk(clerkId);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			header.add("Description", "Error in getting all buyers");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).headers(header).body(null);
		}
		header.add("Description", "Getting all farmers");
		return ResponseEntity.status(HttpStatus.OK).headers(header).body(clerkConverter.entityToDto(clerk));
		
	}
	@PutMapping("/updateclerk")
	public ResponseEntity<ClerkDto> updateclerk(@RequestBody ClerkDto clerk) {

		Clerk clerk1 = clerkConverter.dtoToEntity(clerk);
		
		clerkRepository.save(clerk1);

		return new ResponseEntity<ClerkDto>(clerkConverter.entityToDto(clerk1), HttpStatus.OK);

	}

	
	@GetMapping("/getAdminByState")
	public ResponseEntity<AdminDto> getAdminByState(@RequestBody Map<String,String> map){
		Admin admin;
		AdminDto adminDto;
		try {
			admin = adminService.getAdminByState(map.get("state"));
		} catch (ServiceException e) {
			return null;
		}
		if (admin != null) {
			adminDto=adminConverter.entityToDto(admin);
			HttpHeaders header = new HttpHeaders();
			header.add("desc", "Admin Found");
			header.add("userType", "admin");
			return new ResponseEntity<>(adminDto, header, HttpStatus.OK);
		} else
			return null;
		
	}
}
