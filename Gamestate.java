import java.util.ArrayList;
import java.util.List;

/**
 * Gamestate
 */
public class Gamestate {

    private List<GamePile> buildPiles = new ArrayList<GamePile>();
    private List<GamePile> suitPiles = new ArrayList<GamePile>();
    private Deck deck;

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


    public void dealCards() {
        for (GamePile gamePile : buildPiles) {
            for (int i = 0; i < buildPiles.indexOf(gamePile) + 1; i++) {
                gamePile.setupCard(deck.drawCard());
            }
            gamePile.getTopCard().reveal();
        }
    }

    // only for test with kings
    /*public void dealCards() {
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
    }*/

    public void moveCardToPile(int[] input) {
        GamePile pileFrom = buildPiles.get(input[0]);
        GamePile pileTo = buildPiles.get(input[1]);

        ArrayList<Card> cardsToMove = pileFrom.getRevealed();
        //check if move is possible
        /*
        ******* check has been made into a method itself*******
        if(!pileTo.getTopCard().getColour().equals(cardsToMove.get(0).getColour())
                && (pileTo.getTopCard().getRank() == (cardsToMove.get(0).getRank() + 1) )){*/
        if(( pileTo.getTopCard() == null && (cardsToMove.get(0).getRank() == 12) ) ||
                pileTo.getTopCard() != null && isMoveLegal(cardsToMove.get(0),pileTo.getTopCard())){
            //move the cards
            pileFrom.removeCards(cardsToMove);
            pileTo.addCards(cardsToMove);
            //reveal card underneath moved pile
            if(pileFrom.getRemainingCards() != 0){
                pileFrom.getTopCard().reveal();
            }
        }
    }


    private boolean isMoveLegal(Card from, Card to){
        if(!to.getColour().equals(from.getColour())
                && (to.getRank() == (from.getRank() + 1) )){
            return true;
        }
        else{
            System.out.println("Hov hov, ulovligt træk!");
            return false;
        }
    }

    public void moveDrawPileCard(int inputTo){
        //move flipped card into the build piles if legal move
        GamePile pileTo = buildPiles.get(inputTo);
        //check if legal
        if(isMoveLegal(deck.getTopCard(), pileTo.getTopCard())){
            //move card
            ArrayList<Card> cardToMove = new ArrayList<>();
            cardToMove.add(deck.drawCard());
            cardToMove.get(0).reveal();
            pileTo.addCards(cardToMove);

        }
    }

    public void drawNextCard(){
        deck.skipCard();
    }

    public void print(){
        System.out.printf("|%11s|%11d|%11d|%11d|%11d|%11d|%11d| \n","pilenr: 1",2,3,4,5,6,7);
        boolean printDone = false;
        for (int j = 0; j < 20; j++){
            System.out.print("|");
            for (int i = 0 ; i < 7; i++){
                printDone = true;
                if(buildPiles.get(i) != null && buildPiles.get(i).getCardByIndex(j) != null){
                    if(buildPiles.get(i).getCardByIndex(j).getRevealed()){
                        System.out.printf("%11s|", buildPiles.get(i).getCardByIndex(j).toString());
                    }
                    else{
                        System.out.printf("%11s|", "hidden");
                    }
                    printDone = false;
                }
                else{
                    System.out.printf("%11s|", " -  ");
                }

            }
            System.out.print("\n");
            if(printDone){
                break;
            }
        }

        String suitPileString = "| ";
        for (GamePile suitPile : suitPiles){
            if(suitPile.getTopCard() != null) {
                suitPileString += suitPile.getTopCard().toString() + " | ";
            }
            else{
                suitPileString += "empty," + ((SuitPile)suitPile).getSuit() + " | ";
            }
        }
        System.out.println("suitPiles: " + suitPileString);

        System.out.println("Draw pile: " + deck.getDrawPileSize() + " left (total: " + deck.getDeckSize() + ")");
        System.out.println("Flipped Card: [" + deck.getTopCard() + "] (index: " + deck.getDrawPileCounter() + ")");
        System.out.println("commands: draw=[d], movePile=[mx,y] (x,y is pile index to,from), insertDrawnCard[dmx] (x is pile index to)");
        System.out.print("insert command: ");
    }
}