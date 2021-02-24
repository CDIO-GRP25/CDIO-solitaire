
/**
 * Pile
 */
public class Pile extends GamePile {

    @Override
    void addCard(Card card) {
        if (getRemainingCards() == 0 && card.getRank() == 12) {
            cardPile.add(card);
        }
        else if (getRemainingCards() == 0) {
            System.out.print("Pile is empty only kings can be placed");
        }
        else {
            if (getTopCard().getColour().equals(card.getColour()) && (getTopCard().getRank() == card.getRank() +1 )) {
                cardPile.add(card);
            } else {
                System.out.println("Some error message");
            }
        }
    }


}