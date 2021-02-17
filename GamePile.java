import java.util.Deque;
import java.util.LinkedList;

/**
 * gamePile
 */
abstract class GamePile {

    Deque<Card> cardPile = new LinkedList<Card>();
    Card removeTopCard() {
        return cardPile.removeFirst();
    };
    abstract void addCard(Card card);
    
    Card getTopCard() {
        return cardPile.peek();
    };
    int getRemainingCards() {
        return cardPile.size();
    };
}