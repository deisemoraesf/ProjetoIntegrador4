package com.akatsukidevs.perfumariapi4.controller;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.akatsukidevs.perfumariapi4.model.Endereco;
import com.akatsukidevs.perfumariapi4.model.Pessoa;
import com.akatsukidevs.perfumariapi4.repository.EnderecoRepository;
import com.akatsukidevs.perfumariapi4.repository.PessoaRepository;

@RestController
public class EnderecoController {
	
	@Autowired 
	private EnderecoRepository er;
	
	@Autowired 
	private PessoaRepository pr;
	
	@RequestMapping(value="/clientesAdm/cadastrarEndereco/{id_pessoa}", method=RequestMethod.GET)
	public ModelAndView salvar() {
		ModelAndView mv = new ModelAndView("/admin/clienteAdm/formEndereco");
		return mv;
	}
	
	@RequestMapping(value="/clientesAdm/cadastrarEndereco/{id_pessoa}", method=RequestMethod.POST)
	public String salvar(@PathVariable Long id_pessoa, Endereco endereco, BindingResult result, RedirectAttributes attribute) {
		if(result.hasErrors()) {
			attribute.addFlashAttribute("mensagem", "Verifique os campos em branco"); 
		}
		er.save(endereco);
		Set<Endereco> enderecosadicionado = new HashSet<>();
		enderecosadicionado.add(endereco);
		Optional<Pessoa> cli = pr.findById(id_pessoa);
		Pessoa cliente = cli.get();
		if(cliente.getEnderecos() != null && cliente.getEnderecos().isEmpty()) {
			Set<Endereco> enderecosCad = cliente.getEnderecos();
			for(Endereco e : enderecosCad) {
				enderecosadicionado.add(e);
				cliente.setEnderecos(enderecosadicionado);
			}
		}	
		Set<Pessoa> pessoaadd = new HashSet<>();
		if(endereco.getClientes() != null && endereco.getClientes().isEmpty()) {
			Set<Pessoa> clienteCad = endereco.getClientes();
			for(Pessoa c : clienteCad) {
				pessoaadd.add(c);
				endereco.setClientes(pessoaadd);
				er.save(endereco);
			}
		}
		
		attribute.addFlashAttribute("mensagem", "Salvo com sucesso");
		return ("redirect:/clientesAdm/cadastrarEndereco/"+id_pessoa);
	}
	
	
	@RequestMapping(value="/clientesAdm/editarEndereco/{id}", method=RequestMethod.GET)
	public ModelAndView editarEndereco(@PathVariable ("id") Long id, RedirectAttributes attribute ) {
		ModelAndView mv = new ModelAndView("/admin/clienteAdm/formEndereco");
		Optional<Endereco> e = er.findById(id);
		Endereco endereco = e.get();
		mv.addObject("enderecos", endereco);
		attribute.addFlashAttribute("mensagem", "Editado com sucesso");
		return mv;
		
	}
}