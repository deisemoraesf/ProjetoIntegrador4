package com.akatsukidevs.perfumariapi4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	
	//Index Cliente
	@RequestMapping("/index")//toda vez que digitar / vai para o Index
	public String index() {
		return("index");
	}
	
	//Index Cliente
	@RequestMapping("/indexLog")//toda vez que digitar / vai para o Index
	public String indexLogado() {
		return("indexLogado");
		}
	
	//Index Administrativo
	@GetMapping("/admin/")
	public String admin() {
		return ("admin/home");
	}
	
	//Acesso Negado
	@GetMapping("/acessoNegado")
	public String acessoNegado() {
		return ("admin/acessoNegado");
	}
	
	//Direcionar pagina de detalhes do Produto
	@GetMapping("/detalhesProduto")
	public String detalhesProduto() {
		return ("admin/produtos/detalhesProduto");
	}
	

}
