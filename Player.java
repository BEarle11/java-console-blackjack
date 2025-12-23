
import java.util.ArrayList;
public class Player {
	ArrayList<Card> cards = new ArrayList<Card>();
	boolean stand=false;
	private boolean dealer;
	int numCards=0;
	int wins=0;
	
	public Player(boolean d) {
		dealer=d;
	}
	
	
	public void reset() {
		cards.clear();
		numCards=0;
		stand=false;
		
	}
	public int hit(Card c) {
		cards.add(c);
		numCards++;
		return calcHandTotal();
	}
	
	
	public boolean getStand() {
		return stand;
	}
	
	
	public void setStand(boolean s) {
		stand=s;
	}
	public void addWins() {
		wins++;
	}
public int getWins() {
		return wins;
	}
	
	
	public int calcHandTotal() {
		int total=0;
		int aces=0;
		for (int i =0;i<cards.size();i++) {
			int value=cards.get(i).getVal();
			if (value>10) value=10;
			total+=value;
			if (cards.get(i).isAce()) aces++;
		}
		// see if you can upgrade aces to 10 
		while ( aces>0 && total<=11) {
			total+=10;
			aces--;
		}
		if(total>=21) stand=true;
		return total;
	}
	
	// hole card is for when dealer displays last card 
	public void displayCards2(boolean holeCard) {
		// ♥ ♦ ♣ ♠
		if (holeCard) dealer=false;
		for (int i=0;i<numCards;i++) {
			char suitTemp=cards.get(i).getSuit();
			String valTemp;
			switch (cards.get(i).getVal()) {
			    case 1:
			        valTemp = "A";
			        break;
			    case 10:
			        valTemp = "T";
			        break;
			    case 11:
			        valTemp = "J";
			        break;
			    case 12:
			        valTemp = "Q";
			        break;
			    case 13:
			        valTemp = "K";
			        break;
			    default:
			        valTemp = String.valueOf(cards.get(i).getVal());
			        break;
			}
			if (dealer&&i==numCards-1) {
				System.out.println("┌─────────┐\n"
								 + "│▒▒▒▒▒▒▒▒▒│\n"
								 + "│▒▒▒▒▒▒▒▒▒│\n"
								 + "│▒▒▒▒▒▒▒▒▒│\n"
								 + "│▒▒▒▒▒▒▒▒▒│\n"
								 + "│▒▒▒▒▒▒▒▒▒│\n"
								 + "└─────────┘"); 
			}
			else{ System.out.print(
				    "┌─────────┐\n" +
				    "│" + valTemp + "        │\n" +
				    "│" + suitTemp + "        │\n" +
				    "│   B J   │\n" +
				    "│        " + suitTemp + "│\n" +
				    "│        " + valTemp + "│\n" +
				    "└─────────┘\n");
			}
		}
		if(!dealer) {
			System.out.println("This hand is worth: "+calcHandTotal());
			if (calcHandTotal()==21)System.out.println("BlackJack! ");
			if (calcHandTotal()>21)System.out.println("Bust!");
			
		}
		
		
	}
	public void displayCards(boolean holeCard) {
		boolean hideCard = dealer && !holeCard;
		for(int j=1;j<8;j++) {
			for (int i=0;i<numCards;i++) {
				char suitTemp=cards.get(i).getSuit();
				String valTemp;
				switch (cards.get(i).getVal()) {
                case 1:  valTemp = "A"; break;
                case 10: valTemp = "T"; break;
                case 11: valTemp = "J"; break;
                case 12: valTemp = "Q"; break;
                case 13: valTemp = "K"; break;
				default: valTemp = String.valueOf(cards.get(i).getVal()); break;
				}
				if (hideCard&&i==numCards-1) {
					switch (j) {
					case 1: System.out.print("┌─────────┐"); break;
					case 2: System.out.print("│▒▒▒▒▒▒▒▒▒│"); break;
					case 3: System.out.print("│▒▒▒▒▒▒▒▒▒│"); break;
					case 4: System.out.print("│▒▒▒▒▒▒▒▒▒│"); break;
					case 5: System.out.print("│▒▒▒▒▒▒▒▒▒│"); break;
					case 6: System.out.print("│▒▒▒▒▒▒▒▒▒│"); break;
					case 7: System.out.print("└─────────┘"); break;
							}
					}
					else {
						switch (j) {
						case 1: System.out.print( "┌─────────┐"); break;
						case 2: System.out.print("│"+valTemp + "        │"); break;
						case 3: System.out.print("│" + suitTemp + "        │"); break;
						case 4: System.out.print("│   B J   │"); break;
						case 5:System.out.print("│        " + suitTemp + "│"); break;
						case 6: System.out.print("│        " + valTemp + "│"); break;
						case 7: System.out.print("└─────────┘"); break;
							
						}
						
					}
				
		}
			System.out.println();
		}
		if(!dealer || holeCard) {
			System.out.println("This hand is worth: "+calcHandTotal());
			System.out.println();
			if (calcHandTotal()==21)System.out.println("BlackJack! ");
			if (calcHandTotal()>21)System.out.println("Bust!");
			
		}
	}
		
}

