package ar.edu.unju.fi.controller;

import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;
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

import ar.edu.unju.fi.model.Candidato;
import ar.edu.unju.fi.service.ICandidatoService;

@Controller
@RequestMapping("/candidato")
public class CandidatoController {
	
	@Autowired
	@Qualifier("CandidatoServiceImpList")
	private ICandidatoService candidatoService;
	
	private static final Log LOGGER = LogFactory.getLog(CandidatoController.class);
	//ListaCandidato listaCandidatos = new ListaCandidato();
	
	@GetMapping("/nuevo")
	public String getFormNuevoAlumnnoPage(Model model) {
		
		model.addAttribute("candidato", candidatoService.getCandidato());
		
		return "nuevo_candidato";
	}
	
	@PostMapping("/guardar")
	public ModelAndView getListaCandidatosPage(@Validated @ModelAttribute("candidato")Candidato candidato,
			BindingResult bindingResult) {
		//el objeto bindingResult contiene el resultado de la validación, 
		//(los errores que pueden haber ocurrido).
		if(bindingResult.hasErrors()) {
			ModelAndView mav = new ModelAndView("nuevo_candidato");
			mav.addObject("candidato", candidato);
			return mav;
		}
		ModelAndView mav = new ModelAndView("redirect:/candidato/listacandidatos");
		
		//agrego un objeto candidato a la lista
		if(candidatoService.guardarCandidato(candidato)) {
			LOGGER.info("Se agregó un objeto al arrayList de candidatos");
		}
		return mav;
	}
	
	@GetMapping("/listacandidatos")
	public ModelAndView getListaCandidatosPage() {
		ModelAndView mav = new ModelAndView("lista_candidatos");
		mav.addObject("candidatos",candidatoService.getListaCandidato().getCandidatos());
		return mav;
	}
	
	/**
	 * permite mostrar el formulario para edición
	 * @param codigo
	 * @return
	 */
	@GetMapping("/editar/{codigo}")
	public ModelAndView getEditarCandidatoPage(@PathVariable(value="codigo")int codigo) {
		ModelAndView mav = new ModelAndView("editar_candidato");
		Candidato candidato = candidatoService.buscarCandidato(codigo);
		mav.addObject("candidato", candidato);
		return mav;
	}

	@GetMapping("/votar/{email}")
	public ModelAndView getEditarCandidatoPage(@PathVariable(value="email")String email) {
		ModelAndView mav = new ModelAndView("mostrar_candidatos");
		mav.addObject("candidatos",candidatoService.getListaCandidato().getCandidatos());
		mav.addObject("email", email);
		return mav;
	}
	
	@GetMapping("/candidato/sumarvoto/{codigo}")
	public ModelAndView getSumarVotoCandidatoPage(@PathVariable(value="codigo")int codigo) {
		if(candidatoService.sumarVotoCandidato(codigo)) {
			ModelAndView mav = new ModelAndView("voto_finalizar");
			mav.addObject("mensage", "Gracias, por realizar la votacion");
			return mav;
		}
		ModelAndView mav = new ModelAndView("voto_finalizar");
		mav.addObject("mensage", "No se encontro al Candidato");
		return mav;
	}
	
	@PostMapping("/modificar")
	public ModelAndView editarDatosCandidato(@Validated @ModelAttribute("candidato") Candidato candidato, BindingResult bindingResult ) {
		if(bindingResult.hasErrors()) {
			LOGGER.info("ocurrió un error "+candidato);
			ModelAndView mav = new ModelAndView("editar_candidato");
			mav.addObject("candidato", candidato);
			return mav;
		}
		
		ModelAndView mav = new ModelAndView("redirect:/candidato/listacandidatos");
		candidatoService.modificarCandidato(candidato);
		return mav;
		
	}
	
	@GetMapping("/eliminar/{codigo}")
	public ModelAndView eliminarCandidato(@PathVariable("codigo")int codigo) {
		ModelAndView mav = new ModelAndView("redirect:/candidato/listacandidatos");
		candidatoService.eliminarCandidato(codigo);
		return mav;
		
	}
	
	@GetMapping("/estadovotacion")
	public ModelAndView getResultadoCandidatosPage() {
		ModelAndView mav = new ModelAndView("resultado_candidatos");
		mav.addObject("candidatos",candidatoService.getListaCandidato().getCandidatos());
		mav.addObject("totalVotos", (Integer)candidatoService.calcularTotalVotos());
		return mav;
	}

}
