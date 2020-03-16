package com.radisys.controller;

import java.util.Arrays;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	
	public ResponseEntity<?> saveUserData(){
		return ResponseEntity.ok(Arrays.asList("umapathi"));
	}
}
