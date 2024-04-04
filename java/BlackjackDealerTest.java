import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
public class BlackjackDealerTest {

    @Test
    public void testGenerateDeck() {
        BlackjackDealer dealer = new BlackjackDealer();
        dealer.generateDeck();
        assertEquals(52, dealer.deckSize());
    }

    @Test
    public void testDealHand() {
        BlackjackDealer dealer = new BlackjackDealer();
        dealer.generateDeck();
        ArrayList<Card> hand = dealer.dealHand();
        assertEquals(2, dealer.index);
        assertEquals(2, hand.size());
        assertEquals(50, dealer.deckSize());
    }

    @Test
    public void testDrawOne() {
        BlackjackDealer dealer = new BlackjackDealer();
        dealer.generateDeck();
        Card card = dealer.drawOne();
        assertEquals(1, dealer.index);
        assertEquals(51, dealer.deckSize());
    }

}
