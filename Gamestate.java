import java.util.ArrayList;
import java.util.List;

/**
 * Gamestate
 */
public class Gamestate {

    List<GamePile> buildPiles = new ArrayList<GamePile>();
    List<GamePile> suitPiles = new ArrayList<GamePile>();
    Deck deck;

    public Gamestate() {
        setupGameState();
    }

    public void setupGameState() {
        this.deck = new Deck();
        for (int i = 0; i < 6; i++) {
            GamePile y = new Pile();
            buildPiles.add(y);
        }
        for (Suits s : Suits.values()) {
            GamePile x = new SuitPile(s);
            suitPiles.add(x);
        }
        dealCards();
    }


    public void dealCards() {
        for (GamePile gamePile : buildPiles) {
            for (int i = 0; i < buildPiles.indexOf(gamePile) + 1; i++) {
                gamePile.setupCard(deck.drawCard());
            }
        }
    }

    public void moveCardToPile(GamePile from, GamePile to) {
        // Remove top card of the pile
        Card card = from.removeTopCard();
        //Add card to top of pilr
        to.addCard(card);

    }

    public void print(){
        String remainingInPile ="| ";
        String buildPileString = "| ";
        for (GamePile buildPile : buildPiles){
            remainingInPile += buildPile.getRemainingCards() + " | ";
            buildPileString +=  buildPile.getTopCard().toString() + " | ";
        }
        System.out.println(remainingInPile + "\n" + buildPileString + "\n");

        String suitPileString = "| ";
        for (GamePile suitPile : suitPiles){
            suitPileString += suitPile.getTopCard().toString() + " | ";
        }
        System.out.println("suitPiles: " + suitPileString);
    }
}