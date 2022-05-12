package com.collabera.bushub.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.collabera.bushub.dao.BusDao;
import com.collabera.bushub.dao.AdminOwnerDao;
import com.collabera.bushub.dto.Bus;
import com.collabera.bushub.dto.AdminOwner;
import com.collabera.bushub.model.AdminOwnerModel;

@ExtendWith(MockitoExtension.class)
class AdminOwnerServiceImplTest {
	
	@Mock
	private BusDao busDao;
	
	
	@Mock
	private AdminOwnerDao busOwnerDao;
	
	@InjectMocks
	private AdminOwnerServiceImpl busOwnerServiceImpl;
	
	
	

	@Test
	void testRegData() {
		AdminOwner busOwner=new AdminOwner();
		busOwner.setId(1);
		busOwner.setName("raman");
		busOwner.setContact(1234567890);
		busOwner.setAddress("benglore");
		busOwner.setUserName("ram");
		busOwner.setPassword("Ram@123");
		busOwner.setRole("owner");
		when(busOwnerDao.save(busOwner)).thenReturn(busOwner);
		
		AdminOwner busOwner2=busOwnerServiceImpl.regData(busOwner);
		assertEquals(busOwner2, busOwner);
	}

	@Test
	void testGetData() {
		
		AdminOwner busOwner=new AdminOwner();
		busOwner.setId(10);
		busOwner.setAddress("belawadi");
		busOwner.setName("srujan");
		busOwner.setContact(1234567890);
		busOwner.setUserName("sru");
		busOwner.setRole("owner");
		
		when(busOwnerDao.findById(10)).thenReturn(busOwner);
		
		AdminOwner busOwner2=busOwnerServiceImpl.getData(10);
		assertEquals(busOwner2, busOwner);
		
	}

	@Test
	void testEditData() {
		
		AdminOwner busOwner1=new AdminOwner();
		
		busOwner1.setId(1);
		busOwner1.setAddress("belawadi");
		busOwner1.setName("srujan");
		busOwner1.setContact(1234567890);
		busOwner1.setUserName("sru");
		busOwner1.setRole("owner");
		when(busOwnerDao.save(busOwner1)).thenReturn(busOwner1);
		
		AdminOwner busOwner=new AdminOwner();
	        busOwner=busOwnerDao.findById(1);
	busOwner.setId(1);
		busOwner.setAddress("bel");
		busOwner.setName("rock");
		busOwner.setContact(123456);
		busOwner.setUserName("sr");
		busOwner.setRole("owner");
		
		when(busOwnerDao.save(busOwner)).thenReturn(busOwner);
		AdminOwner busOwner2=busOwnerServiceImpl.editData(busOwner,1);
		
		System.out.println(busOwner2);
		assertEquals(busOwner2, busOwner);
		
	}

	@Test
	void testDeleteData() {
		
		
		fail("Not yet implemented");
	}

	@Test
	void testGetBusData() {
		fail("Not yet implemented");
	}

	@Test
	void testInsertBusData() {
		
		Bus bus1=new Bus();
		AdminOwnerModel bus=new AdminOwnerModel();
		
		bus.setBusId(100);
		bus.setBusName("VRL");
		bus.setBusFacility("sleeper Coach");
		bus.setBusFeature("AC");
	
//		bus.setCharges(2000.0);
		bus.setDistance(200);
		bus.setFromPlace("hallur");
		bus.setToPlace("salahalli");
		
		
		bus1.setBusId(bus.getBusId());
		bus1.setBusName(bus.getBusName());
		bus1.setBusFacility(bus.getBusFacility());
		bus1.setBusFeature(bus.getBusFeature());
//		bus1.setCharges(bus.getCharges());
		
		bus1.setDistance(bus.getDistance());
		bus1.setFromPlace(bus.getFromPlace());
		bus1.setToPlace(bus.getToPlace());
		
		
		when(busDao.save(bus1)).thenReturn(bus1);
Bus busOwner2=busOwnerServiceImpl.insertBusData(bus);
		
		System.out.println(busOwner2);
		assertEquals(bus1,busOwner2);
		
	}

	@Test
	void testEditBusData() {
		fail("Not yet implemented");
	}

	@Test
	void testDeleteBusData() {
		fail("Not yet implemented");
	}

	@Test
	void testGetownerData() {
		fail("Not yet implemented");
	}

	@Test
	void testGetAllBusDetails() {
		fail("Not yet implemented");
	}

	@Test
	void testLoadUserByUsername() {
		fail("Not yet implemented");
	}

	@Test
	void testGetBusDetails() {
		fail("Not yet implemented");
	}

}
