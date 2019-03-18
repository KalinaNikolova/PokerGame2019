package poker.version_graphics.controller;
import java.util.ArrayList;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
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
		

		view.getShuffleButton().setOnAction(e -> {shuffle();enablePlayerBtn();});
		view.getDealButton().setOnAction(e -> deal());
		view.getWinnerButton().setOnAction(e -> win());
		view.getAddPlayerButton().setOnAction(e -> checkPlayers());
		view.getAboutItem().setOnAction(e-> about());
		view.getGenRulesItem().setOnAction(e-> genRules());
		view.getHandsItem().setOnAction(e-> hands());
		view.getNewGameItem().setOnAction(e->{newGame();enablePlayerBtn();});
		view.getChangeCoverImage().setOnAction(e-> changeCoverImage());// not working yet
		view.getCloseItem().setOnAction(e->close());
		
	}

	/**
	 * Remove all cards from players hands, and shuffle the deck
	 */
	private void shuffle() {
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
		
		Player winner = model.win();
		view.getWinnerLabel().setText(winner.getPlayerName());
	}

	private void addPlayer() {
		model.addPlayer();
		view.addPlayer(model, model.getPlayersCount()-1);
	}
	private void checkPlayers() {
		if(model.getPlayersCount()>=4) {
			view.getAddPlayerButton().setDisable(true);	
			}
			else {
				addPlayer();
			}
	}
	public void enablePlayerBtn() {
		
			view.getAddPlayerButton().setDisable(true);
		
	}

	public void newGame() {
		shuffle();
	}

	public void changeCoverImage() {
		//TODO
	
	}

	public void close() {
	Platform.exit();
	}

	public void hands() {
		Label lbl= new Label("\t\t\t\t1Hands Ranking\n\n"
				+ "1. Royal flush: A, K, Q, J, 10, all the same suit. \n\n"
				+ "2. Straight Flush: Five cards in a sequence, all in the same suit.\n\n"
				+ "3. Four of a kind: All four cards of the same rank.\n\n"
				+ "4. Full house: Three of a kind with a pair.\n\n"
				+ "5. Flush: Any five cards of the same suit, but not in a sequence.\n\n"
				+ "6. Straight: Five cards in a sequence, but not of the same suit.\n\n"
				+ "7. Three of a kind: Three cards of the same rank.\n\n"
				+ "8. Two pair: Two different pairs.\n\n"
				+ "9. Pair: Two cards of the same rank.\n\n"
				+ "10. Hight card: When you haven't made any of the hands above, the highest card plays.");
		
		createStage(lbl);
	}
	public void genRules() {
		Label lbl= new Label("\t\t\t\tGENERAL RULES:\n\nThis is a \"5 Cards Stud\""
				+ " simpliest version of poker.\nUse a standart deck of 52 cards. In each round\n"
				+ "there is a Winner depend on the type of hand that players hold.\n"
				+ "For more information about hand ranking, go to \"Help-> Hands\" menu");
	createStage(lbl);
	
	}
	public void about() {
		Label lbl= new Label("This game has been done as an"
				+ " educaitional exercise from Kalina Nikolova and Ralf \n" + 
				" 2nd semester in FHNW Basel 2019.\n" + 
				"The root code was provided by prof. Bradley Richards");
		createStage(lbl);
	}
	
	public void createStage(Label lbl) {
		HBox hBox = new HBox();
		hBox.getChildren().add(lbl);
		hBox.setId("about");
		Stage stage = new Stage();
		stage.setScene(new Scene(hBox, 400, 400));
		stage.show();
	}
}
