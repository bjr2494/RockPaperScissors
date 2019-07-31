package day4.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.annotation.SessionScope;

@Controller
@RequestMapping("/play")
public class IntroController {
	
	@Autowired
	private Round currentRound;

	@GetMapping
	public String intro(Model model) {
		model.addAttribute("round", currentRound);
		model.addAttribute("you", currentRound.getYou());
		model.addAttribute("comp", currentRound.getComp());
		model.addAttribute("openingUserMessage", "What will your move be?");
		model.addAttribute("openingCompMessage", "Will the computer beat you?");
		return "intro";
	}
	
	@PostMapping("/rock")
	public String rock() {
		currentRound.compChoice();
		currentRound.compMessage();
		currentRound.rock();
		return "redirect:/play";
	}
	
	@PostMapping("/paper")
	public String paper() {
		currentRound.compChoice();
		currentRound.compMessage();
		currentRound.paper();
		return "redirect:/play";
	}
	
	@PostMapping("/scissors")
	public String scissors() {
		currentRound.compChoice();
		currentRound.compMessage();
		currentRound.scissors();
		return "redirect:/play";
	}
	
	@PostMapping("/anew")
	public String anew() {
		currentRound.startAnew();
		return "redirect:/play";
	}

	@Bean
	@SessionScope
	Round currentRound() {
		return new Round();
	}
}
