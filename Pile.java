
/**
 * Pile
 */
public class Pile extends GamePile {

    @Override
    void addCard(Card card) {
        if (getTopCard().getColour().equals(card.getColour()) && (getTopCard().getRank() == card.getRank() +1 )) {
            cardPile.add(card);
        } else {
            System.out.println("Some error message");
        }

    }


}