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
public String win() {
	
		Player winner= players.get(0);
		int winnerint=0;
		int player;
		ArrayList<Integer> count = new ArrayList<>();
		String winnerstr=players.get(0).getPlayerName();
	
		//test
		for(int i=1; i<players.size(); i++) {
			if(players.get(i).compareTo(winner)>0) {
				winner= players.get(i);
				winnerint=i;	//sets the position of the currently highest hand	
				winnerstr=players.get(i).getPlayerName();
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
					winnerstr= players.get(winnerint).getPlayerName();
				}
				else if(player==2) {
					winner=players.get(i);
					winnerint=i;
					winnerstr= players.get(i).getPlayerName();
				} 
				else if(player==-1) {//in case of a tie
					boolean containsI=false;
					boolean containsW=false;
					
					if(count.contains(winnerint)) containsW=true;
					if(count.contains(i)) containsI=true;
					
					if(!containsW) count.add(winnerint);
					if(!containsI) count.add(i);					
					
					String string="Tie -";
					for(int k=0;k<count.size();k++) {
						System.out.println(string += " "+ players.get(count.get(k)).getPlayerName());
						}
				
					winnerstr=string;					
					}
				   }	
			}		
		
		return winnerstr; 		
	}	
}
