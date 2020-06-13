package com.akatsukidevs.perfumariapi4.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.akatsukidevs.perfumariapi4.model.Compra;
import com.akatsukidevs.perfumariapi4.model.ItensCompra;
import com.akatsukidevs.perfumariapi4.model.Produto;
import com.akatsukidevs.perfumariapi4.repository.CompraRepository;
import com.akatsukidevs.perfumariapi4.repository.ProdutoRepositorios;

@Controller
public class ComprasController {
	
	@Autowired
	private CompraRepository cr;
	
	@Autowired
	private ProdutoRepositorios prodr;
	
	@RequestMapping("/compras")
	public ModelAndView verificaCliente() {
		ModelAndView mv=new ModelAndView("admin/compras/listaCompras");
		mv.addObject("compras", cr.findAll());
		return mv;
	}
	
	@GetMapping("/compras/editarStatus/{id}")
	public ModelAndView editarStatus(@PathVariable("id") Long id) {
		ModelAndView mv=new ModelAndView("admin/compras/editaCompras");
		Optional<Compra> compra = cr.findById(id);
		Compra c = compra.get();
		mv.addObject("compras", c);
		return mv;
	}
	
	@PostMapping("/compras/editarStatus/{id}")
	public String editarStatus(@PathVariable("id") Long id,@RequestParam("statusCompra") String statusCompra, RedirectAttributes attribute) {
		
		Optional<Compra> compra = cr.findById(id);
		Compra com = compra.get();
		if(statusCompra.equalsIgnoreCase("cancelado")) {
			for (ItensCompra c: com.getItensCompra()) {
				Produto produtoitem = c.getProduto();
	    		int quantdadeproduto = produtoitem.getQuantidade();
	    		int quantidadeitem = c.getQuantidade();
	    		int recalculaquantidade = quantdadeproduto + quantidadeitem;
	    		produtoitem.setQuantidade(recalculaquantidade);
	    		prodr.save(produtoitem);
			}
		}	
		com.setStatusCompra(statusCompra);
		cr.save(com);
		
		
		attribute.addFlashAttribute("mensagem", "Salvo com sucesso");
		return ("redirect:/compras/editarStatus/{id}");
	}
	
	//Pesquisa Administrativo
	@PostMapping("**/pesquisaPedido")
	public ModelAndView pesquisar(@RequestParam ("pesquisastatus") String pesquisastatus, RedirectAttributes attribute) {
		ModelAndView mv = new ModelAndView("admin/compras/listaCompras");
		Iterable<Compra> compra = cr.findByStatusCompraContainingIgnoreCase(pesquisastatus);
			
		mv.addObject("compras", compra);
		
		return mv;
			
	}

}
