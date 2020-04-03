package com.akatsukidevs.perfumariapi4;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.akatsukidevs.perfumariapi4.controller.FotoController;


@SpringBootApplication
//@ComponentScan({"com.akatsukidevs.perfumariapi4","controller"})
public class PerfumariaPi4Application {

	public static void main(String[] args) {
		new File(FotoController.uploadDirectory).mkdir();
		
		SpringApplication.run(PerfumariaPi4Application.class, args);
	
	}

	
}
