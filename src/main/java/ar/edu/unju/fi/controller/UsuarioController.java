package ar.edu.unju.fi.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.model.Usuario;
import ar.edu.unju.fi.service.IUsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	@Autowired
	@Qualifier("UsuarioServiceImpList")
	private IUsuarioService usuarioService;

	private static final Log LOGGER = LogFactory.getLog(UsuarioController.class);
	
	@GetMapping("/nuevo")
	public String getFormNuevoUsuarioPage(Model model) {
		model.addAttribute("usuario", usuarioService.getUsuario());
		return "nuevo_usuario";
	}
	
	@PostMapping("/guardar")
	public ModelAndView getListaUsuariosPage(@Validated @ModelAttribute("usuario")Usuario usuario,
			BindingResult bindingResult) {
		//el objeto bindingResult contiene el resultado de la validacion, 
		//(los errores que pueden haber ocurrido).
		if(bindingResult.hasErrors()) {
			ModelAndView mav = new ModelAndView("nuevo_usuario");
			mav.addObject("usuario", usuario);
			return mav;
		}
		ModelAndView mav = new ModelAndView("redirect:/candidato/votar/"+usuario.getEmail());
		
		//agrego un objeto usuario a la lista
		if(usuarioService.guardarUsuario(usuario)) {
			LOGGER.info("Se registra usuario "+usuario.getNombre());
		}
		return mav;
	}
	
	@GetMapping("/votar/{email}/candidato/{codigo}")
	public ModelAndView emitirVotoUsuario(@PathVariable(value="email")String email,@PathVariable(value="codigo")int codigo) {
		if(usuarioService.emitirVotoUsuario(email)) {
			ModelAndView mav = new ModelAndView("redirect:/candidato/sumarvoto/"+codigo);			
			return mav;
		}
		ModelAndView mav = new ModelAndView("voto_finalizar");
		mav.addObject("mensage", "solo se permiten 3 votos por usuario");
		return mav;
	}
	
	@GetMapping("/listausuarios")
	public ModelAndView getListaUsuariosPage() {
		ModelAndView mav = new ModelAndView("lista_usuarios");
		mav.addObject("usuarios",usuarioService.getListaUsuario().getUsuarios());
		return mav;
	}
}
