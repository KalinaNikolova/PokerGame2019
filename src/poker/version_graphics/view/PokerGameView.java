package poker.version_graphics.view;


import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import poker.version_graphics.model.PokerGameModel;

public class PokerGameView {
	//private HBox players;
	private ControlArea controls;
//	private MenuBar menuBar = new MenuBar();
	private TopMenu topMenu;
	private PokerGameModel model;
	private TilePane players= new TilePane();
	BorderPane root;

	public PokerGameView(Stage stage, PokerGameModel model) {
		this.model = model;
	
		// Create all of the player panes we need, and put them into an HBox
		//players = new HBox();
		players= new TilePane();
		for (int i = 0; i < model.getPlayersCount(); i++) {
			addPlayer(model, i);

		}

		// Create the control area
		controls = new ControlArea();
		controls.linkDeck(model.getDeck()); // link DeckLabel to DeckOfCards in the logic
//
		//Menubar
		topMenu= new TopMenu();

		// Put players and controls into a BorderPane
		root = new BorderPane();
		
		root.setTop(topMenu.getMenuBar());
		root.setCenter(players);
		root.setBottom(controls);
		
		// Create the scene using our layout; then display it
		Scene scene = new Scene(root,1400,700);
		scene.getStylesheets().add(getClass().getResource("poker.css").toExternalForm());
		stage.setTitle("Poker Miniproject");
		stage.setScene(scene);
		stage.show();
	}
	public void addPlayer(PokerGameModel model, int i) {
		PlayerPane pp = new PlayerPane();

		//HBox.setHgrow(pp, Priority.ALWAYS);// K: center(resize area) the cards //no need

		pp.setPlayer(model.getPlayer(i)); // link to player object in the logic
		players.getChildren().add(pp);
	}
	public MenuItem getAboutItem() {
		return topMenu.about;
	}
	public MenuItem getGenRulesItem() {
		return topMenu.genRuls;
	}
	public MenuItem getHandsItem() {
		return topMenu.hands;
	}
	public MenuItem getNewGameItem() {
		return topMenu.newGame;
	}
	public MenuItem getChangeCoverImage() {
		return topMenu.image;
	}
	public MenuItem getCloseItem() {
		return topMenu.close;
	}
	
	public TopMenu getTopMenu() {
		return topMenu;
	}
	

	public PlayerPane getPlayerPane(int i) {
		return (PlayerPane) players.getChildren().get(i);
	}

	public Button getShuffleButton() {
		return controls.btnShuffle;
	}

	public Button getDealButton() {
		return controls.btnDeal;
	}

	public Button getWinnerButton() {
		return controls.btnWinner;
	}

	public Button getAddPlayerButton() {
		return controls.btnAddPlayer;
	}

	public Label getWinnerLabel() {
		return controls.lblWinner;
	}

	

}
