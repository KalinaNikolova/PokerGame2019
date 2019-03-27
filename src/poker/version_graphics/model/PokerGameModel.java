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
public  Player win() { //determine winner
				
		Player winner= players.get(0);
		int winnerint=0;
		int player;
		//test
		for(int i=1; i<players.size(); i++) {
			if(players.get(i).compareTo(winner)>0) {
				winner= players.get(i);
				winnerint=i;	//sets the position of the currently highest hand
			}
		}
		
		for(int i =0;i<players.size();i++) {
			if(players.get(i).compareTo(winner)==0 && i!=winnerint) {
				HandType.evaluateHand(players.get(i).getCards());  //gets hand type
				
				TieBreak tie = new TieBreak(players.get(i).getCards(),players.get(winnerint).getCards(), 
						HandType.evaluateHand(players.get(i).getCards()).toString());

				player=tie.getWinnerTie();
				if (player==1) {
					winner=players.get(winnerint);
				}
				else if(player==2) {
					winner=players.get(i);
					winnerint=i;
				}
				else winner=players.get(player);		//in case of a tie
			}
		}
		return winner;		
	}	
}
