// GRUPPE 21
import java.lang.reflect.Constructor;

/**
 * card
 */
public class Card {

    private Suits suit;
    private int rank;
    private String colour;
    private Boolean revealed = false;



    public Card(Suits suit, int rank) {
        this.suit = suit;
        this.rank = rank;
        if( suit == Suits.CLUBS || suit == Suits.SPADES) {
            this.colour = "black";
        } else {
            this.colour = "red";
        }
    }

    public Suits getSuit() {
        return suit;
    }

    public void reveal() {
        this.revealed = true;
    }

    public int getRank() {
        return rank;
    }

    public String getColour() {
        return colour;
    }

}