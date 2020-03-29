package com.akatsukidevs.perfumariapi4.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.akatsukidevs.perfumariapi4.model.Produtos;
import com.akatsukidevs.perfumariapi4.repository.ProdutoRepositorios;



@SpringBootApplication
@Controller
public class ProdutosControle {
	
	
	
	@Autowired
	private ProdutoRepositorios produtoRepositorios;
	
	@GetMapping("/produtos/cadastrarProdutos")
	public ModelAndView cadastrar(Produtos produtos) {
		ModelAndView mv =  new ModelAndView("/admin/produtos/cadastroProdutos");
		mv.addObject("produtos", produtos);
		return mv;
		
	}
	
	@GetMapping("/produtos/listarProdutos")
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("/admin/produtos/listaProdutos");
		List<Produtos> produtos = produtoRepositorios.findAll();
		mv.addObject("produtos", produtos);
		return mv;
	}
	
	@GetMapping("/produtos/editar/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		Optional<Produtos> produtos = produtoRepositorios.findById(id);
		return cadastrar(produtos.get());		
	}
	
	@GetMapping("/produtos/remover/{id}")
	public ModelAndView remover(@PathVariable("id") Long id) {
		Optional<Produtos> produtos = produtoRepositorios.findById(id);
		produtoRepositorios.delete(produtos.get());
		return listar();		
	}
	
	@PostMapping("/admin/produtos/salvar")
	public ModelAndView salvar(@Valid Produtos produtos, BindingResult result,
			@RequestParam("file")MultipartFile arquivo)  {	
		
		if(result.hasErrors()) {
			return cadastrar(produtos);
		}
			
		produtoRepositorios.saveAndFlush(produtos);
		return cadastrar(new Produtos());			
	}

}
