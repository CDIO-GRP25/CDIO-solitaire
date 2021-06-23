// GRUPPE 21

/**
 * Pile
 */
public class Pile extends GamePile {

    @Override
    void addCard(Card card) {
        if ((getTopCard().getColour() != card.getColour()) && getTopCard().getRank() == card.getRank() +1 ) {
            cardPile.addFirst(card);
        } else {
            System.out.println("Some error message");
        }

    }
    

}