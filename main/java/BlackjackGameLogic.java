import java.util.ArrayList;

public class BlackjackGameLogic {

    public String whoWon(ArrayList <Card> playerHand1, ArrayList<Card> dealerHand) {
        // Function returns the winner based on calculating whose hand was the winner by evaluating a blackjack, bush, or whoever is closer to 21
        String winner = null;

       if(handTotal(playerHand1) > 21) { // bust
           winner = "dealer";
       }
       else if(handTotal(playerHand1) == 21) { // blackjack
           if(handTotal(dealerHand) != 21) { // no dealer blackjack
               winner = "blackjack";
           }
           else { // dealer blackjack
               winner = "push";
           }
       }
       else if (handTotal(playerHand1) < 21) { // less
           if(handTotal(dealerHand) == 21) { // dealer blackjack
               winner = "dealer";
           }
           else if (handTotal(dealerHand) > 21){ // dealer bust
               winner = "player";
           }
           else {
               if(handTotal(dealerHand) > handTotal(playerHand1)) { // dealer closer to 21
                   winner = "dealer";
               }
               else if(handTotal(dealerHand) < handTotal(playerHand1)) { // player closer to 21
                    winner = "player";
               }
               else if(handTotal(dealerHand) == handTotal(playerHand1)) { // both close to 21
                    winner = "push";
               }
           }
       }

       return winner;
    }

    public int handTotal(ArrayList<Card> hand) {
        int total = 0;

        // For loop calculates the total of the hand by summing each card values, face value cards are capped at 10
        for(Card C : hand) {
            if(C.getValue() >= 10) {
                total += 10;
            } else {
                total += C.getValue();
            }
        }

        // If there is an ace its original value is 11 if total is still under 21, however if including ace value exceeds 21, ace changes value to 1
        for(Card C : hand) {
            if(C.getValue() == 1) {
                if(total + 10 <= 21) {
                    total += 10;
                }
            }
        }

        return total;
    }

    public boolean evaluateBankerDraw(ArrayList<Card> hand) {
        // returns true if banker's hand is less than 16 meaning banker has to deal a card
        int total = handTotal(hand);
        return total <= 16;
    }

}
