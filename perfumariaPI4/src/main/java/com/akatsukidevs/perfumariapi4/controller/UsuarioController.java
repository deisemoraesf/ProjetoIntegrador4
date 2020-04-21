package com.akatsukidevs.perfumariapi4.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	
	@RequestMapping(value="/usuarios/cadastrarUsuario", method=RequestMethod.GET)
	public String salvar() {
		return("/admin/usuarios/cadastroUsuarios");
	}
	
	//para cadastro do usuario solicitando o post
	@RequestMapping(value="/usuarios/cadastrarUsuario", method=RequestMethod.POST)
	public String salvar(Usuario usuario, BindingResult result, RedirectAttributes attribute) {
		if(result.hasErrors()) {
			attribute.addFlashAttribute("mensagem", "Verifique os campos em branco"); 
		}
		ur.save(usuario);
		attribute.addFlashAttribute("mensagem", "Salvo com sucesso");
		return ("redirect:/usuarios/cadastrarUsuario");
	}

	@GetMapping("/usuarios")
	public ModelAndView listaUsuarios() {
		ModelAndView mv = new ModelAndView("/admin/usuarios/listaUsuarios");
		Iterable<Usuario> usuarios = ur.findByStatus(true);
			mv.addObject("usuarios", usuarios);
			return mv;
		
	}
	
	@RequestMapping(value="/usuarios/editarUsuarios/{id_usuario}", method=RequestMethod.GET)
	public ModelAndView editarUsuario(@PathVariable ("id_usuario") Long id_usuario, RedirectAttributes attribute ) {
		ModelAndView mv = new ModelAndView("/admin/usuarios/editarUsuario");
		Optional<Usuario> u = ur.findById(id_usuario);
		Usuario usu = u.get();
		mv.addObject("usuario", usu);
		attribute.addFlashAttribute("mensagem", "Editado com sucesso");
		return mv;
		
	}
	
	@RequestMapping(value="/usuarios/editarUsuarios/{id_usuario}", method=RequestMethod.POST)
	public String salvaEdicao(Usuario u, RedirectAttributes attribute) {
		ur.save(u);
		attribute.addFlashAttribute("mensagem", "Editado com Sucesso");
		return ("redirect:/usuarios/editarUsuarios/{id_usuario}");
	}
	
	@GetMapping("/usuarios/deletarUsuarios/{id_usuario}")
	public String deletarUsuarios(@PathVariable ("id_usuario") Long id_usuario, RedirectAttributes attribute) {
		Optional<Usuario> u = ur.findById(id_usuario);
		Usuario usu = u.get();
		usu.setStatus(false);
		ur.save(usu);
		attribute.addFlashAttribute("mensagem", "Deletado com sucesso");
		return ("redirect:/usuarios");
		
	}
	
	//tem que melhorar o método
	//@PostMapping("**/pesquisaUsuario")
	/*public ModelAndView pesquisar(@RequestParam ("pesquisaemail") String pesquisaemail, RedirectAttributes attribute) {
		ModelAndView mv = new ModelAndView("/admin/usuarios/listaUsuarios");
		Usuario usu = ur.findByEmail(pesquisaemail);
			if(usu.getStatus() == true) {
				mv.addObject("usuarios", usu);
			}
		return mv;
		
	}*/
}
