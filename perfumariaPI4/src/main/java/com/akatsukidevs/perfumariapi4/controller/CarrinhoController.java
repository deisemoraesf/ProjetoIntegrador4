package com.akatsukidevs.perfumariapi4.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.akatsukidevs.perfumariapi4.model.Compra;
import com.akatsukidevs.perfumariapi4.model.Endereco;
import com.akatsukidevs.perfumariapi4.model.ItensCompra;
import com.akatsukidevs.perfumariapi4.model.Pessoa;
import com.akatsukidevs.perfumariapi4.model.Produto;
import com.akatsukidevs.perfumariapi4.model.Usuario;
import com.akatsukidevs.perfumariapi4.repository.CompraRepository;
import com.akatsukidevs.perfumariapi4.repository.EnderecoRepository;
import com.akatsukidevs.perfumariapi4.repository.ItensCompraRepository;
import com.akatsukidevs.perfumariapi4.repository.ProdutoRepositorios;
import com.akatsukidevs.perfumariapi4.repository.UsuarioRepository;


@Scope(value="session")

@Controller
public class CarrinhoController {
	

	private List<ItensCompra> itensCompra = new ArrayList<ItensCompra>();
	private Compra compra = new Compra();
	

	@Autowired
	private ProdutoRepositorios pr;
	
	@Autowired
	private UsuarioRepository ur;
	
	@Autowired
	private CompraRepository cr;
	
	@Autowired
	private ItensCompraRepository itensCompraRepo;
	
	
	@Autowired
	private EnderecoRepository er;
	
	private void calcularTotal() {
		compra.setValorTotal(0.);
		for(ItensCompra it: itensCompra) {
			compra.setValorTotal(compra.getValorTotal() + it.getValorTotal());
		}
	}
	

	@GetMapping("/carrinho")
	public ModelAndView carrinho() {
		ModelAndView mv = new ModelAndView("cliente/compras/carrinho");
		calcularTotal();
		mv.addObject("compra", compra);
		mv.addObject("listaItens", itensCompra);
		return mv;
	}
	

    @RequestMapping(value = "/finalizar", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView currentUserName(Principal principal) {
    	ModelAndView mv = new ModelAndView("cliente/compras/finalizar");
    	calcularTotal();
		Usuario u = ur.findByEmail(principal.getName());
		Pessoa cliente = u.getPessoa();
		int quantidadeTotal = 0;
			for(ItensCompra it: itensCompra) {
				quantidadeTotal = quantidadeTotal +it.getQuantidade();
			}
		mv.addObject("qtda", quantidadeTotal);	
		mv.addObject("compra", compra);
		mv.addObject("listaItens", itensCompra);
		mv.addObject("enderecos", cliente.getEnderecos());
		mv.addObject("pessoas", cliente);
		mv.addObject("usuario", cliente.getUsuario());
        return mv;
    }
    
    
    @PostMapping("/finalizar/confirmar")
    public ModelAndView confirmarCompra(String formaPagamento,String statusCompra, Pessoa p,@RequestParam("id") Long id) {
    	ModelAndView mv = new ModelAndView("cliente/compras/mensagemFinalizado");
    	Optional<Endereco> e = er.findById(id);
    	Endereco end = e.get();
    	cr.saveAndFlush(compra);
    	for(ItensCompra c:itensCompra) {
    		c.setCompra(compra);
    		itensCompraRepo.saveAndFlush(c);
    		Produto produtoitem = c.getProduto();
    		int quantdadeproduto = produtoitem.getQuantidade();
    		int quantidadeitem = c.getQuantidade();
    		int recalculaquantidade = quantdadeproduto - quantidadeitem;
    		produtoitem.setQuantidade(recalculaquantidade);
    		pr.save(produtoitem);
    		
    	}
    	compra.setCliente(p);
    	p.getCompras().add(compra);
    	compra.setEndereco(end);
    	compra.setStatusCompra(statusCompra);
    	compra.setItensCompra(itensCompra);
    	compra.setFormaPagamento(formaPagamento);
    	
    	end.setCompra(compra);
    	 	
    	cr.saveAndFlush(compra);
    	
    	mv.addObject("compra", compra);
    	itensCompra = new ArrayList<>();
    	compra = new Compra();
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
					}
					it.setValorTotal(0.);
					it.setValorTotal(it.getValorTotal() + (it.getQuantidade()* it.getValorUnitario()));
				}
				break;
			}
		}		
		return "redirect:/carrinho";
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
	
	
	
}
