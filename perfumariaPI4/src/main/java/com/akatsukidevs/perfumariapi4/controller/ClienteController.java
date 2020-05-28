package com.akatsukidevs.perfumariapi4.controller;

import java.security.Principal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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
	
	//Cadastro cliente tela Comprador
	@RequestMapping(value="/clientes/cadastrarCliente/PJ", method=RequestMethod.POST)
	public String salvarClientePj(PessoaJuridica pessoaJuridica, Endereco endereco, Usuario usuario, BindingResult result, RedirectAttributes attribute) {
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
		
		Set<Endereco> enderecosadicionado = new HashSet<>();
		enderecosadicionado.add(endereco);
		er.save(endereco);
		pessoaFisica.setEnderecos(enderecosadicionado);
		ur.save(usuario);				
		usuario.setPessoa(pessoaFisica);
		pessoaFisica.setUsuario(usuario);
		pfr.save(pessoaFisica);
		attribute.addFlashAttribute("mensagem", "Salvo com sucesso");
		return ("redirect:/clientesAdm/cadastrarCliente");
	}
		
	//Cadastro cliente tela Administrativo
	@RequestMapping(value="/clientesAdm/cadastrarCliente/PJ", method=RequestMethod.POST)
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
		return ("redirect:/clientesAdm/cadastrarCliente");
	}

	@GetMapping("/clientesAdm")
	public ModelAndView listaClientes(){
		ModelAndView mv = new ModelAndView("/admin/clienteAdm/listaClientes");
		Iterable<Pessoa> clientes = pr.findByStatus(true); 
		mv.addObject("pessoas", clientes);
		return mv;
	}
	
	
    @RequestMapping(value = "/minhaConta", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView currentUserName(Principal principal) {
    	ModelAndView mv = new ModelAndView("/cliente/minhaConta");
		Usuario u = ur.findByEmail(principal.getName());
		Pessoa cliente = u.getPessoa();
		mv.addObject("enderecos", cliente.getEnderecos());
		mv.addObject("pessoas", cliente);
		mv.addObject("usuario", cliente.getUsuario());
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
	
	
	@RequestMapping(value="/clientesAdm/editarClientes/pj/{id_pessoa}", method=RequestMethod.GET)
	public ModelAndView editarClientepj(@PathVariable ("id_pessoa") Long id_pessoa, RedirectAttributes attribute ) {
		ModelAndView mv = new ModelAndView("/admin/clienteAdm/editarCliente");
		Optional<Pessoa> p = pr.findById(id_pessoa);
		Pessoa cli = p.get();
		mv.addObject("pessoas", cli);
		mv.addObject("enderecos", cli.getEnderecos());
		mv.addObject("usuario", cli.getUsuario());
		attribute.addFlashAttribute("mensagem", "Editado com sucesso");
		return mv;
		
	}
	
	
	@RequestMapping(value="/clientesAdm/editarClientes/pf/{id_pessoa}", method=RequestMethod.POST)
	public String salvaEdicaoPF(PessoaFisica c, RedirectAttributes attribute) {
			Set<Endereco> enderecosadicionado = c.getEnderecos();
			pfr.save(c);
			Set<Endereco> enderecos = new HashSet<>();
			for(Endereco end : enderecosadicionado) {
				enderecos.add(end);
				er.save(end);
				c.setEnderecos(enderecos);
			}
			pfr.save(c);
			attribute.addFlashAttribute("mensagem", "Alterado com sucesso");
			return ("redirect:/clientesAdm/editarClientes/pf/{id_pessoa}");
		
	}
	
	@RequestMapping(value="/clientesAdm/editarClientes/pj/{id_pessoa}", method=RequestMethod.POST)
	public String salvaEdicaoPJ(PessoaJuridica c, RedirectAttributes attribute) {
			Set<Endereco> enderecosadicionado = c.getEnderecos();
			pjr.save(c);
			c.setEnderecos(enderecosadicionado);
			attribute.addFlashAttribute("mensagem", "Alterado com sucesso");
			return ("redirect:/clientesAdm/editarClientes/pj/{id_pessoa}");	
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
