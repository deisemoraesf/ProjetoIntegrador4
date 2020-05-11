package com.akatsukidevs.perfumariapi4.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.akatsukidevs.perfumariapi4.model.FotoProduto;
import com.akatsukidevs.perfumariapi4.model.Produto;
import com.akatsukidevs.perfumariapi4.repository.FotoProdutoRepository;
import com.akatsukidevs.perfumariapi4.repository.ProdutoRepositorios;


//@SpringBootApplication
@Controller
public class ProdutoController {
	
	
	
	@Autowired
	private ProdutoRepositorios pr;
	
	@Autowired
	private FotoProdutoRepository fpr;
	
	
	@RequestMapping(value="/produtos/cadastrarProduto", method=RequestMethod.GET)
	public String salvar() {
		return("/admin/produtos/cadastroProdutos");
	}
	

	//para cadastro do produto solicitando o post
	@SuppressWarnings("null")
	@RequestMapping(value="/produtos/cadastrarProduto", method=RequestMethod.POST)
	public String salvar(Produto produto, @RequestParam("files") MultipartFile [] files, BindingResult result, RedirectAttributes attribute) {
		String urlPasta = "C:/Users/Deise/Documents/workspace-spring-tool-suite-4-4.5.1.RELEASE/perfumariaPI4/src/main/resources/static/";
		
		if(result.hasErrors()) {
			attribute.addFlashAttribute("mensagem: ", "Verifique os campos em branco"); 
		}
		if(files.length == 0) {
			attribute.addFlashAttribute("mensagem: ", "Arquivo Invalido"); 
		}
		StringBuilder fileNames = new StringBuilder();
		for(MultipartFile file : files) {
			Path diretorio = Paths.get(urlPasta + file.getOriginalFilename());
			fileNames.append(file.getOriginalFilename()+" ");
			String url = diretorio.toString();
			FotoProduto fp = new FotoProduto();
			fp.setProduto(produto);
			fp.setStatus(true);
			fp.setUrl(url);
			fpr.save(fp);
			Set<FotoProduto> imagens = new HashSet<>();
			imagens.add(fp);
			produto.setImagens(imagens);
			
			try {
				Files.write(diretorio, file.getBytes());
			} catch (IOException e) {
				attribute.addFlashAttribute("mensagem: ", "Erro: "+e.getMessage()); 
			}
		}
		
				
		pr.save(produto);
		attribute.addFlashAttribute("mensagem: ", "Salvo com sucesso");
		return ("redirect:/produtos/cadastrarProduto");
	}

	@GetMapping("/produtos/listarProdutos")
	public ModelAndView listaProdutos() {
		ModelAndView mv = new ModelAndView("/admin/produtos/listaProdutos");
		Iterable<Produto> produtos = pr.findByStatus(true);
			mv.addObject("produtos", produtos);
			return mv;
		
	}
	
	@RequestMapping(value="/produtos/editarProdutos/{id_produto}", method=RequestMethod.GET)
	public ModelAndView editarProduto(@PathVariable ("id_produto") Long id_produto, RedirectAttributes attribute ) {
		ModelAndView mv = new ModelAndView("/admin/produtos/editarProduto");
		Optional<Produto> p = pr.findById(id_produto);
		Produto prod = p.get();
		mv.addObject("produto", prod);
		attribute.addFlashAttribute("mensagem: ", "Editado com sucesso");
		return mv;
		
	}
	
	@RequestMapping(value="/produtos/editarProdutos/{id_produto}", method=RequestMethod.POST)
	public String salvaEdicao(Produto p, RedirectAttributes attribute) {
		pr.save(p);
		attribute.addFlashAttribute("mensagem", "Editado com Sucesso");
		return ("redirect:/produtos/editarProdutos/{id_produto}");
	}
	
	@GetMapping("/produtos/deletarProdutos/{id_produto}")
	public String deletarProdutos(@PathVariable ("id_produto") Long id_produto, RedirectAttributes attribute) {
		Optional<Produto> p = pr.findById(id_produto);
		Produto prod = p.get();
		prod.setStatus(false);
		pr.save(prod);
		attribute.addFlashAttribute("mensagem: ", "Deletado com sucesso");
		return ("redirect:/produtos/listarProdutos");
		
	}
	
	@RequestMapping(value="/produtos/visualizarProdutos/{id_produto}", method=RequestMethod.GET)
	public ModelAndView visualizarProduto(@PathVariable ("id_produto") Long id_produto) {
		ModelAndView mv = new ModelAndView("/admin/produtos/detalhesProduto");
		Optional<Produto> p = pr.findById(id_produto);
		Produto prod = p.get();
		Iterable<FotoProduto> fotos = fpr.findByProduto(prod);
		mv.addObject("produto", prod);
		mv.addObject("fotos", fotos);
		return mv;
	}
	

	
	//@PostMapping("**/pesquisaUsuario")
	/*public ModelAndView pesquisar(@RequestParam ("pesquisaemail") String pesquisaemail) {
		ModelAndView mv = new ModelAndView("/admin/produtos/listaProdutos");
		mv.addObject("produto", ur.findByEmail(pesquisaemail));
		return mv;
	}*/
}
