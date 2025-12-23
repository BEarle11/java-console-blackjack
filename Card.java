
public class Card {
	char suit;
	int val;
public Card(char s,int v) {
	this.val=v;
	this.suit=s;
}
public int getVal() {
	return val;
}
public char getSuit() {
	return suit;
}
public boolean isAce() {
	if (val==1) return true;
	else return false;
}
}