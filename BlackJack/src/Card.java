public class Card {
    enum Suit{
        HEARTS, DIAMONDS, CLUBS, SPADES;
    }

    enum Rank{
        TWO(2), THREE(3) , FOUR(4), FIVE(5), SIX(6), SEVEN(7),
        EIGHT(8), NINE(9), TEN(10), JACK(10), QUEEN(10), KING(10), ACE(11);

        Rank(int value) {
            this.value = value;
        }

        private int value;

        public int getValue(){
            return this.value;
        }
    }

    private Suit suit;
    private Rank rank;
    public Card(Suit suit, Rank rank){
        this.suit = suit;
        this.rank = rank;
    }

    public Rank getRank(){
        return this.rank;
    }

    public int getValue(){
        return this.rank.getValue();
    }

    public Suit getSuit(){
        return this.suit;
    }

    @Override
    public String toString(){
        return this.rank.toString() + " of " + this.suit.toString();
    }
}
