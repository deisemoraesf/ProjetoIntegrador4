package com.akatsukidevs.perfumariapi4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.akatsukidevs.perfumariapi4.model.Usuario;
import com.akatsukidevs.perfumariapi4.repository.UsuarioRepository;

@Controller
public class UsuarioController {

	@Autowired	
	private UsuarioRepository ur;
	
	
	@RequestMapping(value="/cadastrarUsuario", method=RequestMethod.GET)
	public String salvar() {
		return("/admin/usuarios/cadastroUsuarios");
	}
	
	//para cadastro do usuario solicitando o post
	@RequestMapping(value="/cadastrarUsuario", method=RequestMethod.POST)
	public String salvar(Usuario usuario, BindingResult result, RedirectAttributes attribute) {
		if(result.hasErrors()) {
			attribute.addFlashAttribute("mensagem: ", "Verifique os campos em branco"); 
		}
		ur.save(usuario);
		attribute.addFlashAttribute("mensagem: ", "Salvo com sucesso");
		return ("redirect:/cadastrarUsuario");
	}

	@RequestMapping("/usuarios")
	public ModelAndView listaUsuarios() {
		ModelAndView mv = new ModelAndView("/admin/usuarios/listaUsuarios");
		Iterable<Usuario> usuarios = ur.findByStatus(true);
			mv.addObject("usuarios", usuarios);
			return mv;
		
	}
	
	/*@RequestMapping(value="/{id_usuario}", method=RequestMethod.GET)
	public String deletarUsuarios(@PathVariable ("id_usuario") Long id, RedirectAttributes attribute) {
		ur.id(id);
		//u.setStatus(false);
		//ur.save(u);
		attribute.addFlashAttribute("mensagem: ", "Deletado com sucesso");
		return ("redirect:/usuarios");
		
	}*/
	
}
