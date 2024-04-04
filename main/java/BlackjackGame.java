import java.util.ArrayList;
import java.util.Objects;

public class BlackjackGame {
    ArrayList<Card> playerHand = new ArrayList<>();
    ArrayList<Card> bankerHand = new ArrayList<>();
    BlackjackDealer theDealer = new BlackjackDealer();
    BlackjackGameLogic gameLogic = new BlackjackGameLogic();
    private double currentBet;
    private double totalWinnings;
    public double evaluateWinnings() {
        // Based on string returned from who wins, total winnings are calcuated from the bet that was set

        if(Objects.equals(gameLogic.whoWon(playerHand, bankerHand), "player")) {
            totalWinnings += currentBet;
        }
        else if(Objects.equals(gameLogic.whoWon(playerHand, bankerHand), "dealer")) {
            totalWinnings -= currentBet;
        }
        else if(Objects.equals(gameLogic.whoWon(playerHand, bankerHand), "blackjack")) {
            totalWinnings  += (1.5 * currentBet);
        }
        return totalWinnings;
    }

    // Various setters and getters from winnings and bet
    public void setCurrentBet(double currentBet) { this.currentBet = currentBet; }

    public double getCurrentBet() {
        return currentBet;
    }

    public void setTotalWinnings(double totalWinnings) {
        this.totalWinnings = totalWinnings;
    }

    public double getTotalWinnings() {
        return totalWinnings;
    }


}
