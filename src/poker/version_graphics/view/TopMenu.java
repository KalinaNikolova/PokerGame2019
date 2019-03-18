package poker.version_graphics.view;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class TopMenu extends MenuBar {
	private MenuBar menuBar = new MenuBar();
	
	// Create Menu Bar
	// Menu File
	Menu file = new Menu("File");
	MenuItem newGame = new MenuItem("New Game");
	MenuItem image = new MenuItem("Set Image");
	MenuItem close = new MenuItem("Close");
	// Menu Help
	Menu help = new Menu("Help");
	MenuItem about = new MenuItem("About");
	Menu subtMenu = new Menu("Rules");
	MenuItem hands = new MenuItem("Hands");
	MenuItem genRuls = new MenuItem("General Rules");
	
	public TopMenu() {
		super();
		// add to the SubMenu menuItems
				subtMenu.getItems().addAll(hands, genRuls);

				// Add MenuItem to each Menu
				file.getItems().addAll(newGame, image, close);
				help.getItems().addAll(subtMenu, about);
				menuBar.getMenus().addAll(file, help);

	}

	public MenuBar getMenuBar() {
		return menuBar;
	}

}
