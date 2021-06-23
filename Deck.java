// GRUPPE 21
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Deck
 */
public class Deck {

    public Deck () {
        generateDeck();
    }

    private List<Card> cards = new ArrayList<Card>();


    public void generateDeck() {
        for (Suits s : Suits.values()) {
            for (int r = 0; r < 13; r++) {
                Card c = new Card(s, r);
                cards.add(c);
            }
        }
        shuffle();
    }

    public void shuffle() {
        Collections.shuffle(this.cards);
    }


    public Card showTopCard () {
        return cards.iterator().next();
    }

    public Card drawCard() {
        Card draw = cards.get(0);
        cards.remove(draw);
        return draw;
    }

    public void skipCard() {
        //TODO
    }
}
