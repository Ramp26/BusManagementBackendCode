package com.collabera.bushub.service;

import java.util.ArrayList;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.collabera.bushub.dao.BusDao;
import com.collabera.bushub.dao.AdminOwnerDao;
import com.collabera.bushub.dto.Bus;
import com.collabera.bushub.dto.AdminOwner;
import com.collabera.bushub.dto.MyAdminOwner;
import com.collabera.bushub.model.AdminOwnerModel;

@Service
public  class AdminOwnerServiceImpl implements AdminOwnerService, UserDetailsService {
	
	@Autowired
	private BusDao busDao;
	
	@Autowired
	private AdminOwnerDao busOwnerDao;

	@Override
	public AdminOwner regData(AdminOwner busOwner) {
	if(busOwner != null) {
		AdminOwner busOwner3=busOwnerDao.findByUserName(busOwner.getUserName());
		AdminOwner busOwner2=busOwnerDao.findByPassword(busOwner.getPassword());
		
		if(busOwner2 ==null && busOwner3 ==null) {
			return busOwnerDao.save(busOwner);
		}else {
			return null;
		}
		
		
	}else {
		return null;
	}
	
		
	}
	

	@Override
	public AdminOwner getData(int ownerId) {
		if(ownerId>0) {
			return busOwnerDao.findById(ownerId);
		}
		return null;
	}


	@Override
	public AdminOwner editData(AdminOwner busOwner, int ownerId) {
		AdminOwner busOwner2=busOwnerDao.findById(ownerId);
		busOwner2.setName(busOwner.getName());
		busOwner2.setContact(busOwner.getContact());
		busOwner2.setAddress(busOwner.getAddress());
		busOwner2.setUserName(busOwner.getUserName());
		busOwner2.setPassword(busOwner.getPassword());
		return busOwnerDao.save(busOwner2);
	}


	@Override
	public void deleteData(int ownerId) {
		if(ownerId>0) {
			AdminOwner busOwner=busOwnerDao.findById(ownerId);
			busOwnerDao.delete(busOwner);
		}
			
	}

	
	//============Bus crud=========================

	@Override
	public AdminOwnerModel getBusData(int busId) {
		if(busId>0) {
			Bus bus= busDao.findByBusId(busId);
			AdminOwnerModel busOwnerModel=new AdminOwnerModel();
			busOwnerModel.setBusId(bus.getBusId());
			busOwnerModel.setBusName(bus.getBusName());
			busOwnerModel.setBusFacility(bus.getBusFacility());
			busOwnerModel.setBusFeature(bus.getBusFeature());
			busOwnerModel.setCharges(bus.getCharges());
			busOwnerModel.setDistance(bus.getDistance());
			busOwnerModel.setFromPlace(bus.getFromPlace());
			busOwnerModel.setToPlace(bus.getToPlace());
			busOwnerModel.setUserName(bus.getBusOwner().getUserName());
			return busOwnerModel;
		}
		return null;
	}


	@Override
	public Bus insertBusData(AdminOwnerModel bus) {
		AdminOwner busOwner=busOwnerDao.findByUserName(bus.getUserName());
		Bus bus2=new Bus();
		bus2.setBusOwner(busOwner);
		bus2.setBusId(bus.getBusId());
		bus2.setBusName(bus.getBusName());
		bus2.setBusFacility(bus.getBusFacility());
		bus2.setBusFeature(bus.getBusFeature());
		bus2.setDistance(bus.getDistance());
		bus2.setFromPlace(bus.getFromPlace());
		bus2.setToPlace(bus.getToPlace());
		
		if(bus != null) {
			
			if(bus.getBusFacility()=="AC" && bus.getBusFeature()=="Sleeper Coach") {
				bus2.setCharges((double) (bus.getDistance()*10));
			}else {
				bus2.setCharges((double) (bus.getDistance()*7));
			}
			
			return busDao.save(bus2);
		}
		return null;
	}


