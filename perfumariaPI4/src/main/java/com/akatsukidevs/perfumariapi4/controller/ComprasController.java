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
import com.akatsukidevs.perfumariapi4.repository.CompraRepository;

@Controller
public class ComprasController {
	
	@Autowired
	private CompraRepository cr;
	
	@RequestMapping("/compras")
	public ModelAndView verificaCliente() {
		ModelAndView mv=new ModelAndView("/admin/listaCompras");
		mv.addObject("compras", cr.findAll());
		return mv;
	}
	
	@GetMapping("/compras/editarStatus/{id}")
	public ModelAndView editarStatus(@PathVariable("id") Long id) {
		ModelAndView mv=new ModelAndView("/admin/editaCompras");
		Optional<Compra> compra = cr.findById(id);
		Compra c = compra.get();
		mv.addObject("compras", c);
		return mv;
	}
	
	@PostMapping("/compras/editarStatus/{id}")
	public String editarStatus(@PathVariable("id") Long id,@RequestParam("statusCompra") String statusCompra, RedirectAttributes attribute) {
		
		Optional<Compra> compra = cr.findById(id);
		Compra com = compra.get();
		com.setStatusCompra(statusCompra);
		cr.save(com);
		
		attribute.addFlashAttribute("mensagem", "Salvo com sucesso");
		return ("redirect:/compras/editarStatus/{id}");
	}

}
