package com.collabera.bushub.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.collabera.bushub.dto.AdminOwner;

@Repository
public interface AdminOwnerDao extends JpaRepository<AdminOwner,Integer> {
	public AdminOwner findById(int id);

	public AdminOwner findByUserName(String userName);

	public AdminOwner findByPassword(String password);

	
	@Query("from AdminOwner")
	public List<AdminOwner> findAllOwners();

}
