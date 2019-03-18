package poker.version_graphics.model;

import java.util.ArrayList;
import java.util.Arrays;

import poker.version_graphics.model.Card.Rank;
import poker.version_graphics.model.Card.Suit;

public enum HandType {
	HighCard, OnePair, TwoPair, ThreeOfAKind, Straight, Flush, FullHouse, FourOfAKind, StraightFlush,RoyalFlush;

	/**
	 * Determine the value of this hand. Note that this does not account for any
	 * tie-breaking
	 */
	public static HandType evaluateHand(ArrayList<Card> cards) {
		HandType currentEval = HighCard;

		if (isOnePair(cards))
			currentEval = OnePair;
		if (isTwoPair(cards))
			currentEval = TwoPair;
		if (isThreeOfAKind(cards))
			currentEval = ThreeOfAKind;
		if (isStraight(cards))
			currentEval = Straight;
		if (isFlush(cards))
			currentEval = Flush;
		if (isFullHouse(cards))
			currentEval = FullHouse;
		if (isFourOfAKind(cards))
			currentEval = FourOfAKind;
		if (isStraightFlush(cards))
			currentEval = StraightFlush;
		 if (isRoyalFlush(cards)) currentEval=RoyalFlush;
		return currentEval;
	}

	public static boolean isOnePair(ArrayList<Card> cards) {

		return findSameOfAKind(cards, 2);
	}

	public static boolean isTwoPair(ArrayList<Card> cards) {
		// Clone the cards, because we will be altering the list
		ArrayList<Card> clonedCards = (ArrayList<Card>) cards.clone();

		// Find the first pair; if found, remove the cards from the list
		boolean firstPairFound = false;
		for (int i = 0; i < clonedCards.size() - 1 && !firstPairFound; i++) {
			for (int j = i + 1; j < clonedCards.size() && !firstPairFound; j++) {
				if (clonedCards.get(i).getRank() == clonedCards.get(j).getRank()) {
					firstPairFound = true;
					clonedCards.remove(j); // Remove the later card
					clonedCards.remove(i); // Before the earlier one
				}
			}
		}
		// If a first pair was found, see if there is a second pair
		return firstPairFound && isOnePair(clonedCards);
	}

	public static boolean isThreeOfAKind(ArrayList<Card> cards) {
		
		 return findSameOfAKind(cards, 3);

	}
    public static boolean isStraight(ArrayList<Card> cards) {  
    	
        boolean isStraight = false;	
        if(!HandType.checkFlush(cards) && HandType.checkStraight(cards)) isStraight=true;	
       
        return isStraight;
           
       } 
       
       public static boolean isFlush(ArrayList<Card> cards) {  
       	
     	 boolean isFlush=false;
    
       	
        if(HandType.checkFlush(cards) && !HandType.checkStraight(cards)) isFlush=true;
        
        
        return isFlush;
            
        }
       
       public static boolean isFullHouse(ArrayList<Card> cards) {
       	
      //suit doesn't matter only rank, 3 of the same rank and 2 of the same rank
       	
           int [] valueCards = HandType.getValueList(cards);        
           
           for(int i=0;i<cards.size();i++) {
           	valueCards[i]=cards.get(i).getOrdinal();
           }    
           Arrays.sort(valueCards);
           
           boolean isFullHouse = false;

           	if(valueCards[0]==valueCards[1]) {
           		//check if third card is the same or the next 3 are the same
           		if(valueCards[1]==valueCards[2]) {
           			if(valueCards[3]==valueCards[4]) isFullHouse=true;
           		}
           		else if(valueCards[2]==valueCards[3]&&valueCards[3]==valueCards[4])isFullHouse=true;
           	}
           
           return isFullHouse;       
       }
       
   	public static boolean isFourOfAKind(ArrayList<Card> cards) {
   		// TODO - Done
   		return findSameOfAKind(cards, 4);
   	}
   	
       public static boolean isStraightFlush(ArrayList<Card> cards) {
       boolean isStraightFlush=false; 
      
    		if(HandType.checkFlush(cards) && HandType.checkStraight(cards)) isStraightFlush=true;
    
       	return isStraightFlush;        
     }    
       
       public static boolean isRoyalFlush(ArrayList<Card> cards) {
       
       	boolean isRoyalFlush=false;
       	 int [] valueCards = HandType.getValueList(cards);        
       	 final int royalFlush=60;
       	 int rankValue=0;
       	 
       	  for(int i=0;i<valueCards.length;i++) {	//getCard Value
       		    rankValue+=valueCards[i];
       		}
    
       	 if(HandType.checkFlush(cards) && rankValue==royalFlush) isRoyalFlush=true;

       	    	return isRoyalFlush;  
       	  }    
       
