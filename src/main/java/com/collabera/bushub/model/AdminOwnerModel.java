package com.collabera.bushub.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminOwnerModel {
	
	private int busId;
	private String busName;
	private String busFeature;
	private String busFacility;
	private String fromPlace;
	private String  toPlace;
	private Double charges;
	private int distance;
	private String userName;

}
