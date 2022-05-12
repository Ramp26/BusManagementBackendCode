package com.collabera.bushub.dao;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.collabera.bushub.dto.Bus;

@Repository
public interface BusDao extends JpaRepository<Bus, Integer>  {
	
	public Bus findByBusId(int bId);



//	public List<Bus> findBybusOwner(int ownerId);


@Query("from Bus where busOwner.id = ?1")
	public List<Bus> findallBusDetailsById(int id);


//@Query("from Bus where toPlace= ?1")
//public List<Bus> findallBusDetailsByToPlace(String toPlace);


//@Query("from Bus where fromPlace= ?1")
//public List<Bus> findallBusDetailsByFromPlace(String fromPlace);





@Query("from Bus where toPlace=:toPlace and fromPlace=:fromPlace")
public List<Bus> findallByPlace(String toPlace, String fromPlace);




	

}
