public class BlackJackApp {
    public static void main(String[] args) {
        BlackJackGame blackJackGame = new BlackJackGame();
        blackJackGame.dealInitialCards();
        blackJackGame.printHands();
        while(blackJackGame.calculateHandValue(blackJackGame.getPlayerHand()) < 18){
            blackJackGame.playerHit();
        }
        blackJackGame.printHands();
        blackJackGame.dealerPlay();
        System.out.println("Dealer's final hand:");
        blackJackGame.printHand(blackJackGame.getDealerHand());
        System.out.println("Player's final hand:");
        blackJackGame.printHand(blackJackGame.getPlayerHand());
        System.out.println(blackJackGame.determineWinner());
    }
}
