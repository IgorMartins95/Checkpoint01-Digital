package br.com.fiap.epictask.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.fiap.epictask.model.Usuarios;
import br.com.fiap.epictask.repository.UsuariosRepository;

@Controller
public class LoginController {
	
	@Autowired
	private UsuariosRepository repository;
	
	@GetMapping("/usuarios")
	public ModelAndView index() {
		List<Usuarios> usuarios = repository.findAll();
		ModelAndView modelAndView = new ModelAndView("usuarios");
		modelAndView.addObject("usuarios", usuarios);
		return modelAndView;
	}
	
	@PostMapping("/usuarios")
	public String save(@Valid Usuarios usuarios, BindingResult result) {
		if (result.hasErrors()) return "newuser";
		repository.save(usuarios);
		return "usuarios";
	}
	
	@RequestMapping("/login/new")
	public String create(Usuarios usuarios) {
		return "newuser";
	}
	
	@RequestMapping("/login") 
	public String index2() {	
		return "login";
	}
	
		
}
