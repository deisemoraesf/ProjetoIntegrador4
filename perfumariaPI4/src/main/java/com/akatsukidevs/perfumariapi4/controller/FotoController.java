package com.akatsukidevs.perfumariapi4.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class FotoController {
	public static String uploadDirectory = System.getProperty("user.dir")+"/upload";
	
	
	@RequestMapping("/uploadImage")
	public String UploadPage (Model model) {
		return "/admin/produtos/uploadview";
	}
	
	
	@RequestMapping("/upload")
	public String upload(Model model, @RequestParam("files") MultipartFile[] files) {
		StringBuilder fileNames = new StringBuilder();
		for(MultipartFile file : files) {
			Path fileNameAndPath = Paths.get(uploadDirectory, file.getOriginalFilename());
			fileNames.append(file.getOriginalFilename()+" ");
			try {
				Files.write(fileNameAndPath, file.getBytes());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		model.addAttribute("msg", "Arquivo Salvo com Sucesso!" + fileNames.toString());
		return "/admin/produtos/uploadstatusview";
		
	}
	


}
