import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class BlackjackGameLogicTest {

    @Test
    public void testWhoWon() {
        BlackjackGameLogic logic = new BlackjackGameLogic();

        ArrayList<Card> player21 = new ArrayList<>();
        ArrayList<Card> dealer21 = new ArrayList<>();
        ArrayList<Card> player23 = new ArrayList<>();
        ArrayList<Card> dealer23 = new ArrayList<>();
        ArrayList<Card> player18 = new ArrayList<>();
        ArrayList<Card> dealer18 = new ArrayList<>();
        ArrayList<Card> player17 = new ArrayList<>();
        ArrayList<Card> dealer17 = new ArrayList<>();

        player21.add(new Card("C", 10));
        player21.add(new Card("C", 1));

        dealer21.add(new Card("C", 1));
        dealer21.add(new Card("C", 10));

        player18.add(new Card("C", 10));
        player18.add(new Card("C", 8));

        dealer18.add(new Card("C", 8));
        dealer18.add(new Card("C", 10));

        player17.add(new Card("C", 10));
        player17.add(new Card("C", 7));

        dealer17.add(new Card("C", 7));
        dealer17.add(new Card("C", 10));

        player23.add(new Card("C", 10));
        player23.add(new Card("C", 6));
        player23.add(new Card("C", 7));

        dealer23.add(new Card("C", 10));
        dealer23.add(new Card("C", 4));
        dealer23.add(new Card("C", 9));

        assertEquals("dealer", logic.whoWon(player23,dealer21));
        assertEquals("blackjack", logic.whoWon(player21,dealer18));
        assertEquals("push", logic.whoWon(player21,dealer21));
        assertEquals("dealer", logic.whoWon(player17,dealer21));
        assertEquals("player", logic.whoWon(player18,dealer23));
        assertEquals("player", logic.whoWon(player18,dealer17));
        assertEquals("push", logic.whoWon(player18,dealer18));
        assertEquals("dealer", logic.whoWon(player17,dealer18));
    }

    @Test
    public void testHandTotal() {
        BlackjackGameLogic logic = new BlackjackGameLogic();
        ArrayList<Card> hand1 = new ArrayList<>();
        ArrayList<Card> hand2 = new ArrayList<>();
        ArrayList<Card> hand3 = new ArrayList<>();
        ArrayList<Card> hand4 = new ArrayList<>();

        hand1.add(new Card("C",6));
        hand1.add(new Card("C",9));

        hand2.add(new Card("C",12));
        hand2.add(new Card("C",5));

        hand3.add(new Card("C",1));
        hand3.add(new Card("C",4));

        hand4.add(new Card("C",1));
        hand4.add(new Card("C",13));


        assertEquals(15,logic.handTotal(hand1));
        assertEquals(15,logic.handTotal(hand2));
        assertEquals(15,logic.handTotal(hand3));
        assertEquals(21,logic.handTotal(hand4));
    }

    @Test
    public void testEvaluateBankerDraw() {
        BlackjackGameLogic logic = new BlackjackGameLogic();

        ArrayList<Card> dealer14 = new ArrayList<>();
        ArrayList<Card> dealer18 = new ArrayList<>();

        dealer14.add(new Card("C", 10));
        dealer14.add(new Card("C", 4));

        dealer18.add(new Card("C", 9));
        dealer18.add(new Card("C", 9));

        assertTrue(logic.evaluateBankerDraw(dealer14));
        assertFalse(logic.evaluateBankerDraw(dealer18));
    }

}
