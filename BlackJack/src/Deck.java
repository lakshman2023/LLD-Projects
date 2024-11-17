import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Deck {
    private final ArrayList<Card> cards;
    public Deck() {
        cards = new ArrayList<>();
        for(Card.Suit suit : Card.Suit.values()) {
            for(Card.Rank rank : Card.Rank.values()) {
                cards.add(new Card(suit, rank));
            }
        }
    }

    public void shuffle(){
        Collections.shuffle(cards);
    }

    public Card dealCard(){
        return (cards.size() > 0) ? cards.remove(cards.size() - 1) : null;
    }
}
