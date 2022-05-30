package ar.edu.unju.fi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PaginaController {

	@GetMapping("/inicio")
	public String mostrarPaginaInicio() {
		return "index";
	}
	@GetMapping("/votar")
	public ModelAndView redireccionarPaginaVotar() {
		ModelAndView mav = new ModelAndView("redirect:/usuario/nuevo");
		return mav;
	}

}