       private static boolean checkFlush(ArrayList<Card> cards) {
       	
         	 boolean isFlush=false;
          	 boolean allSuit = true;
        	 
               for (int i=0; i<cards.size()-1; i++) { 
               	  for (int j = i+1; j < cards.size(); j++ ) {
                 
                  	 if(cards.get(i).getSuit() == cards.get(j).getSuit()&& allSuit) {
                  	 isFlush=true;
              
                  	 }
                       else {
                  	 allSuit=false;
                  	 isFlush=false;
                       }
                   }
               } 	
       	
       	return isFlush;
       }
       
       private static boolean checkStraight(ArrayList <Card> cards) {
       	
       	
           int [] valueCards = HandType.getValueList(cards);     
          
           boolean isStraight = false;
           
           for(int i=0;i<valueCards.length-1;i++) {
           	if(valueCards[i]+1==valueCards[i+1]) {
           		isStraight=true;
           	}
           	else { isStraight=false;
           			break;
           		}    	
           	}
           return isStraight;
       }
       
       private static int [] getValueList(ArrayList <Card> cards) {
       	int [] valueCards = new int[cards.size()];
           for(int i=0;i<cards.size();i++) {
           	valueCards[i]=cards.get(i).getOrdinal();
           }    
           Arrays.sort(valueCards);
           /*
           for(int i=0;i<valueCards.length;i++) {
           	System.out.println("Value: " + valueCards[i]);
               }     */
       	
       	return valueCards;
       }

	
	public static boolean findSameOfAKind(ArrayList<Card> cards, int num) {
		int count = 1;
		boolean found = false;
		for (int i = 0; i < cards.size() - 1 && !found; i++) {
			count = 1;
			for (int j = i + 1; j < cards.size() && !found; j++) {
				if (cards.get(i).getRank() == cards.get(j).getRank())
					count++;
				//System.out.println(count + " x " + cards.get(i).getRank() + "(" + cards.get(j).getRank() + ")");
			}
			if (count >= num) {
				found = true;
			}
		}
		return found;

	}
	}

 // Kalina version

//public static boolean isStraight(ArrayList<Card> cards) {
//	
//	boolean isStraight=true;
//	int end= cards.size();
//	cards.sort(null);//2,3,4,5,A; 0,1,2,3,12
//	if(cards.get(0).getRank().ordinal()==0 && cards.get(end-1).getRank().ordinal()==12) {
//		end-=1;
//	}
//	for(int i=1; i<end; i++) {
//	   if(cards.get(i).getRank().ordinal()!= cards.get(i-1).getRank().ordinal()+1) {
//		isStraight=false;
//	   }
//	}
//	return isStraight;
//}
//
//public static boolean isFlush(ArrayList<Card> cards) {
//	boolean isFlush= true;
//	Suit suit= cards.get(0).getSuit();
//	for(int i=1; i<cards.size();i++) {
//		if(cards.get(i).getSuit()!= suit) {
//			isFlush=false;
//		}
//	}
//	return isFlush;
//}
//  private static boolean checkFlush(ArrayList<Card> cards) {
//    	
//      	 boolean isFlush=false;
//       	 boolean allSuit = true;
//     	 
//            for (int i=0; i<cards.size()-1; i++) { 
//            	  for (int j = i+1; j < cards.size(); j++ ) {
//              
//               	 if(cards.get(i).getSuit() == cards.get(j).getSuit()&& allSuit) {
//               	 isFlush=true;
//           
//               	 }
//                    else {
//               	 allSuit=false;
//               	 isFlush=false;
//                    }
//                }
//            } 	
//    	
//    	return isFlush;
//    }
//
//public static boolean isFullHouse(ArrayList<Card> cards) {
//	// TODO
//	Rank rank=null;
//	int count = 1;
//	boolean found = false;
//	for (int i = 0; i < cards.size() - 2 && !found; i++) {
//		count = 1;
//		for (int j = i + 1; j < cards.size() && !found; j++) {
//			if (cards.get(i).getRank() == cards.get(j).getRank())
//				count++;
//			//System.out.println(count + " x " + cards.get(i).getRank() + "(" + cards.get(j).getRank() + ")");
//		}
//		if (count ==3) {
//			rank= cards.get(i).getRank();
//			found = true;
//		}
//	}
//	boolean isFullHouse = false;
//	if(found) {
//		
//		for (int i = 0; i < cards.size() - 1 && !isFullHouse; i++) {
//			count = 1;
//			for (int j = i + 1; j < cards.size() && !isFullHouse; j++) {
//				if (cards.get(i).getRank() == cards.get(j).getRank())
//					count++;
//				//System.out.println(count + " x " + cards.get(i).getRank() + "(" + cards.get(j).getRank() + ")");
//			}
//			if (count ==2) {
//				if(cards.get(i).getRank() != rank) {
//					isFullHouse = true;
//				}
//			}
//		}
//	}
//
//	return isFullHouse;
//}

//public static boolean isStraightFlush(ArrayList<Card> cards) {
//	// DONE
//	
//	return isFlush(cards) && isStraight(cards);
//	
//}