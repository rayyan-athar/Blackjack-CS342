public class Card {
    // Main card class has a suit and value with a constructor and two getters
    private final String suit;
    private final int value;
    public Card(String theSuit, int theValue) {
        this.suit = theSuit;
        this.value = theValue;
    }
    public int getValue() {
        return value;
    }

    public String getSuit() {
        return suit;
    }
}
