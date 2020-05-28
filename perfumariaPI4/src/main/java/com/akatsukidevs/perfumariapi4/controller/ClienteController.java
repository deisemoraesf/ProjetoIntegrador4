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
	
	
	//Cadastro cliente tela Comprador
	@RequestMapping(value="/clientes/cadastrarCliente", method=RequestMethod.GET)
	public String salvarCliente() {
		return("/cliente/cadastroClientePF");
	}
	
	//Cadastro cliente tela Comprador
	@RequestMapping(value="/clientes/cadastrarCliente/PJ", method=RequestMethod.GET)
	public String salvarClientePj() {
		return("/cliente/cadastroClientePJ");
	}
		
	//Cadastro cliente tela Comprador
	@RequestMapping(value="/clientes/cadastrarCliente", method=RequestMethod.POST)
	public String salvar(PessoaFisica pessoaFisica, Endereco endereco, Usuario usuario, BindingResult result, RedirectAttributes attribute) {
		if(result.hasErrors()) {
			attribute.addFlashAttribute("mensagem", "Verifique os campos em branco"); 
		}
		
		er.save(endereco);
		ur.save(usuario);
		pessoaFisica.setUsuario(usuario);
		pessoaFisica.getEnderecos().add(endereco);
		pfr.save(pessoaFisica);
		usuario.setPessoa(pessoaFisica);
		ur.save(usuario);
		
		
		attribute.addFlashAttribute("mensagem", "Salvo com sucesso");
		return ("redirect:/clientes/cadastrarCliente");
	}
	
	//Cadastro cliente tela Comprador
	@RequestMapping(value="/clientes/cadastrarCliente/PJ", method=RequestMethod.POST)
	public String salvarClientePj(PessoaJuridica pessoaJuridica, Endereco endereco, Usuario usuario, BindingResult result, RedirectAttributes attribute) {
		if(result.hasErrors()) {
			attribute.addFlashAttribute("mensagem", "Verifique os campos em branco"); 
		}
		er.save(endereco);
		ur.save(usuario);
		pessoaJuridica.setUsuario(usuario);
		pessoaJuridica.getEnderecos().add(endereco);
		pjr.save(pessoaJuridica);
		usuario.setPessoa(pessoaJuridica);
		ur.save(usuario);
				
		attribute.addFlashAttribute("mensagem", "Salvo com sucesso");
		return ("redirect:/clientes/cadastrarCliente/PJ");
	}
	
	
	
	//Cadastro cliente tela Administrativo
	@RequestMapping(value="/clientesAdm/cadastrarCliente", method=RequestMethod.GET)
	public String salvar() {
		return("/admin/clienteAdm/cadastroPF");
	}
	
	//Cadastro cliente tela Administrativo
	@RequestMapping(value="/clientesAdm/cadastrarCliente/PJ", method=RequestMethod.GET)
	public String salvarPj() {
		return("/admin/clienteAdm/cadastroPJ");
	}
	
	//Cadastro cliente tela Administrativo
	@RequestMapping(value="/clientesAdm/cadastrarCliente", method=RequestMethod.POST)
	public String salvarC(PessoaFisica pessoaFisica, Endereco endereco, Usuario usuario, BindingResult result, RedirectAttributes attribute) {
		if(result.hasErrors()) {
			attribute.addFlashAttribute("mensagem", "Verifique os campos em branco"); 
		}
		
		er.save(endereco);
		ur.save(usuario);
		pessoaFisica.setUsuario(usuario);
		pessoaFisica.getEnderecos().add(endereco);
		pfr.save(pessoaFisica);
		usuario.setPessoa(pessoaFisica);
		ur.save(usuario);
				
		attribute.addFlashAttribute("mensagem", "Salvo com sucesso");
		return ("redirect:/clientesAdm/cadastrarCliente");
	}
		
	//Cadastro cliente tela Administrativo
	@RequestMapping(value="/clientesAdm/cadastrarCliente/PJ", method=RequestMethod.POST)
	public String salvarPj(PessoaJuridica pessoaJuridica, Endereco endereco, Usuario usuario, BindingResult result, RedirectAttributes attribute) {
		if(result.hasErrors()) {
			attribute.addFlashAttribute("mensagem", "Verifique os campos em branco"); 
		}
		er.save(endereco);
		ur.save(usuario);
		pessoaJuridica.setUsuario(usuario);
		pessoaJuridica.getEnderecos().add(endereco);
		pjr.save(pessoaJuridica);
		usuario.setPessoa(pessoaJuridica);
		ur.save(usuario);
				
		
		attribute.addFlashAttribute("mensagem", "Salvo com sucesso");
		return ("redirect:/clientesAdm/cadastrarCliente");
	}
	
	//Acesso a cliente administrativo
	@GetMapping("/clientesAdm")
	public ModelAndView listaClientes(){
		ModelAndView mv = new ModelAndView("/admin/clienteAdm/listaClientes");
		Iterable<Pessoa> clientes = pr.findByStatus(true); 
		mv.addObject("pessoas", clientes);
		return mv;
	}
	
	//Acesso do cliente
	@GetMapping("/clientes/minhaConta")
	public ModelAndView MinhaConta(@AuthenticationPrincipal Usuario usuario, RedirectAttributes attribute) {
		ModelAndView mv = new ModelAndView("/cliente/minhaConta");
		Optional<Usuario> u = ur.findById(usuario.getId_usuario());
		Usuario usu = u.get();
		mv.addObject("pessoas", usu);
		return mv;
	}
	
	//Visualizar Cliente Administrativo
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
	
	//Acesso ADM ao cliente editar PF
	@RequestMapping(value="/clientesAdm/editarClientes/pf/{id_pessoa}", method=RequestMethod.GET)
	public ModelAndView editarClientepf(@PathVariable ("id_pessoa") Long id_pessoa, RedirectAttributes attribute ) {
		ModelAndView mv = new ModelAndView("/admin/clienteAdm/editarCliente");
		Optional<Pessoa> p = pr.findById(id_pessoa);
		Pessoa cli = p.get();
		mv.addObject("pessoas", cli);
		mv.addObject("enderecos", cli.getEnderecos());
		mv.addObject("usuario", cli.getUsuario());
		attribute.addFlashAttribute("mensagem", "Editado com sucesso");
		return mv;		
	}
	
	//Acesso ADM ao cliente Salva editar PF
	@RequestMapping(value="/clientesAdm/editarClientes/pf/{id_pessoa}", method=RequestMethod.POST)
	public String salvaEdicaoPF(PessoaFisica c, RedirectAttributes attribute) {
		Optional<PessoaFisica> p = pfr.findById(c.getId_pessoa());
		PessoaFisica consultada = p.get();
		Set<Endereco> e = consultada.getEnderecos();
		c.setEnderecos(e);
		
		pfr.save(c);
				
		attribute.addFlashAttribute("mensagem", "Alterado com sucesso");
		return ("redirect:/clientesAdm/editarClientes/pf/{id_pessoa}");
			
		}
	
	//Acesso ADM ao cliente editar PJ
	@RequestMapping(value="/clientesAdm/editarClientes/pj/{id_pessoa}", method=RequestMethod.GET)
	public ModelAndView editarClientepj(@PathVariable ("id_pessoa") Long id_pessoa, RedirectAttributes attribute ) {
		ModelAndView mv = new ModelAndView("/admin/clienteAdm/editarCliente");
		Optional<Pessoa> p = pr.findById(id_pessoa);
		Pessoa cli = p.get();
		
		Set<Endereco> auxilio = new HashSet<>();
		if(cli.getEnderecos() != null || !cli.getEnderecos().isEmpty()) {
			for(Endereco e: cli.getEnderecos()) {
				if(e.isStatus() == true) {
					auxilio.add(e);	
				}
				
			}
		}
		
		mv.addObject("pessoas", cli);
		mv.addObject("enderecos", auxilio);
		mv.addObject("usuario", cli.getUsuario());
		attribute.addFlashAttribute("mensagem", "Editado com sucesso");
		return mv;
		
	}
	
		
	//Acesso ADM ao cliente Salvar editar PJ
	@RequestMapping(value="/clientesAdm/editarClientes/pj/{id_pessoa}", method=RequestMethod.POST)
	public String salvaEdicaoPJ(PessoaJuridica c, RedirectAttributes attribute) {
		Optional<PessoaJuridica> p = pjr.findById(c.getId_pessoa());
		PessoaJuridica consultada = p.get();
		Set<Endereco> e = consultada.getEnderecos();
		c.setEnderecos(e);
		
		pjr.save(c);
			
		attribute.addFlashAttribute("mensagem", "Alterado com sucesso");
		return ("redirect:/clientesAdm/editarClientes/pj/{id_pessoa}");	
	}
	
	//Acesso ADM ao cliente Salvar deletar PF
	@GetMapping("/clientesAdm/deletarClientes/pf/{id_pessoa}")
	public String deletarClientesPf(@PathVariable ("id_pessoa") Long id_pessoa, RedirectAttributes attribute) {
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
	
	//Acesso ADM ao cliente Salvar deletar PJ
	@GetMapping("/clientesAdm/deletarClientes/pj/{id_pessoa}")
	public String deletarClientesPj(@PathVariable ("id_pessoa") Long id_pessoa, RedirectAttributes attribute) {
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

	
	//Verifica se o cliente já tem conta, antes de realizar o cadastro
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
	
	//Edicao ENDERECO Administrativo
		@GetMapping("/clientesAdm/editarEndereco/{id}")
		public ModelAndView editarEndereco(@PathVariable ("id") Long id, RedirectAttributes attribute ) {
			ModelAndView mv = new ModelAndView("/admin/clienteAdm/edicaoEndereco");
			Optional<Endereco> e = er.findById(id);
			Endereco endereco = e.get();
			mv.addObject("endereco", endereco);
			attribute.addFlashAttribute("mensagem", "Editado com sucesso");
			return mv;
			
		}
		
		// Salva ENDERECO Edicao Administrativo
		@PostMapping("/clientesAdm/editarEndereco/{id}")
		public String SalvaEdicao(Endereco e, RedirectAttributes attribute ) {
			er.save(e);
			attribute.addFlashAttribute("mensagem", "Editado com sucesso");
			return ("redirect:/clientesAdm/editarEndereco/{id}");
			
		}
		
		// deletar ENDERECO Administrativo
		@GetMapping("/clientesAdm/deletarEndereco/{id}/{id_pessoa}")
		public String deletarUsuarios(@PathVariable ("id") Long id,@PathVariable ("id_pessoa") Long id_pessoa, RedirectAttributes attribute) {
			Optional<Endereco> endereco = er.findById(id);
			Optional<Pessoa> pessoa = pr.findById(id_pessoa);
			Endereco end = endereco.get();
			Pessoa p = pessoa.get();
			end.setStatus(false);
			end.getClientes().remove(p);
			p.getEnderecos().remove(end);
					
			er.save(end);
			attribute.addFlashAttribute("mensagem", "Deletado com sucesso");
			return ("redirect:/clientesAdm");
			
		}
	
		// Salva ENDERECO  
		@RequestMapping(value="/clientesAdm/cadastrarEndereco/{id_pessoa}", method=RequestMethod.GET)
		public ModelAndView salvarEndereco() {
			ModelAndView mv = new ModelAndView("/admin/clienteAdm/cadastroEndereco");
			return mv;
		}
		
		// Salva ENDERECO  
		@RequestMapping(value="/clientesAdm/cadastrarEndereco/{id_pessoa}", method=RequestMethod.POST)
		public String salvar(@PathVariable Long id_pessoa, Endereco endereco, BindingResult result, RedirectAttributes attribute) {
			if(result.hasErrors()) {
				attribute.addFlashAttribute("mensagem", "Verifique os campos em branco"); 
			}
			Optional<Pessoa> p = pr.findById(id_pessoa);
			Pessoa pessoa = p.get();
			endereco.getClientes().add(pessoa);
			er.save(endereco);
			pessoa.getEnderecos().add(endereco);
			pr.save(pessoa);
			
			
			attribute.addFlashAttribute("mensagem", "Salvo com sucesso");
			return ("redirect:/clientesAdm/cadastrarEndereco/"+id_pessoa);
		}
	
	
	
	
}
