import java.util.Scanner;

public class Controller {
    Gamestate gamestate;
    Scanner scanner = new Scanner(System.in);

    public Controller (Gamestate gamestate){
        this.gamestate = gamestate;
    }


    public void runGame(){
        while(true){
            gamestate.print();
            String input = scanner.nextLine();
            //checking input action
            if(input.charAt(0) == 'd' && input.length() == 1) {
                gamestate.drawNextCard();
            }
            else if(input.length() == 3 && input.charAt(1) == 'm'){
                //move flipped card from draw pile to build pile
                gamestate.moveDrawPileCard(Integer.parseInt(input.substring(2)) - 1);
            }
            else if(input.charAt(0) == 'm'){
                int[] move = extractMoveInput(input);
                gamestate.moveCardToPile(move);
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
}
