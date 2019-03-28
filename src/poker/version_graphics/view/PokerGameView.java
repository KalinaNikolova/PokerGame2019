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
	private BorderPane root;
	private Stage stage;


	public PokerGameView(Stage stage, PokerGameModel model) {
		this.model = model;
		this.stage= stage;
	
		
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
		
		// Create new stage
		createScene(randomCss());
	}
	public void createScene(String css) {
		
		Scene scene = new Scene(this.root, 1600,650);
		scene.getStylesheets().add(getClass().getResource(css).toExternalForm());
		stage.setTitle("Poker Miniproject");
		stage.setScene(scene);
		stage.show();
	}
	
	public void createMenuStage(String css, Label lbl, HBox hBox) {
		Scene scene = new Scene(hBox);
		Stage menuStage= new Stage();
		hBox.getChildren().add(lbl);
		scene.getStylesheets().add(getClass().getResource(css).toExternalForm());
		menuStage.setTitle("Poker Rules");
		menuStage.setScene(scene);
		menuStage.show();
		
		
	}

	public static String randomCss() {
		ArrayList<String> myCss= new ArrayList<>();
		myCss.add("red.css");
		myCss.add("blue.css");
		myCss.add("poker.css");
		Random gen= new Random();
		int myGen= gen.nextInt(3);
		String css= myCss.get(myGen);
		
		return css;
	}
		public void addPlayer(PokerGameModel model, int i) {
		PlayerPane pp = new PlayerPane();

		//HBox.setHgrow(pp, Priority.ALWAYS);// K: center(resize area) the cards //no need

		pp.setPlayer(model.getPlayer(i)); // link to player object in the logic
		players.getChildren().add(pp);
	}


	public ControlArea getControls() {
	return controls;
	}
	public void setControls(ControlArea controls) {
	this.controls = controls;
	}
	public PokerGameModel getModel() {
	return model;
	}
	public void setModel(PokerGameModel model) {
	this.model = model;
	}
	public TilePane getPlayers() {
	return players;
	}
	public void setPlayers(TilePane players) {
	this.players = players;
	}
	public BorderPane getRoot() {
	return root;
	}
	public void setRoot(BorderPane root) {
	this.root = root;
	}
	public void setTopMenu(TopMenu topMenu) {
	this.topMenu = topMenu;
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

	public Button getRemovePlayerButton() {
		return controls.btnRemovePlayer;
	}
	public Button getAddPlayerButton() {
		return controls.btnAddPlayer;
	}

	public Label getWinnerLabel() {
		return controls.lblWinner;
	}
}
