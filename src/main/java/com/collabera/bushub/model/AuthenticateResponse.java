package com.collabera.bushub.model;



import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticateResponse {
	
private String jwt;
private List<UserDetails> role;

}
