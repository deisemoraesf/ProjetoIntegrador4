package com.akatsukidevs.perfumariapi4.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.catalina.authenticator.SpnegoAuthenticator.AuthenticateAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.akatsukidevs.perfumariapi4.model.Compra;
import com.akatsukidevs.perfumariapi4.model.ItensCompra;
import com.akatsukidevs.perfumariapi4.model.Produto;
import com.akatsukidevs.perfumariapi4.model.Usuario;
import com.akatsukidevs.perfumariapi4.repository.ProdutoRepositorios;
import com.akatsukidevs.perfumariapi4.repository.UsuarioRepository;


@Scope(value="session")

@Controller
public class CarrinhoController {
	

	private List<ItensCompra> itensCompra = new ArrayList<ItensCompra>();
	private Compra compra = new Compra();
	private Usuario usuario;

	@Autowired
	private ProdutoRepositorios pr;
	
	@Autowired
	private UsuarioRepository us;
	
	
	private void calcularTotal() {
		compra.setValorTotal(0.);
		for(ItensCompra it: itensCompra) {
			compra.setValorTotal(compra.getValorTotal() + it.getValorTotal());
		}
	}
	
	private void buscarUsuarioLogado() {
		Authentication autenticado = SecurityContextHolder.getContext().getAuthentication();
		if(!(autenticado instanceof AnonymousAuthenticationToken)) {
			String email = autenticado.getName();
			usuario = us.findByEmail(email);
		}
	}

	@GetMapping("/carrinho")
	public ModelAndView carrinho() {
		ModelAndView mv = new ModelAndView("/cliente/compras/carrinho");
		calcularTotal();
		mv.addObject("compra", compra);
		mv.addObject("listaItens", itensCompra);
		return mv;
	}
	
	@GetMapping("/carrinhoLogado")
	public ModelAndView carrinhoLogado() {
		ModelAndView mv = new ModelAndView("/cliente/compras/carrinhoLogado");
		calcularTotal();
		mv.addObject("compra", compra);
		mv.addObject("listaItens", itensCompra);
		return mv;
	}
	
	
	@RequestMapping("/finalizar")
	public ModelAndView finalizar() {
		buscarUsuarioLogado();
		ModelAndView mv=new ModelAndView("cliente/compras/finalizar");
		calcularTotal();
		mv.addObject("compra", compra);
		mv.addObject("listaItens", itensCompra);
		mv.addObject("usuario","usuario");
		return mv;
	}
	

	@GetMapping("/carrinho/addcarrinho/{id}")
	public String addCarrinho(@PathVariable Long id) {
		
		Optional<Produto> prod = pr.findById(id);
		Produto produto = prod.get();
		
		int controle = 0;
		for(ItensCompra it:itensCompra) {
			if(it.getProduto().getId_produto().equals(produto.getId_produto())) {
				it.setQuantidade(it.getQuantidade()+1);
				it.setValorTotal(0.);
				it.setValorTotal(it.getValorTotal() + (it.getQuantidade()* it.getValorUnitario()));
				controle = 1;
				break;
			}
		}		
		if(controle==0) {
		ItensCompra item = new ItensCompra();
		item.setProduto(produto);
		item.setValorUnitario(produto.getPreco());
		item.setQuantidade(item.getQuantidade() + 1);
		item.setValorTotal(item.getQuantidade()* item.getValorUnitario());
		itensCompra.add(item);
		}		
		return "redirect:/carrinho";
	}
	
	
	@GetMapping("/carrinhoLogado/addcarrinho/{id}")
	public String addCarrinhoLogado(@PathVariable Long id) {
		
		Optional<Produto> prod = pr.findById(id);
		Produto produto = prod.get();
		
		int controle = 0;
		for(ItensCompra it:itensCompra) {
			if(it.getProduto().getId_produto().equals(produto.getId_produto())) {
				it.setQuantidade(it.getQuantidade()+1);
				it.setValorTotal(0.);
				it.setValorTotal(it.getValorTotal() + (it.getQuantidade()* it.getValorUnitario()));
				controle = 1;
				break;
			}
		}		
		if(controle==0) {
		ItensCompra item = new ItensCompra();
		item.setProduto(produto);
		item.setValorUnitario(produto.getPreco());
		item.setQuantidade(item.getQuantidade() + 1);
		item.setValorTotal(item.getQuantidade()* item.getValorUnitario());
		itensCompra.add(item);
		}		
		return "redirect:/carrinhoLogado";
	}
	
	
	
	@GetMapping("/carrinho/alterarQtde/{id}/{acao}")
	public String alterarQtde(@PathVariable Long id, @PathVariable Integer acao) {
				
		for(ItensCompra it:itensCompra) {
			if(it.getProduto().getId_produto().equals(id)) {
				if(acao.equals(1)) {
					it.setValorTotal(0.);
					it.setQuantidade(it.getQuantidade()+1);
					it.setValorTotal(it.getValorTotal() + (it.getQuantidade()* it.getValorUnitario()));
				}else if(acao==0) {
					it.setQuantidade(it.getQuantidade()-1);
					if(it.getQuantidade()<=0) {
						removerItemCarrinho(id);
						removerItemCarrinhoLogado(id);
					}
					it.setValorTotal(0.);
					it.setValorTotal(it.getValorTotal() + (it.getQuantidade()* it.getValorUnitario()));
				}
				break;
			}
		}		
		return "redirect:/carrinho";
	}
	
	
	@GetMapping("/carrinhoLogado/alterarQtde/{id}/{acao}")
	public String alterarQtdeLogado(@PathVariable Long id, @PathVariable Integer acao) {
				
		for(ItensCompra it:itensCompra) {
			if(it.getProduto().getId_produto().equals(id)) {
				if(acao.equals(1)) {
					it.setValorTotal(0.);
					it.setQuantidade(it.getQuantidade()+1);
					it.setValorTotal(it.getValorTotal() + (it.getQuantidade()* it.getValorUnitario()));
				}else if(acao==0) {
					it.setQuantidade(it.getQuantidade()-1);
					if(it.getQuantidade()<=0) {
						removerItemCarrinho(id);
						removerItemCarrinhoLogado(id);
					}
					it.setValorTotal(0.);
					it.setValorTotal(it.getValorTotal() + (it.getQuantidade()* it.getValorUnitario()));
				}
				break;
			}
		}		
		return "redirect:/carrinhoLogado";
	}
	
	
		
	@GetMapping("/carrinho/removerItem/{id}")
	public String removerItemCarrinho(@PathVariable Long id) {
				
		for(ItensCompra it:itensCompra) {
			if(it.getProduto().getId_produto().equals(id)) {
				itensCompra.remove(it);			
				break;
			}
		}		
		return "redirect:/carrinho";
	}
	
	
	@GetMapping("/carrinhoLogado/removerItem/{id}")
	public String removerItemCarrinhoLogado(@PathVariable Long id) {
				
		for(ItensCompra it:itensCompra) {
			if(it.getProduto().getId_produto().equals(id)) {
				itensCompra.remove(it);			
				break;
			}
		}		
		return "redirect:/carrinhoLogado";
	}

}
