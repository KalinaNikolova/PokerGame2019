package poker.version_graphics.model;

public class Card implements Comparable<Card> {
	public enum Suit {
		Clubs, Diamonds, Hearts, Spades;
		@Override
		public String toString() {
			String suit = "";
			switch (this) {
			case Clubs:
				suit = "clubs";
				break;
			case Diamonds:
				suit = "diamonds";
				break;
			case Hearts:
				suit = "hearts";
				break;
			case Spades:
				suit = "spades";
				break;
			}
			return suit;
		}
	};

	public enum Rank {
		Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten, Jack, Queen, King, Ace;
		@Override
		public String toString() {
			String str = "ace"; // Assume we have an ace, then cover all other cases
			// Get ordinal value, which ranges from 0 to 12
			int ordinal = this.ordinal();
			if (ordinal <= 8) {
				str = Integer.toString(ordinal + 2);
			} else if (ordinal == 9) { // Jack
				str = "jack";
			} else if (ordinal == 10) { // Queen
				str = "queen";
			} else if (ordinal == 11) { // King
				str = "king";
			}
			return str;
		}
	};
	private int ordinalint;
	private final Suit suit;
	private final Rank rank;

	public Card(Suit suit, Rank rank) {
		this.suit = suit;
		this.rank = rank;
	}

	public Suit getSuit() {
		return suit;
	}

	public Rank getRank() {
		return rank;
	}
	 public int getOrdinal() {
	    	
	    	ordinalint=rank.ordinal()+2;    	    
	    	
	    	return ordinalint;
	    	
	    }

	@Override
	public String toString() {
		return rank.toString() + suit.toString();
	}

	@Override
	public int compareTo(Card that) {
		// TODO Auto-generated method stub
		if (this.rank.ordinal()> that.rank.ordinal()) {
			return 1;
		}
		else if (this.rank.ordinal()< that.rank.ordinal()) {
			return -1;
		}
		return 0;
	}
}