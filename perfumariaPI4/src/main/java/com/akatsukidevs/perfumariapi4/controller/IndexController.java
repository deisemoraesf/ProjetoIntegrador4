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
	
	//Direcionar pagina para Categoria Masculino
	@GetMapping("/masculino")
	public String masculino() {
		return ("clienteProdutos/masculino");
	}
	
	//Direcionar pagina para Categoria Feminino
	@GetMapping("/feminino")
	public String feminino() {
		return ("clienteProdutos/feminino");
	}
	
	//Direcionar pagina para Categoria Infantil
	@GetMapping("/infantil")
	public String infantil() {
		return ("clienteProdutos/infantil");
	}
	


}
