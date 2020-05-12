package com.akatsukidevs.perfumariapi4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.akatsukidevs.perfumariapi4.model.Produto;
import com.akatsukidevs.perfumariapi4.repository.ProdutoRepositorios;



@Controller
public class IndexController {
	
	
	@Autowired
	private ProdutoRepositorios pr;
	
	
	@GetMapping("/")
	public ModelAndView listaProdutos() {
		ModelAndView mv = new ModelAndView("/index");
		Iterable<Produto> produtos = pr.findByStatus(true);
			mv.addObject("produtos", produtos);
			return mv;		
	}
	

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
	
	//Direcionar pagina de categoria
	@GetMapping("cliente/masculino")
	public String masculino() {
		return ("clienteProdutos/masculino");
	}
	
	
	//Direcionar pagina de categoria
	@GetMapping("cliente/feminino")
	public String feminino() {
		return ("clienteProdutos/feminino");
	}
	
	
	//Direcionar pagina de categoria
	@GetMapping("cliente/infantil")
	public String infantil() {
		return ("clienteProdutos/infantil");
	}
	
	
	
	

}
