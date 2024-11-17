import java.util.ArrayList;
import java.util.List;

public class BlackJackGame {
    private final Deck deck;
    private final List<Card> playerHand;
    private final List<Card> dealerHand;

    public List<Card> getPlayerHand() {
        return playerHand;
    }

    public List<Card> getDealerHand() {
        return dealerHand;
    }

    public BlackJackGame() {
        deck = new Deck();
        playerHand = new ArrayList<>();
        dealerHand = new ArrayList<>();
    }

    public void dealInitialCards(){
        deck.shuffle();
        dealerHand.clear();
        playerHand.clear();
        dealerHand.add(deck.dealCard());
        playerHand.add(deck.dealCard());
        dealerHand.add(deck.dealCard());
        playerHand.add(deck.dealCard());
    }

    public int calculateHandValue(List<Card> hand){
        int value = hand.stream().mapToInt(card -> card.getValue()).sum();
        long aceCount = hand.stream().filter(card -> card.getRank() == Card.Rank.ACE).count();
        while(value > 21 && aceCount > 0){
            value -= 10;
            aceCount--;
        }
        return value;
    }

    public void printHands(){
        System.out.println("Player's Hand: " + playerHand);
        System.out.println("Dealer's Hand: " + dealerHand.get(0) + " [Hidden]");
    }

    public void printHand(List<Card> hand){
        System.out.println(hand);
    }

    public Card playerHit(){
        playerHand.add(deck.dealCard());
        System.out.println("Player drew " + playerHand.get(playerHand.size()-1));
        return playerHand.get(playerHand.size()-1);
    }

    public void dealerPlay(){
        while(calculateHandValue(dealerHand) < 17){
            dealerHand.add(deck.dealCard());
            System.out.println("Dealer drew " + dealerHand.get(dealerHand.size()-1));
        }
    }

    public String determineWinner(){
        int playerValue = calculateHandValue(playerHand);
        int dealerValue = calculateHandValue(dealerHand);
        if (playerValue > 21) return "Player Busted! Dealer Wins.";
        if (dealerValue > 21) return "Dealer Busted! Player Wins.";
        if (playerValue > dealerValue) return "Player Wins!";
        if (playerValue < dealerValue) return "Dealer Wins!";
        return "It's a Tie!";
    }
}
