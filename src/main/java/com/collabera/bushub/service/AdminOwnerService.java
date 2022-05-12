package com.collabera.bushub.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import com.collabera.bushub.dto.Bus;
import com.collabera.bushub.dto.AdminOwner;
import com.collabera.bushub.model.AdminOwnerModel;

public interface AdminOwnerService {

	AdminOwner regData(AdminOwner busOwner);

	AdminOwner getData(int ownerId);

	AdminOwner editData(AdminOwner busOwner, int id);

	void deleteData(int ownerId);

	AdminOwnerModel getBusData(int busId);

	Bus insertBusData(AdminOwnerModel bus);

	Bus editBusData(AdminOwnerModel bus, int busId);

	void deleteBusData(int busId);


	public UserDetails loadUserByUsername(String username);
	AdminOwner getownerData(String userName);

	List<AdminOwnerModel> getAllBusDetails(int id);

	List<AdminOwnerModel> getBusDetails(AdminOwnerModel busOwnerModel);

	List<AdminOwner> getAllBusOwners();



}
