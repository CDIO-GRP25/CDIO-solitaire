import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Deck
 */
public class Deck {

    public Deck () {
        drawPileCounter = 0;
        generateDeck();
    }

    private List<Card> cards = new ArrayList<Card>();
    private int drawPileCounter;

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


    public Card getTopCard () {
        //used for turning cards over in drawpile
        Card flippedCard = cards.get(drawPileCounter);
        //drawPileCounter++;
        return flippedCard;

        //return cards.iterator().next();
    }

    public Card drawCard() {
        //drawPileCounter is 0 at initiation so
        Card draw = cards.get(drawPileCounter);
        cards.remove(draw);
        //decrement counter to point to last seen card
        drawPileCounter--;
        if(drawPileCounter < 0){
            drawPileCounter = 0;
        }
        return draw;
    }

    public void skipCard() {
        //TODO
        drawPileCounter++;
        if(drawPileCounter > cards.size()-1){
            //if deck has been iterated through
            drawPileCounter = 0;
        }
    }

    public int getDrawPileSize(){
        return cards.size()-drawPileCounter;
    }

    public int getDeckSize(){
        return cards.size();
    }

    public int getDrawPileCounter(){
        return drawPileCounter;
    }
}
