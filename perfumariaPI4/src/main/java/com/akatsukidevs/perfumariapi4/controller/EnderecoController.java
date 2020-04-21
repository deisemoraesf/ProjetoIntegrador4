package com.akatsukidevs.perfumariapi4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.akatsukidevs.perfumariapi4.model.Endereco;
import com.akatsukidevs.perfumariapi4.repository.EnderecoRepository;

@RestController
public class EnderecoController {
	
	@Autowired 
	private EnderecoRepository er;
	
	@GetMapping("https://viacep.com.br/ws/{cep}/json/")
	public ResponseEntity<Endereco> consultaCEPpf(@PathVariable ("cep") String cep, RedirectAttributes attribute) {
		Endereco endereco = er.findByCep(cep);
		
		attribute.addFlashAttribute("mensagem", "Deletado com sucesso");
		return ResponseEntity.ok(endereco);
		
	}
}