	@Override
	public Bus editBusData(AdminOwnerModel bus, int busId) {
		Bus bus2=busDao.findByBusId(busId);
		
		bus2.setBusName(bus.getBusName());
		bus2.setBusFacility(bus.getBusFacility());
		bus2.setBusFeature(bus.getBusFeature());
		bus2.setDistance(bus.getDistance());
		bus2.setFromPlace(bus.getFromPlace());
		bus2.setToPlace(bus.getToPlace());
		
		if(bus.getBusFacility()=="AC" && bus.getBusFeature()=="sleeper coach") 
		{
			bus2.setCharges((double) (bus.getDistance()*10));
		}else {
			bus2.setCharges((double) (bus.getDistance()*7));
		}
		
		return busDao.save(bus2);
	}


	@Override
	public void deleteBusData(int busId) {
		
		Bus bus=busDao.findByBusId(busId);
		busDao.delete(bus);
		
	}


	// getting all bus details respected owner
	@Override
	public AdminOwner getownerData(String userName) {
		if(userName != null) {
			AdminOwner busOwner=busOwnerDao.findByUserName(userName);
//			System.out.println("busOwner"+busOwner);
			
			return busOwner;
		}else {
			return null;
		}
		
	}


	@Override
	public List<AdminOwnerModel> getAllBusDetails(int id) {
		List<AdminOwnerModel> models=new ArrayList<AdminOwnerModel>();
		if(id>0) {
			List<Bus> bus= busDao.findallBusDetailsById(id);
			System.out.println(bus);
			for (Bus bus2 : bus) {

				AdminOwnerModel busOwnerModel=new AdminOwnerModel();
				busOwnerModel.setBusId(bus2.getBusId());
				busOwnerModel.setBusName(bus2.getBusName());
				busOwnerModel.setBusFeature(bus2.getBusFeature());
				busOwnerModel.setBusFacility(bus2.getBusFacility());
				busOwnerModel.setCharges(bus2.getCharges());
				busOwnerModel.setDistance(bus2.getDistance());
				busOwnerModel.setFromPlace(bus2.getFromPlace());
				busOwnerModel.setToPlace(bus2.getToPlace());
				busOwnerModel.setUserName(bus2.getBusOwner().getUserName());
				
				
				models.add(busOwnerModel);
			}
			
			return models;
		
		}
		return null;
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AdminOwner  busOwner=busOwnerDao.findByUserName(username);
		return new MyAdminOwner(busOwner);
	}


	@Override
	public List<AdminOwnerModel> getBusDetails(AdminOwnerModel busOwnerModel) {
		 
		List<AdminOwnerModel> models=new ArrayList<AdminOwnerModel>();
		List<Bus> bus=busDao.findallByPlace(busOwnerModel.getToPlace(),busOwnerModel.getFromPlace());
		System.out.println("================>"+bus);

	
		for (Bus bus2 : bus) {
			AdminOwnerModel busOwnerModel2=new AdminOwnerModel();

			busOwnerModel2.setBusId(bus2.getBusId());
			busOwnerModel2.setBusName(bus2.getBusName());
			busOwnerModel2.setBusFeature(bus2.getBusFeature());
			busOwnerModel2.setBusFacility(bus2.getBusFacility());
			busOwnerModel2.setCharges(bus2.getCharges());
			busOwnerModel2.setDistance(bus2.getDistance());
			busOwnerModel2.setFromPlace(bus2.getFromPlace());
			busOwnerModel2.setToPlace(bus2.getToPlace());
			busOwnerModel2.setUserName(bus2.getBusOwner().getUserName());
			
			
			models.add(busOwnerModel2);
		}
		
		return models;
	
	}


	//for Admin getting all owners
	@Override
	public List<AdminOwner> getAllBusOwners() {
		  
		
		
		return busOwnerDao.findAllOwners();
	}

}
