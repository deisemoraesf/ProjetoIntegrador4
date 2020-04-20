package com.akatsukidevs.perfumariapi4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.akatsukidevs.perfumariapi4.repository.EnderecoRepository;

@Controller
public class EnderecoController {
	
	@Autowired 
	private EnderecoRepository er;

}
