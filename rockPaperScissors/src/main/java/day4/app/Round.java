package day4.app;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ModelAttribute;

@Component
@Scope("session")
public class Round {

	private User you = new User();
	private User comp = new User();
	private String roundMessage;
	private String endMessage;

	public User getYou() {
		return you;
	}

	public void setYou(@ModelAttribute("you") User you) {
		this.you = you;
	}

	public User getComp() {
		return comp;
	}

	public void setComp(@ModelAttribute("comp") User comp) {
		this.comp = comp;
	}

	public String getRoundMessage() {
		return roundMessage;
	}

	public void setRoundMessage(String roundMessage) {
		this.roundMessage = roundMessage;
	}

	public String getEndMessage() {
		return endMessage;
	}

	public void setEndMessage(String endMessage) {
		this.endMessage = endMessage;
	}

	public String compChoice() {
		List<String> options = Arrays.asList("rock", "paper", "scissors");
		Random rand = new Random();
		int number = rand.nextInt(options.size());
		switch (number) {
		case 0:
			comp.setChoice("rock");
			break;
		case 1:
			comp.setChoice("paper");
			break;
		case 2:
			comp.setChoice("scissors");
			break;
		}
		return comp.getChoice();
	}

	public String compMessage() {
		if (comp.getChoice().equalsIgnoreCase("rock"))
			comp.setMessage("The computer's choice is rock");
		if (comp.getChoice().equalsIgnoreCase("paper"))
			comp.setMessage("The computer's choice is paper");
		if (comp.getChoice().equalsIgnoreCase("scissors"))
			comp.setMessage("The computer's choice is scissors");
		return comp.getMessage();
	}

	public void rock() {
		you.setMessage("Your move is rock");
		you.setChoice("rock");
		String rock = you.getChoice();
		String compMove = comp.getChoice();
		this.tie();
		if (rock.equalsIgnoreCase("rock") & (compMove.equalsIgnoreCase("paper"))) {
			this.roundMessage = "Rock loses to Paper. The computer gains a point.";
			this.increaseCompScore();
		}
		if (rock.equalsIgnoreCase("rock") & (compMove.equalsIgnoreCase("scissors"))) {
			this.roundMessage = "Rock beats Scissors! You gain a point!";
			this.increaseYouScore();
		}
		this.scoreHandling();
	}

	public void paper() {
		you.setMessage("Your move is paper");
		you.setChoice("paper");
		String paper = you.getChoice();
		String compMove = comp.getChoice();
		this.tie();
		if (paper.equalsIgnoreCase("paper") & (compMove.equalsIgnoreCase("scissors"))) {
			this.roundMessage = "Paper loses to Scissors. The computer gains a point.";
			this.increaseCompScore();
		}
		if (paper.equalsIgnoreCase("paper") & (compMove.equalsIgnoreCase("rock"))) {
			this.roundMessage = "Paper beats Rock! You gain a point!";
			this.increaseYouScore();
		}
		this.scoreHandling();
	}

	public void scissors() {
		you.setMessage("Your move is scissors");
		you.setChoice("scissors");
		String scissors = you.getChoice();
		String compMove = comp.getChoice();
		this.tie();
		if (scissors.equalsIgnoreCase("scissors") & (compMove.equalsIgnoreCase("rock"))) {
			this.roundMessage = "Scissors loses to Rock. The computer gains a point.";
			this.increaseCompScore();
		}
		if (scissors.equalsIgnoreCase("scissors") & (compMove.equalsIgnoreCase("paper"))) {
			this.roundMessage = "Scissors beats Paper! You gain a point!";
			this.increaseYouScore();
		}
		this.scoreHandling();
	}

	public void startAnew() {
		this.roundMessage = "Here we go!";
		you.setScore(0);
		you.setChoice("");
		you.setMessage("Make a move!");
		comp.setScore(0);
		comp.setChoice("");
		comp.setMessage("Which move will I make?");
		this.endMessage = "";
	}

	private void tie() {
		if (you.getChoice().equals(comp.getChoice()))
			this.roundMessage = "A tie! Go again.";
	}

	private void increaseYouScore() {
		you.setScore(you.getScore() + 1);
	}

	private void increaseCompScore() {
		comp.setScore(comp.getScore() + 1);
	}

	private void scoreHandling() {
		if (comp.getScore() == 5)
			this.endMessage = "The computer wins. Play again if you wish";
		if (you.getScore() == 5) {
			this.endMessage = "You win! Play again.";
		}
	}
}
