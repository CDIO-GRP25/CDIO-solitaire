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

    public ArrayList<Card> getRevealed(){
        ArrayList<Card> revealedCards = new ArrayList<>();
        for(Card card : cardPile){
            if(card.getRevealed() == true){
                revealedCards.add(card);
            }
        }
    return revealedCards;
    }

    public void removeCards(ArrayList<Card> cards){
        for(Card card : cards){
            cardPile.remove(card);
        }
    }

    public void addCards(ArrayList<Card> cards){
        for(Card card : cards){
            cardPile.add(card);
        }
    }
}