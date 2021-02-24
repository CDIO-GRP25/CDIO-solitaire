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
            int[] input = getInput();
            gamestate.moveCardToPile(input);
        }
    }

    private int[] getInput(){
        String[] splitInput = scanner.nextLine().split(",");
        int[] input = new int[2];
        input[0] = Integer.parseInt(splitInput[0])-1;
        input[1] = Integer.parseInt(splitInput[1])-1;
        return input;
    }
}
