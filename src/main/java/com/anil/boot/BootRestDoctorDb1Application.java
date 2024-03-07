package com.anil.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
//@EnableConfigServer
@EnableSwagger2
@EnableCaching
@EnableWebMvc
public class BootRestDoctorDb1Application {

	public static void main(String[] args) {
		SpringApplication.run(BootRestDoctorDb1Application.class, args);
	}

}
