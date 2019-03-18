package poker.version_graphics.model;

import java.util.ArrayList;

import poker.version_graphics.PokerGame;

public class PokerGameModel {
	private final ArrayList<Player> players = new ArrayList<>();
	private DeckOfCards deck;
	private int playersCount;
	private int maxPLayers;
	

	public PokerGameModel() {
		playersCount=2;
		maxPLayers=4;
		
		for (int i = 0; i < playersCount; i++) {
			players.add(new Player("Player " + (i+1)));
			}
		

		deck = new DeckOfCards();
	}
	
	public Player getPlayer(int i) {
		return players.get(i);
	}

	public DeckOfCards getDeck() {
		return deck;
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}
	public int getPlayersCount() {
		return playersCount;
	}
	
	public boolean addPlayer() {
		players.add(new Player("Player "+ ++playersCount ));
		return true;
		
	}
	public Player win() {
		
		Player winner= players.get(0);
		for(int i=1; i<players.size(); i++) {
			if(players.get(i).compareTo(winner)>0) {
				winner= players.get(i);
			}
		}
		return winner;
	}
	
	
}
