import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

/**
 * gamePile
 */
abstract class GamePile {

    ArrayList<Card> cardPile = new ArrayList<>();
    Card removeTopCard() {
        return cardPile.remove(cardPile.size() - 1);
    };
    abstract void addCard(Card card);
    
    Card getTopCard() {
        return cardPile.get(cardPile.size() - 1);
    };
    Card getCardByIndex(int x){
        if(x < cardPile.size()) {
            return cardPile.get(x);
        }
        else return null;
    }

    int getRemainingCards() {
        return cardPile.size();
    };

    void setupCard(Card card){
        cardPile.add(card);
    }
}