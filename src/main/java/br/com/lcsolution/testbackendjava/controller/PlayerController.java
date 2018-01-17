package br.com.lcsolution.testbackendjava.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.lcsolution.testbackendjava.domain.Player;
import br.com.lcsolution.testbackendjava.service.PlayerService;

@Controller
public class PlayerController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PlayerController.class);
	
	@Autowired
	private PlayerService playerService;
	
	@GetMapping("newPlayer")
	public ModelAndView newPlayerView(Model model){
		model.addAttribute("player", new Player());
		return new ModelAndView("newplayer");
	}
	
	@PostMapping("savePlayer")
	public ModelAndView savePlayer(@ModelAttribute @Valid Player request, BindingResult result, RedirectAttributes redirectAttributes){
		ModelAndView modelAndView = new ModelAndView("redirect:/newPlayer");
		if (result.hasErrors()){
			LOGGER.info("ERROR SAVING CONVERSION: "+result.getFieldError());
			redirectAttributes.addFlashAttribute("error", "Erro ao salvar alteração de pontos, tente novamente!");
			return modelAndView;
		}else{
			try {
	             playerService.registerPlayer(request,redirectAttributes,modelAndView);
	         } catch (Exception e) {
	             e.printStackTrace();
	             redirectAttributes.addFlashAttribute("error", "Erro ao salvar alteração de pontos, tente novamente!");
	             return modelAndView;
	         }
		return modelAndView;
		}
	}
	
	@GetMapping("listPlayer")
	public ModelAndView listPlayer(Model model){
		List<Player> players = playerService.getPlayers();
		model.addAttribute("listplayer", players);
		return new ModelAndView("listplayer");
	}
}
