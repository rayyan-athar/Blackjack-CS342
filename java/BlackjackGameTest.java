import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
public class BlackjackGameTest {
    @Test
    public void testSetandGetBet() {
        BlackjackGame game = new BlackjackGame();
        game.setCurrentBet(10.00);
        assertEquals(10.00,game.getCurrentBet());
    }

    @Test
    public void testSetandGetWinnings() {
        BlackjackGame game = new BlackjackGame();
        game.setTotalWinnings(1000.00);
        assertEquals(1000.00,game.getTotalWinnings());
    }

    @Test
    public void testEvaluateWinnings() {
        BlackjackGame game = new BlackjackGame();
        game.setTotalWinnings(1000);
        game.setCurrentBet(100);

        game.playerHand.add(new Card("C",10));
        game.playerHand.add(new Card("C",9));
        game.bankerHand.add(new Card("C",8));
        game.bankerHand.add(new Card("C",7));

        assertEquals(1100,game.evaluateWinnings());

        game.playerHand.clear();
        game.bankerHand.clear();

        game.setTotalWinnings(1000);
        game.setCurrentBet(100);

        game.playerHand.add(new Card("C",6));
        game.playerHand.add(new Card("C",9));
        game.bankerHand.add(new Card("C",8));
        game.bankerHand.add(new Card("C",7));

        assertEquals(1000,game.evaluateWinnings());

        game.playerHand.clear();
        game.bankerHand.clear();

        game.setTotalWinnings(1000);
        game.setCurrentBet(100);

        game.playerHand.add(new Card("C",6));
        game.playerHand.add(new Card("C",9));
        game.bankerHand.add(new Card("C",8));
        game.bankerHand.add(new Card("C",10));

        assertEquals(900,game.evaluateWinnings());

        game.playerHand.clear();
        game.bankerHand.clear();

        game.setTotalWinnings(1000);
        game.setCurrentBet(100);

        game.playerHand.add(new Card("C",1));
        game.playerHand.add(new Card("C",12));
        game.bankerHand.add(new Card("C",8));
        game.bankerHand.add(new Card("C",7));

        assertEquals(1150,game.evaluateWinnings());


    }
}
