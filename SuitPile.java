
/**
 * SuitPile
 */
public class SuitPile extends GamePile {
    Boolean finished = false;
    Suits suit;

    public SuitPile(Suits suit) {
        this.suit = suit;
    }

    @Override
    void addCard(Card card) {
        if (card.getSuit() == suit && getTopCard().getRank() == card.getRank() -1) {
            cardPile.addFirst(card);
        } else {
            System.out.println("Can only add cards of the same suit to this suitpile");
        }

    }
}
