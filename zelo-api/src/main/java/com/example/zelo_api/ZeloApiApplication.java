package com.example.zelo_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ZeloApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZeloApiApplication.class, args);
	}

}
