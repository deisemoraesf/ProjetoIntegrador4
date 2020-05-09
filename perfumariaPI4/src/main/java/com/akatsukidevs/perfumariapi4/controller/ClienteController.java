package com.akatsukidevs.perfumariapi4.controller;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
		
		Set<Endereco> enderecosadicionado = new HashSet<>();
		enderecosadicionado.add(endereco);
		er.save(endereco);
		pessoaFisica.setEnderecos(enderecosadicionado);
		ur.save(usuario);				
		usuario.setPessoa(pessoaFisica);
		pessoaFisica.setUsuario(usuario);
		pfr.save(pessoaFisica);
		attribute.addFlashAttribute("mensagem", "Salvo com sucesso");
		return ("redirect:/clientes/cadastrarCliente");
	}
	
	@RequestMapping(value="/clientes/cadastrarCliente/PJ", method=RequestMethod.POST)
	public String salvarPj(PessoaJuridica pessoaJuridica, Endereco endereco, Usuario usuario, BindingResult result, RedirectAttributes attribute) {
		if(result.hasErrors()) {
			attribute.addFlashAttribute("mensagem", "Verifique os campos em branco"); 
		}
		Set<Endereco> enderecosadicionado = new HashSet<>();
		enderecosadicionado.add(endereco);
		er.save(endereco);
		pessoaJuridica.setEnderecos(enderecosadicionado);
						
		usuario.setPessoa(pessoaJuridica);
		pessoaJuridica.setUsuario(usuario);
		pjr.save(pessoaJuridica);
		attribute.addFlashAttribute("mensagem", "Salvo com sucesso");
		return ("redirect:/clientes/cadastrarCliente");
	}

	@GetMapping("/clientesAdm")
	public ModelAndView listaClientes(){
		ModelAndView mv = new ModelAndView("/admin/clienteAdm/listaClientes");
		Iterable<Pessoa> clientes = pr.findByStatus(true); 
		mv.addObject("pessoas", clientes);
		return mv;
		
	}
	
	@GetMapping("/clientes/minhaConta")
	public ModelAndView MinhaConta(@AuthenticationPrincipal Usuario usuario, RedirectAttributes attribute) {
		ModelAndView mv = new ModelAndView("/cliente/minhaConta");
		Optional<Usuario> u = ur.findById(usuario.getId_usuario());
		Usuario usu = u.get();
		mv.addObject("pessoas", usu);
		return mv;
		
	}
	
	
	@RequestMapping(value="/clientesAdm/visualizarClientes/{id_pessoa}", method=RequestMethod.GET)
	public ModelAndView visualizarCliente(@PathVariable ("id_pessoa") Long id_pessoa) {
		ModelAndView mv = new ModelAndView("/admin/clienteAdm/visualizarCliente");
		Optional<Pessoa> p = pr.findById(id_pessoa);
		Pessoa cliente = p.get();
		mv.addObject("pessoas", cliente);
		mv.addObject("enderecos", cliente.getEnderecos());
		mv.addObject("usuario", cliente.getUsuario());
		return mv;
	}
	
	@RequestMapping(value="/clientesAdm/editarClientes/{id_pessoa}", method=RequestMethod.GET)
	public ModelAndView editarCliente(@PathVariable ("id_pessoa") Long id_pessoa, RedirectAttributes attribute ) {
		ModelAndView mv = new ModelAndView("/admin/clienteAdm/editarCliente");
		Optional<Pessoa> p = pr.findById(id_pessoa);
		Pessoa cli = p.get();
		mv.addObject("pessoas", cli);
		mv.addObject("enderecos", cli.getEnderecos());
		mv.addObject("usuario", cli.getUsuario());
		attribute.addFlashAttribute("mensagem", "Editado com sucesso");
		return mv;
		
	}
	
	@RequestMapping(value="/clientesAdm/editarClientes/{id_pessoa}", method=RequestMethod.POST)
	public String salvaEdicao(Pessoa c, RedirectAttributes attribute) {
		
		String tipoPessoa = c.getTipoPessoa();
		
		if(tipoPessoa == "pf" && tipoPessoa !=null) {
			PessoaFisica pf = (PessoaFisica) c;
			pfr.save(pf);
		}else if (tipoPessoa == "pj") {
			PessoaJuridica pj= (PessoaJuridica) c;
			pjr.save(pj);
		}
		
		attribute.addFlashAttribute("mensagem", "Alterado com sucesso");
		return ("redirect:/clientesAdm/editarClientes/{id_pessoa}");
			
			
	}
	
	@GetMapping("/clientesAdm/deletarClientes/{id_pessoa}")
	public String deletarClientes(@PathVariable ("id_pessoa") Long id_pessoa, RedirectAttributes attribute) {
		Optional<Pessoa> c = pr.findById(id_pessoa);
		Pessoa cli = c.get();
		Usuario usu = cli.getUsuario();
		usu.setStatus(false);
		cli.setStatus(false);
		pr.save(cli);
		ur.save(usu);
		attribute.addFlashAttribute("mensagem", "Deletado com sucesso");
		return ("redirect:/clientesAdm");
		
	}
	
	
	@PostMapping("/clientes/verificarCliente")
	public String verificaCliente(String email, RedirectAttributes attribute) {
		Usuario usu = ur.findByEmail(email);
		if (usu != null && usu.isStatus()==true) {
			attribute.addFlashAttribute("mensagem", "Você já possui uma conta");
			return ("redirect:/clientes/verificarCliente");
		}else if (email == null || email.isEmpty()) {
			attribute.addFlashAttribute("mensagem", "Digite um email válido");
			return ("redirect:/clientes/verificarCliente");
		}else {	
			return ("redirect:/clientes/cadastrarCliente");
		}
	}
	
	
	
		
	
	
	
	
}
