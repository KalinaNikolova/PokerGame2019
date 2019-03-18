package poker.version_graphics.view;

import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import poker.version_graphics.model.Card;
import poker.version_graphics.model.HandType;
import poker.version_graphics.model.Player;

public class PlayerPane extends VBox {
	private Label lblName = new Label();
	private HBox hboxCards = new HBox();
	private Label lblEvaluation = new Label("--");

	// Link to player object
	private Player player;

	public PlayerPane() {
		super(); // Always call super-constructor first !!
		this.getStyleClass().add("player"); // CSS style class

		// Add child nodes
		this.getChildren().addAll(lblName, hboxCards, lblEvaluation);

		// Center the cards
		VBox.setMargin(hboxCards, new Insets(0));

		// Add CardLabels for the cards
		for (int i = 0; i < 5; i++) {
			Label lblCard = new CardLabel();
			hboxCards.getChildren().add(lblCard);
		}
	}

	public void setPlayer(Player player) {
		this.player = player;
		updatePlayerDisplay(); // Immediately display the player information
	}

	public void updatePlayerDisplay() {
		double timeX=0.5;
		double timeY=0.3;
		lblName.setText(player.getPlayerName());
		for (int i = 0; i < Player.HAND_SIZE; i++) {
			Card card = null;
			if (player.getCards().size() > i)
				
				card = player.getCards().get(i);
			CardLabel cl = (CardLabel) hboxCards.getChildren().get(i);
			cl.setCard(card);
			
			if(cl.getGraphic() != null) {
				TranslateTransition transition= new TranslateTransition();
			    timeX+=0.5;
				transition.setFromX(500);
				transition.setToX(0);
				transition.setDuration(Duration.seconds(timeX));
				transition.setNode(cl);
				transition.play();
			}
			
				
			
			HandType evaluation = player.evaluateHand();
			if (evaluation != null)
				lblEvaluation.setText(evaluation.toString());
			else
				lblEvaluation.setText("---");
			
		}
	}

}
