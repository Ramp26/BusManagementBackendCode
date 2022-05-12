package com.collabera.bushub.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bus implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int busId;
	private String busName;
	private String busFeature;
	private String busFacility;
	private String fromPlace;
	private String  toPlace;
	private Double charges;
	private int distance;
	@ManyToOne
	@JoinColumn(name="foreignkey")
	private AdminOwner busOwner;
	

}
