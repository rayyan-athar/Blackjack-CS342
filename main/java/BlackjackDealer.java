import java.util.ArrayList;
import java.util.Random;

public class BlackjackDealer {
    int DeckSize;
    int index;
    ArrayList<Card> Deck = new ArrayList<>();

    public void generateDeck() {
        // For each suit, adds 13 values cards into the deck
        String[] suits = {"C", "D", "H", "S"};
        for (String suit : suits) {
            for (int i = 1; i <= 13; i++) {
                Deck.add(new Card(suit, i));
            }
        }
        DeckSize = 52;
    }

    public ArrayList<Card> dealHand() {
        // Two cards are dealt from the function, updating deck size and index
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(Deck.get(index));
        index++;
        DeckSize--;
        hand.add(Deck.get(index));
        index++;
        DeckSize--;
        return hand;
    }

    public Card drawOne() {
        // One card is returned
        index++;
        DeckSize--;
        return Deck.get(index);
    }

    public void shuffleDeck() {
        // Fisher-Yates shuffling algorithm is used below to shuffle the deck by setting a random seed based on the time
        Random rand = new Random(System.currentTimeMillis());

        for(int i = Deck.size() - 1; i > 0; i--) {
            int index = rand.nextInt(i+1);

            Card temp = Deck.get(i);
            Deck.set(i, Deck.get(index));
            Deck.set(index, temp);
        }

        index = 0;
        DeckSize = 52;
    }

    public int deckSize() {
        return DeckSize;
    }
}
