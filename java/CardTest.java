import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class CardTest {

	@Test
	public void testGetValue() {
		Card card = new Card("H", 10);
		assertEquals(10, card.getValue());
	}

	@Test
	public void testGetSuit() {
		Card card = new Card("C", 2);
		assertEquals("C", card.getSuit());
	}
}
