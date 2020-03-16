package com.radisys.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data	
public class User {

	private String userName;
	
	private String number;
	
	private String age;
	
	private String dept;
}
