package poker.version_graphics.controller;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import poker.version_graphics.model.Card;
import poker.version_graphics.model.DeckOfCards;
import poker.version_graphics.model.Player;
import poker.version_graphics.model.PokerGameModel;

import poker.version_graphics.view.PlayerPane;
import poker.version_graphics.view.PokerGameView;

public class PokerGameController {
	private PokerGameModel model;
	private PokerGameView view;

	public PokerGameController(PokerGameModel model, PokerGameView view) {
		this.model = model;
		this.view = view;

		view.getShuffleButton().setOnAction(e -> {
			enablePlayerBtn();
			shuffle();
			
		});
		view.getDealButton().setOnAction(e -> deal());
		view.getWinnerButton().setOnAction(e -> win());
		view.getAddPlayerButton().setOnAction(e -> checkPlayers());
		view.getAboutItem().setOnAction(e -> about());
		view.getGenRulesItem().setOnAction(e -> genRules());
		view.getHandsItem().setOnAction(e -> hands());
		view.getNewGameItem().setOnAction(e -> {
			newGame();
			enablePlayerBtn();
		});
		view.getCloseItem().setOnAction(e -> close());
		view.getRemovePlayerButton().setOnAction(e -> removePlayer());

	}

	/**
	 * Remove all cards from players hands, and shuffle the deck
	 */
	private void shuffle() {
		view.getAddPlayerButton().setDisable(false);
		for (int i = 0; i < model.getPlayersCount(); i++) {
			Player p = model.getPlayer(i);
			p.discardHand();
			PlayerPane pp = view.getPlayerPane(i);
			pp.updatePlayerDisplay();
		}

		model.getDeck().shuffle();
	}

	/**
	 * Deal each player five cards, then evaluate the two hands
	 */
	private void deal() {
		int cardsRequired = model.getPlayersCount() * Player.HAND_SIZE;
		DeckOfCards deck = model.getDeck();
		if (cardsRequired <= deck.getCardsRemaining()) {
			for (int i = 0; i < model.getPlayersCount(); i++) {
				Player p = model.getPlayer(i);
				p.discardHand();
				for (int j = 0; j < Player.HAND_SIZE; j++) {
					Card card = deck.dealCard();
					p.addCard(card);
				}
				p.evaluateHand();
				PlayerPane pp = view.getPlayerPane(i);
				pp.updatePlayerDisplay();

			}

		} else {
			Alert alert = new Alert(AlertType.ERROR, "Not enough cards - shuffle first");
			alert.showAndWait();

		}
	}

	private void win() {
		String content=("");
		view.getWinnerLabel().setStyle("-fx-text-fill: #FB0505");		
		content=model.win();
		animation("Winner: " + content);
	}

	private void removePlayer() {
		// TODO

	}

	private void addPlayer() {
		model.addPlayer();
		view.addPlayer(model, model.getPlayersCount() - 1);
	}

	private void checkPlayers() {
		if (model.getPlayersCount() >= 4) {
			view.getAddPlayerButton().setDisable(true);
		} else {
			addPlayer();
		}
	}

	public void enablePlayerBtn() {

		view.getAddPlayerButton().setDisable(false);

	}
	public void newGame() {
		shuffle();
	}

	public void close() {
		Platform.exit();
	}

	public void hands() {
		Label lbl= new Label("\t\t\t\tHands Ranking\n\n"
				+ "1. Royal Flush: A, K, Q, J, 10, all the same suit. \n\n"
				+ "2. Straight Flush: Five cards in a sequence, all of the same suit.\n\n"
				+ "3. Four of a Kind: All four cards of the same rank.\n\n"
				+ "4. Full House: Three cards of the same rank and two cards of a different matching rank.\n\n"
				+ "5. Flush: Any five cards of the same suit, but not in a sequence.\n\n"
				+ "6. Straight: Five cards in a sequence, but not of the same suit.\n\n"
				+ "7. Three of a Kind: Three cards of the same rank.\n\n"
				+ "8. Two Pair: Two different pairs."
				+ "9. Pair: Two cards of the same rank."
				+ "10. High Card: When you haven't made any "
				+ "of the hands above, the highest card plays.\t\t");
		view.createMenuStage("topMenuStage.css",lbl,new HBox());
	}
	public void genRules() {
		Label lbl= new Label("\t\t\t\tGENERAL RULES:\n\nThis is a \"5 Cards Stud\""
				+ " simpliest version of poker.\nUse a standart deck of 52 cards. In each round\n"
				+ "there is a winner or a tie depending on the type of hand that the players hold.\n"
				+ "For more information about hand ranking, go to \"Help-> RUles-> Hands\" menu\t\t\n\n");
		view.createMenuStage("topMenuStage.css", lbl,new HBox());
	}
	public void about() {
		Label lbl= new Label("This game has been done as an"
				+ " educaitional exercise from Kalina Nikolova and Ralf Fux\n" + 
				" 2nd semester in FHNW, Basel, 2019. \t\t");
		view.createMenuStage("topMenuStage.css", lbl,new HBox());//createStage(lbl);
		
	}
		public void animation(String content) {
			
		  Transition animation = new Transition() {
		     {
		         setCycleDuration(Duration.millis(2000));
		     }		 
		     protected  void interpolate(double split) {
		         final int length = content.length();
		         final int n = Math.round(length * (float) split);
		         view.getWinnerLabel().setText(content.substring(0, n));
		     }		 
		 };				 
		 animation.play();
	}
}
