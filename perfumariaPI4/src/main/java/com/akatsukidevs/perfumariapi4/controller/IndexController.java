package com.akatsukidevs.perfumariapi4.controller;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.akatsukidevs.perfumariapi4.model.FotoProduto;
import com.akatsukidevs.perfumariapi4.model.Pessoa;
import com.akatsukidevs.perfumariapi4.model.Produto;
import com.akatsukidevs.perfumariapi4.repository.FotoProdutoRepository;
import com.akatsukidevs.perfumariapi4.repository.ProdutoRepositorios;



@Controller
public class IndexController {
	
	
	@Autowired
	private ProdutoRepositorios pr;
	
	@Autowired
	private FotoProdutoRepository fpr;
	
	
	@GetMapping("/")
	public ModelAndView listaProdutos() {
		ModelAndView mv = new ModelAndView("/index");
		Iterable<Produto> produtos = pr.findByStatus(true);
		mv.addObject("produtos", produtos);
		return mv;		
	}
	
	@RequestMapping(value="/fotos/{id_produto}", method=RequestMethod.GET)
	public ModelAndView visualizarFoto(@PathVariable ("id_produto") Long id_produto) {
		ModelAndView mv = new ModelAndView("/index");
		Optional<Produto> p = pr.findById(id_produto);
		Produto prod = p.get();
		Iterable<FotoProduto> fp = fpr.findByProduto(prod);
		mv.addObject("fotos", fp);
		return mv;
	}
	
	@RequestMapping(value="/clientes/produtos/visualizarProdutos/{id_produto}", method=RequestMethod.GET)
	public ModelAndView visualizarProduto(@PathVariable ("id_produto") Long id_produto) {
		ModelAndView mv = new ModelAndView("/admin/produtos/detalhesProduto");
		Optional<Produto> p = pr.findById(id_produto);
		Produto prod = p.get();
		Iterable<FotoProduto> fotos = fpr.findByProduto(prod);
		mv.addObject("produto", prod);
		mv.addObject("fotos", fotos);
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
