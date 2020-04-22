package com.akatsukidevs.perfumariapi4.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.akatsukidevs.perfumariapi4.model.Endereco;
import com.akatsukidevs.perfumariapi4.model.Pessoa;
import com.akatsukidevs.perfumariapi4.model.PessoaFisica;
import com.akatsukidevs.perfumariapi4.model.PessoaJuridica;
import com.akatsukidevs.perfumariapi4.model.Usuario;
import com.akatsukidevs.perfumariapi4.repository.EnderecoRepository;
import com.akatsukidevs.perfumariapi4.repository.PessoaFisicaRepository;
import com.akatsukidevs.perfumariapi4.repository.PessoaJuridicaRepository;
import com.akatsukidevs.perfumariapi4.repository.PessoaRepository;
import com.akatsukidevs.perfumariapi4.repository.UsuarioRepository;

@Controller
public class ClienteController {

	@Autowired
	private PessoaRepository pr;
	
	@Autowired
	private PessoaFisicaRepository pfr;
	
	@Autowired
	private PessoaJuridicaRepository pjr;
	
	@Autowired
	private UsuarioRepository ur;
	
	@Autowired
	private EnderecoRepository er;
	
	@RequestMapping("/clientes/verificarCliente")
	public ModelAndView verificaCliente() {
		ModelAndView mv=new ModelAndView("cliente/verificaCliente");
		return mv;
	}
	
	@RequestMapping(value="/clientes/cadastrarCliente", method=RequestMethod.GET)
	public String salvar() {
		return("cliente/cadastroPF");
	}
	
	@RequestMapping(value="/clientes/cadastrarCliente/PJ", method=RequestMethod.GET)
	public String salvarPj() {
		return("cliente/cadastroPJ");
	}
	
	@RequestMapping(value="/clientes/cadastrarCliente", method=RequestMethod.POST)
	public String salvar(PessoaFisica pessoaFisica, Endereco endereco, Usuario usuario, BindingResult result, RedirectAttributes attribute) {
		if(result.hasErrors()) {
			attribute.addFlashAttribute("mensagem", "Verifique os campos em branco"); 
		}
		pfr.save(pessoaFisica);
		er.save(endereco);
		ur.save(usuario);
		attribute.addFlashAttribute("mensagem", "Salvo com sucesso");
		return ("redirect:/clientes/cadastrarCliente");
	}
	
	@RequestMapping(value="/clientes/cadastrarCliente/PJ", method=RequestMethod.POST)
	public String salvarPj(PessoaJuridica pessoaJuridica, Endereco endereco, Usuario usuario, BindingResult result, RedirectAttributes attribute) {
		if(result.hasErrors()) {
			attribute.addFlashAttribute("mensagem", "Verifique os campos em branco"); 
		}
		pjr.save(pessoaJuridica);
		er.save(endereco);
		ur.save(usuario);
		attribute.addFlashAttribute("mensagem", "Salvo com sucesso");
		return ("redirect:/clientes/cadastrarCliente");
	}

	@GetMapping("/clientesAdm")
	public ModelAndView listaClientes() {
		ModelAndView mv = new ModelAndView("/admin/clienteAdm/listaClientes");
		Iterable<Pessoa> clientes = pr.findByStatus(true); 
		mv.addObject("clientes", clientes);
		return mv;
		
	}
	
	@RequestMapping(value="/clientes/editarClientes/{id_cliente}", method=RequestMethod.GET)
	public ModelAndView editarCliente(@PathVariable ("id_cliente") Long id_cliente, RedirectAttributes attribute ) {
		ModelAndView mv = new ModelAndView("/cliente/editarCliente");
		Optional<PessoaFisica> c = pfr.findById(id_cliente);
		PessoaFisica cli = c.get();
		mv.addObject("clientes", cli);
		attribute.addFlashAttribute("mensagem", "Editado com sucesso");
		return mv;
		
	}
	
	@RequestMapping(value="/clientes/editarClientes/{id_cliente}", method=RequestMethod.POST)
	public String salvaEdicao(PessoaFisica c) {
		pfr.save(c);
		return ("redirect:/clientes/editarClientes/{id_cliente}");
	}
	
	@GetMapping("/clientes/deletarClientes/{id_cliente}")
	public String deletarClientes(@PathVariable ("id_cliente") Long id_cliente, RedirectAttributes attribute) {
		Optional<PessoaFisica> c = pfr.findById(id_cliente);
		PessoaFisica cli = c.get();
		cli.setStatus(false);
		pfr.save(cli);
		attribute.addFlashAttribute("mensagem", "Deletado com sucesso");
		return ("redirect:/clientes");
		
	}
	
	@PostMapping("/clientes/verificarCliente")
	public String verificaCliente(String email, RedirectAttributes attribute) {
		Usuario usu = ur.findByEmail(email);
		if (usu != null && usu.isStatus()==true) {
			attribute.addFlashAttribute("mensagem", "Você já possui uma conta");
			return ("redirect:/clientes/verificarCliente");
		/*}else if (usu == null) {
			attribute.addFlashAttribute("mensagem", "Digite um email válido");
			return ("redirect:/clientes/verificarCliente");*/
		}else {	
			return ("redirect:/clientes/cadastrarCliente");
		}
	}
	
	
	
		
	
	
	
	
}
