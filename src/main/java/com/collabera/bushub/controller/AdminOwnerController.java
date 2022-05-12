package com.collabera.bushub.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.collabera.bushub.dto.Bus;
import com.collabera.bushub.dto.AdminOwner;
import com.collabera.bushub.jwtUtil.JwtUtil;
import com.collabera.bushub.model.AuthenticateRequest;
import com.collabera.bushub.model.AuthenticateResponse;
import com.collabera.bushub.model.AdminOwnerModel;
import com.collabera.bushub.service.AdminOwnerService;

@RestController
@CrossOrigin(origins = "*")
public class AdminOwnerController {
	
	@Autowired
	private AdminOwnerService busOwnerService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtUtil jwtUtil;
	
	// Owner crud operations
	
	@PostMapping("/reg")
	public ResponseEntity<?> registerOwner(@RequestBody AdminOwner busOwner) {
		try {
			AdminOwner busOwner2=busOwnerService.regData(busOwner);
			if(busOwner2!= null) {
				return new ResponseEntity<String>("successfully register", HttpStatus.OK) ;
			}
			return new ResponseEntity<String>("Changes need in Username and password", HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<String>("something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@PostMapping("/login")
	
	public ResponseEntity<?> authenticatecredentials( @RequestBody AuthenticateRequest authenticateRequest) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticateRequest.getUserName(), authenticateRequest.getPassword()));
		} catch (AuthenticationException e) {
			throw new Exception("invalid username or password"+e);
		}
		
		UserDetails details=busOwnerService.loadUserByUsername(authenticateRequest.getUserName());
	  String jwt=jwtUtil.generateToken(details);
	  System.out.println("jwttttttttttttttt"+jwt);
	  
	  System.out.println("roleeeeeeeeeeeee"+details.getAuthorities());
		return ResponseEntity.ok(new AuthenticateResponse(jwt, (List<UserDetails>) details.getAuthorities()));
		
	}
	
	
	@GetMapping("/get/{id}")
	
	public ResponseEntity<?> getOwner(@PathVariable int id) {
		
		try {
			AdminOwner busOwner=busOwnerService.getData(id);
			if(busOwner != null) {
				return new ResponseEntity<AdminOwner>(busOwner, HttpStatus.OK);
			}
			return new ResponseEntity<String>("id not match", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("something went wrong", HttpStatus.OK);
		}
		
	}
	
	
	@PutMapping("/edit/{id}")
	public ResponseEntity<?> updateOwner(@RequestBody AdminOwner busOwner, @PathVariable int id) {
		try {
			AdminOwner busOwner2=busOwnerService.editData(busOwner,id);
			if(busOwner2 != null) {
				return new ResponseEntity<String>("updated successfully",HttpStatus.OK);
			}else {
				return new ResponseEntity<String>("invalid Data",HttpStatus.OK);
			}
			
		} catch (Exception e) {
			return new ResponseEntity<String>("something went wrong",HttpStatus.OK);
		}
		
	
	}
	
	@DeleteMapping("/remove/{id}")
	
	public ResponseEntity<?> removeData(@PathVariable int id) {
		  try {
			busOwnerService.deleteData(id);
			
			return new ResponseEntity<String>("deleted successfully", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("spmething went wrong",HttpStatus.OK);
		}
		
	}
	
	
	
	//==============Bus details crud operations=================
	
	
	@GetMapping("/getbus/{busId}")
	public ResponseEntity<?> getBus(@PathVariable int busId) {
		
		try {
			AdminOwnerModel bus=busOwnerService.getBusData(busId);
			if(bus != null) {
				return new ResponseEntity<AdminOwnerModel>(bus, HttpStatus.OK);
			}
			return new ResponseEntity<String>("id not match",HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("something went wrong", HttpStatus.OK);
		}
		
	}
	
	
	
	@PostMapping("/addbus")
	public ResponseEntity<?> addBus(@RequestBody AdminOwnerModel bus) {
		try {
			
			
			Bus bus2=busOwnerService.insertBusData(bus);
			  if(bus2!= null) {
				  return new ResponseEntity<String>("inserted successfully",HttpStatus.OK);
			  }
			  return new ResponseEntity<String>("invalid data",HttpStatus.OK);
		} catch (Exception e) {
			 return new ResponseEntity<String>("something went wrong",HttpStatus.OK);
		}
		
	}

	
	@PutMapping("/editbus/{busId}")
	public ResponseEntity<?> updateData(@RequestBody AdminOwnerModel bus, @PathVariable int busId) {
		try {
			Bus bus2=busOwnerService.editBusData(bus,busId);
			if(bus2 != null) {
				return new ResponseEntity<String>("Updated successfully",HttpStatus.OK);
			}
			return new ResponseEntity<String>("invalid data",HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("something went wrong",HttpStatus.OK);
		}
		
	}
	
	
	@DeleteMapping("/removebus/{busId}")
	public ResponseEntity<?> removeBus(@PathVariable int busId) {
		
		try {
			busOwnerService.deleteBusData(busId);
			
			return new ResponseEntity<String>("deleted successfully", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("something went wrong", HttpStatus.OK);	
		}
		
	}
	
	//get all bus details related to owner using username
	
	
	@GetMapping("/getallbuses/{userName}")
	public ResponseEntity<?> getbusDetails(@PathVariable String userName) {
		try {
			
			AdminOwner busOwner=busOwnerService.getownerData(userName);
			System.out.println("busbus"+busOwner);
			if(busOwner != null) {
				List<AdminOwnerModel> bus=busOwnerService.getAllBusDetails(busOwner.getId());
//				if(bus != null) {
					return new ResponseEntity<List<AdminOwnerModel>>( bus, HttpStatus.OK);
//				}else {
//					return new ResponseEntity<String>("Data not found", HttpStatus.OK);
//				}
				
			}else {
				return new ResponseEntity<String>("username missmatch", HttpStatus.OK);
			}
			
			
			
		} catch (Exception e) {
			return new ResponseEntity<String>("something went wrong", HttpStatus.OK);
		}
		
	}
	
	
	// getting bus data respected from and toplace
	@GetMapping("/search")
	
	public ResponseEntity<?> getBusDetails(@RequestBody AdminOwnerModel busOwnerModel) {
		
        try {
			List<AdminOwnerModel> bus= busOwnerService.getBusDetails(busOwnerModel);	
			System.out.println("+++++++++++++++++++++++++>"+bus);
			return new ResponseEntity<List<AdminOwnerModel>>(bus, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("something went wrong", HttpStatus.OK);
		}
		
	}
	
	
	// getting all Owners and admins 
	
	@GetMapping("/owners")
	public ResponseEntity<?> getAllOwners() {
		
		try {
			List<AdminOwner> owners= busOwnerService.getAllBusOwners();
			return new ResponseEntity<List<AdminOwner>>(owners, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("something went wrong", HttpStatus.OK);
		}
		
	}
	
	
	
}
