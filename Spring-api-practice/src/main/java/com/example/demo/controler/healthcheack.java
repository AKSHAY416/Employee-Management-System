package com.example.demo.controler;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class healthcheack {
	@GetMapping("/Healthcheack")
	public String Healthcheack() {
		return " hy akshay ok";
	}
	

}
