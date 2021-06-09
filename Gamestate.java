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
        if( !cardsToMove.isEmpty() &&
                //( pileTo.getTopCard() == null && (cardsToMove.get(0).getRank() == 12) ) ||
                //pileTo.getTopCard() != null &&
                        isMoveLegal(cardsToMove.get(0),pileTo.getTopCard())){
            //move the cards
            pileFrom.removeCards(cardsToMove);
            pileTo.addCards(cardsToMove);
            //reveal card underneath moved pile
            if(pileFrom.getRemainingCards() != 0){
                pileFrom.getTopCard().reveal();
            }
        }
    }
    private int movecheckcount = 0;
    private boolean isMoveLegal(Card from, Card to){
        movecheckcount++;
        if( ( (to == null) && (from.getRank()==12) ) ||
                (to!=null  && !to.getColour().equals(from.getColour()) && (to.getRank() == (from.getRank()+1)) ) ){
            return true;
        }
        else{
            //System.out.println("Hov hov, ulovligt træk! " + from + " " + to);
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
            //drawcard removes the card from the deck btw :)
            cardToMove.add(deck.drawCard());
            cardToMove.get(0).reveal();
            pileTo.addCards(cardToMove);

        }
    }

    public void drawNextCard(){
        deck.skipCard();
    }

    public void addCardToSuitPile(int from){
        GamePile pileFrom;
        if( from >= 0 && from < buildPiles.size()) {
            pileFrom = buildPiles.get(from);
            if (pileFrom.getTopCard() != null) {
                Card cardToMove = pileFrom.getTopCard();
                boolean moveLegal;
                for (GamePile suitPile : suitPiles) {
                    moveLegal = false;
                    if (cardToMove.getSuit() == ((SuitPile)suitPile).getSuit()){
                        if(suitPile.getTopCard() == null && cardToMove.getRank() == 0){
                            //suitpile empty and card is and ace
                            moveLegal = true;
                        }
                        else if(suitPile.getTopCard() != null && suitPile.getTopCard().getRank() == cardToMove.getRank() -1){
                            //suitpile not empty and card is +1
                            moveLegal = true;
                        }
                    }
                    if(moveLegal){
                        suitPile.addCard(cardToMove);
                        System.out.println(cardToMove.toString() + "added to suit");
                        ArrayList<Card> cardRemover = new ArrayList<>();
                        cardRemover.add(cardToMove);
                        pileFrom.removeCards(cardRemover);
                        System.out.println(cardToMove.toString() + " removed from pile");
                        if(pileFrom.getRemainingCards() != 0){
                            pileFrom.getTopCard().reveal();
                        }
                    }
                }
            }
        }
    }
    public void drawToSuitPile(){
        Card temp = deck.getTopCard();

        for(GamePile suitPile : suitPiles){
            boolean moveLegal = false;
            if (temp.getSuit() == ((SuitPile)suitPile).getSuit()){
                if(suitPile.getTopCard() == null && temp.getRank() == 0){
                    //suitpile empty and card is and ace
                    moveLegal = true;
                }
                else if(suitPile.getTopCard() != null && suitPile.getTopCard().getRank() == temp.getRank() - 1){
                    //suitpile not empty and card is +1
                    moveLegal = true;
                }
            }
            if(moveLegal){
                suitPile.addCard(temp);
                deck.drawCard();
            }


        }
    }

    public void print(){
        System.out.printf("|%11s|%11d|%11d|%11d|%11d|%11d|%11d| \n","pilenr: 1",2,3,4,5,6,7);

        for (int j = 0; j < 20; j++){
            //iterate through horizontal lines (max 20 is 7 hidden and 13 shown)
            boolean printDone = true;
            System.out.print("|");
            for (int i = 0 ; i < 7; i++){
                //iterate through piles
                if(buildPiles.get(i) != null && buildPiles.get(i).getCardByIndex(j) != null){
                    // card exists in this position
                    if(buildPiles.get(i).getCardByIndex(j).getRevealed()){
                        System.out.printf("%11s|", buildPiles.get(i).getCardByIndex(j).toString());
                    }
                    else{
                        System.out.printf("%11s|", "hidden");
                    }
                    printDone = false;
                }
                else{
                    // no card in this position
                    System.out.printf("%11s|", " -  ");
                }

            }
            System.out.print("\n");
            if(printDone){ //if no cards found on line
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
        System.out.println("commands: draw=[d], movePile=[mx,y] (x,y is pile index to,from), insertDrawnCard[dmx] (x is pile index to), addToSuitPile=[sx] (x is pile index from), drawCardToSuitPile=[dms]");
        System.out.print("insert command: ");
    }

    public void detectMoves(){
        System.out.println("detecting moves");
        ArrayList<String> possibleCommands = new ArrayList<>();
        ArrayList<Move> moves = new ArrayList<>();
        //check all moves moving cards FROM a buildpile
        for (int i = 0; i < buildPiles.size(); i++) {
            GamePile buildPileFrom = buildPiles.get(i);
            ArrayList<Card> cardsToMove = new ArrayList<>();
            cardsToMove = buildPileFrom.getRevealed();

            //check if cards available:
            if(!cardsToMove.isEmpty()){
                //get highest card in the pile
                Card topCardFrom = cardsToMove.get(0);

                //if topCardFrom is king then look for empty piles:
                if(topCardFrom.getRank() == 12){
                    for(int j = 0; j < buildPiles.size(); j++){
                        GamePile buildPileTo = buildPiles.get(j);
                        if(buildPileTo.getRemainingCards() == 0 && buildPileFrom.getRemainingCards() > cardsToMove.size()){
                            //card will be revealed upon move
                            int prio = 2 + buildPileFrom.getRemainingCards() - buildPileFrom.getRevealed().size();
                            moves.add(new Move(prio, "KINGMOVE: \tfrom " + (i+1) + " \tto " + (j+1)));
                        }
                    }
                }
                //if any other card then look for moves to  "x+1"'s and suitpiles
                else {
                    //checking basicmoves (buildpile to buildpile with x+1)
                    for(int j = 0; j < buildPiles.size(); j++){
                        GamePile buildPileTo = buildPiles.get(j);
                        Card topCardTo = buildPileTo.getTopCard();
                        if (isMoveLegal(topCardFrom, topCardTo)) {
                            //card will be revealed
                            int prio = 2 + buildPileFrom.getRemainingCards() - buildPileFrom.getRevealed().size();
                            moves.add(new Move(prio, "BASICMOVE:\tfrom " + (i+1) + " \tto " + (j+1)));
                        }
                    }

                    //checking TOSUIT moves (from buildpile to suitpile)
                    Card lowCardFrom = buildPileFrom.getTopCard();
                    for(int j = 0; j < suitPiles.size(); j++){
                        GamePile suitPile = suitPiles.get(j);
                        if (lowCardFrom.getSuit() == ((SuitPile) suitPile).getSuit()) {
                            if (suitPile.getTopCard() == null && lowCardFrom.getRank() == 0) {
                                //suitpile empty and card is and ace
                                moves.add(new Move(25, "TOSUITMOVE:\tfrom build " + (i+1) + "\tto suit " + (j+1)));
                            } else if (suitPile.getTopCard() != null && suitPile.getTopCard().getRank() == lowCardFrom.getRank() - 1) {
                                //suitpile not empty and card is +1
                                int prio =  4 + buildPileFrom.getRemainingCards() - buildPileFrom.getRevealed().size();
                                moves.add(new Move(prio, "TOSUITMOVE:\tfrom build " + (i+1) + "\tto suit " + (j+1)));
                            }
                        }
                    }

                    //check if "buildpilefrom" can recieve draw-card
                    Card deckCard = deck.getTopCard();
                    Card buildPileCard =  buildPileFrom.getTopCard();
                    if(isMoveLegal(deckCard, buildPileCard)){
                        moves.add(new Move(1,"DRAWMOVE:\tdraw \tto build " + (i+1) ));
                    }
                }
            }
        }

        //check if draw card can be placed in suitpiles
        Card deckCard = deck.getTopCard();
        for(int i = 0; i < suitPiles.size(); i++){
            GamePile suitPile = suitPiles.get(i);
            if (deckCard.getSuit() == ((SuitPile)suitPile).getSuit()){
                if(suitPile.getTopCard() == null && deckCard.getRank() == 0){
                    //suitpile empty and card is and ace
                    moves.add(new Move(20, "DRAWTOSUIT: \tdraw \tto suit " + (i+1)));
                }
                else if(suitPile.getTopCard() != null && suitPile.getTopCard().getRank() == deckCard.getRank() - 1){
                    //suitpile not empty and card is +1
                    if(deckCard.getRank() == 2){
                        //deck card is a deuce
                        moves.add(new Move(20, "DRAWTOSUIT: \tdraw \tto suit " + (i+1)));
                    }
                    else{
                        moves.add(new Move(2, "DRAWTOSUIT: \tdraw \tto suit " + (i+1)));
                    }
                }
            }
        }

        //obligatorisk drawcard move
        moves.add(new Move(0, "Draw card, no other present moves"));

        Move prioMove = moves.get(0);
        for(int i = 0; i < moves.size(); i++){
            if(prioMove.getPriority() < moves.get(i).getPriority()){
                prioMove = moves.get(i);
            }
        }
        System.out.println("prioMove : "+ prioMove.getMovedesc() );

    }
}