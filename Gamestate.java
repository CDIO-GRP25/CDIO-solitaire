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
        for (int i = 0; i < 7; i++) {
            GamePile y = new Pile();
            buildPiles.add(y);
        }
        for (Suits s : Suits.values()) {
            GamePile x = new SuitPile(s);
            suitPiles.add(x);
        }
        dealCards();
    }

/*
    public void dealCards() {
        for (GamePile gamePile : buildPiles) {
            for (int i = 0; i < buildPiles.indexOf(gamePile) + 1; i++) {
                gamePile.setupCard(deck.drawCard());
            }
            gamePile.getTopCard().reveal();
        }
    } */

    // only for test
    public void dealCards() {
        for (GamePile gamePile : buildPiles) {
            for (int i = 0; i < buildPiles.indexOf(gamePile) + 1; i++) {
                if (buildPiles.indexOf(gamePile) == i && i == 0) {
                    // ignore    
                }
                else if (buildPiles.indexOf(gamePile) == i && i == 1) {
                    Card card = new Card(Suits.CLUBS, 12);
                    gamePile.setupCard(card);
                }
                else {
                    gamePile.setupCard(deck.drawCard());
                }
                
            }
            if (gamePile.cardPile.size() != 0) {
                gamePile.getTopCard().reveal();
            }
        }
    }

    public void moveCardToPile(int[] input) {
        GamePile pileFrom = buildPiles.get(input[0]);
        GamePile pileTo = buildPiles.get(input[1]);

        ArrayList<Card> cardsToMove = pileFrom.getRevealed();
        //check if move is possible
        if(!pileTo.getTopCard().getColour().equals(cardsToMove.get(0).getColour())
                && (pileTo.getTopCard().getRank() == (cardsToMove.get(0).getRank() + 1) )){
            //move the cards
            pileFrom.removeCards(cardsToMove);
            pileTo.addCards(cardsToMove);
            //reveal card underneath moved pile
            if(pileFrom.getRemainingCards() != 0){
                pileFrom.getTopCard().reveal();
            }
        }
        else{
            System.out.println("Hov hov, ulovligt træk!");
        }
    }

    public void print(){
        System.out.println("pilenr: | 1 | 2 | 3 | 4 | 5 | 6 | 7 |");
        boolean printDone = false;
        for (int j = 0; j < 20; j++){
            for (int i = 0 ; i < 7; i++){
                printDone = true;
                if(buildPiles.get(i) != null && buildPiles.get(i).getCardByIndex(j) != null){
                    if(buildPiles.get(i).getCardByIndex(j).getRevealed()){
                        System.out.print(buildPiles.get(i).getCardByIndex(j).toString() + " | ");

                    }
                    else{
                        System.out.print("hidden | ");
                    }
                    printDone = false;
                }
                else{
                    System.out.print("- | ");
                }

            }
            System.out.print("\n");
            if(printDone){
                break;
            }
        }


        /* String remainingInPile ="| ";
        String buildPileString = "| ";
        for (GamePile buildPile : buildPiles){
            remainingInPile += buildPile.getRemainingCards()-1 + " | ";
            buildPileString +=  buildPile.getTopCard().toString() + " | ";
        }
        System.out.println(remainingInPile + "\n" + buildPileString + "\n");
*/
        /*String suitPileString = "| ";
        for (GamePile suitPile : suitPiles){
            if(suitPile.getTopCard() != null) {
                suitPileString += suitPile.getTopCard().toString() + " | ";
            }
            else{
                suitPileString += "empty," + ((SuitPile)suitPile).getSuit() + " | ";
            }
        }
        System.out.println("suitPiles: " + suitPileString);*/
    }
}