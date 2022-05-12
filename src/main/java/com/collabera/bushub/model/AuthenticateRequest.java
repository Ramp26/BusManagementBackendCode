package com.collabera.bushub.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticateRequest {
	
	private String userName;
	private String password;

}
