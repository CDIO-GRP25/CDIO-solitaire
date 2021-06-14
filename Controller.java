import java.util.ArrayList;
import java.util.Scanner;

public class Controller {
    Gamestate gamestate;
    Scanner scanner = new Scanner(System.in);
    ArrayList<StateDTO> testStates = new ArrayList<>();

    public Controller (Gamestate gamestate){
        this.gamestate = gamestate;
        generateTestStates();
        gamestate.updateState(testStates.get(0));
    }

    public void runGame(){
        while(true){
            gamestate.print();
            Move bestMove = gamestate.detectMoves();
            scanner.nextLine();
            String input = bestMove.getCommand();
            if(input.isEmpty()){
                System.out.println("no input...");
            }
            //checking input action
            else if(input.charAt(0) == 'd' && input.length() == 1) {
                gamestate.drawNextCard();
            }
            else if(input.equals("dms")){
                gamestate.drawToSuitPile();
            }
            else if(input.length() == 3 && input.substring(0,2).equals("dm") ){
                //move flipped card from draw pile to build pile
                gamestate.moveDrawPileCard(Integer.parseInt(input.substring(2)) - 1);
            }
            else if(input.charAt(0) == 'm' && input.length()==4 && input.charAt(2) == ','){
                int[] move = extractMoveInput(input);
                gamestate.moveCardToPile(move);
            }
            else if(input.length() == 2 && input.substring(0,1).equals("s")){
                gamestate.addCardToSuitPile(Integer.parseInt(input.substring(1)) - 1);
            }
            else {
                System.out.println("input ikke gyldigt");
            }
        }
    }

    private int[] extractMoveInput(String input){
        //System.out.println("input string: " + input);
        //System.out.println("input substring: " + input.substring(1));
        String[] splitInput = input.substring(1).split(",");
        //System.out.println("split string0: " + splitInput[0] + ", splitstring1: "+ splitInput[1]);
        int[] move = new int[2];
        move[0] = Integer.parseInt(splitInput[0])-1;
        move[1] = Integer.parseInt(splitInput[1])-1;
        return move;
    }

    private void generateTestStates() {
        //add the states you want to run through
        testStates.add(new StateDTO(new String[]{"C1", "C2", "C3", "D5", "S12", "C8", "H10"}, new String[]{"C1", "C2", "C2", "C2"}, "C1" ));
    }
}
