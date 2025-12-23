import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Stack;

public class ConsoleBlackJackMain {
	static ArrayList<Player> players = new ArrayList<Player>();
	static Stack<Card> deck = new Stack<Card>();
	static Player Dealer;
	static int numPlayers=0;

	
	
	public static void main(String[] args) {
		
		boolean active=true;
		initalize();
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome to BlackJack!");
		// account for players
		System.out.println("How many players will be dealt in?");
		numPlayers=sc.nextInt();
		
		for (int i=0;i<numPlayers;i++) {
			Player p= new Player(false);
			players.add(p);
		}
		//add dealer to end of player list 
		Dealer = new Player(true);
		players.add(Dealer);
		
		
		//game runs through a while loop
		while(active) {
			
			System.out.println("Dealer is dealing...  ");
		
			
			// hands are distributed and hit or stand takes place as well as the dealers hand 
			deal();
			
			// finds and displays results of round 
			findResults();
			
			//quit program 
			System.out.println("Next round is about to begin...");
			System.out.println("To quit, press 0. To keep playing, press anything else:   ");
			if (sc.next()=="0") active=false;
			
			//set up next round 
			for (int i=0;i<(numPlayers+1);i++) {
				players.get(i).reset();
			}
		}
		

	}
	
	public static void initalize() {
		// creating deck, looping through different suits
		for (int i=1;i<5;i++) {
			char suit=' ';
			switch (i){
			case 1:
				suit='♣';
				break;
			case 2:
				suit='♦';
				break;
			case 3:
				suit='♥';
				break;
			case 4:
				suit='♠';
				break;
			}
			// loop through different face values 
			for (int j=1;j<14;j++) {
				Card c= new Card(suit,j);
				deck.push(c);
				// add card to stack (deck)
			}
		}
		shuffle(deck);
	}
	// handles the dealing of a round 
	public static void deal() {
		Scanner sc = new Scanner(System.in);
		//deal 2 cards to begin round 
		for(int j=0;j<2;j++){
			for (int i=0;i<(numPlayers+1);i++) {
				players.get(i).hit(deck.pop());
			}
		}
		displayTable(false);
		// stand or hit segment
		for (int i=0;i<numPlayers;i++) {
			if (players.get(i).getStand()) continue;
			System.out.println("Will player "+(i+1)+" hit or stand? ");
			System.out.println("Enter h or s: ");
			//hit
			if (sc.nextLine().equals("h")) {
				players.get(i).hit(deck.pop());
				players.get(i).displayCards(false);
				i--;
				sc.nextLine();
				continue;
			}
			//stand
			else if (sc.nextLine().equals("s")) {
				players.get(i).setStand(true);
				System.out.println("Player "+(i+1)+"has: "+players.get(i).calcHandTotal());
				sc.nextLine();
				continue;
			}
		
		}
		//automate dealer decision with BJ rules
		System.out.println("Its the dealers turn!: ");
		Dealer.displayCards(true);
		while(Dealer.calcHandTotal()<17) {
			Dealer.hit(deck.pop());
			System.out.println("Dealer hits...");
			Dealer.displayCards(true);
			
		}
		
	}
	
	
	// display everyones hand 
	public static void displayTable(boolean hc) {
		for (int i=0;i<numPlayers;i++) {
			int j=i+1;
			System.out.println("Player "+j+"'s hand: ");
			players.get(i).displayCards(false);
		}
		// handle dealer 
		System.out.println();
		System.out.println();
		System.out.println("Dealers hand: ");
		Dealer.displayCards(hc);
	}
	//Ending sequence to one round of BJ
	public static void findResults() {
		System.out.println("/////////////////////////////////////////");
		System.out.println("This rounds over, Lets see what happened: ");
		System.out.println("/////////////////////////////////////////");
		displayTable(true);
		for (int i=0;i<numPlayers;i++) {
			//dealer beats
			if (Dealer.calcHandTotal()>22 || players.get(i).calcHandTotal()>Dealer.calcHandTotal() && players.get(i).calcHandTotal()<22) {
				players.get(i).addWins();
				//System.out.println("Player "+(i+1)+"("+players.get(i).getWins()+" win(s)) beat the dealer");
				
			}
			System.out.println("Player "+(i+1)+" has "+players.get(i).getWins()+" win(s)");
		}
	}
	
	
	public static void shuffle(Stack deck) {
		Collections.shuffle(deck);
	}

}
