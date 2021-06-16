package com.example.cdio.logic;

import java.util.ArrayList;
import java.util.Scanner;

public class Controller {
    Gamestate gamestate;
    Scanner scanner = new Scanner(System.in);
    ArrayList<StateDTO> testStates = new ArrayList<>();
    int testStateCounter = 0;

    public Controller (Gamestate gamestate){
        this.gamestate = gamestate;
        //mergefix
        generateTestStates();
    }

    public void runGame(){
        while(true){
            gamestate.updateState(testStates.get(testStateCounter++));
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

    public Move updateGame(StateDTO stateDTO){
        gamestate.updateState(stateDTO);
        Move prioMove = gamestate.detectMoves();
        gamestate.print();
        executeCommand(prioMove.getCommand());
        return prioMove;
    }

    private void executeCommand(String input){
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
        testStates.add(new StateDTO(new String[]{"C2", "D10", "C4", "D5", "S12", "H8", "H10"}, new String[]{"", "", "", ""}, "C1" ));
        // basicmove 3,4
        testStates.add(new StateDTO(new String[]{"C2", "D10", "C0", "C4", "S12", "H8", "H10"}, new String[]{"", "", "", ""}, "C1" ));
        //tosuitmove s3
        testStates.add(new StateDTO(new String[]{"C2", "D10", "D12", "C4", "S12", "H8", "H10"}, new String[]{"", "", "", "C0"}, "C1" ));
        //drawtosuit dms
        testStates.add(new StateDTO(new String[]{"C2", "D10", "D12", "C4", "S12", "H8", "H10"}, new String[]{"", "", "", "C1"}, "C12" ));
        //tosuitmove s1 (to free 1st collumn for king)
        testStates.add(new StateDTO(new String[]{"", "D10", "D12", "C4", "S12", "H8", "H10"}, new String[]{"", "", "", "C2"}, "C12" ));
        //kingmove 5,1
        testStates.add(new StateDTO(new String[]{"S12", "D10", "D12", "C4", "H9", "H8", "H10"}, new String[]{"", "", "", "C2"}, "C12" ));
        //drawcard d
        testStates.add(new StateDTO(new String[]{"S12", "D10", "D12", "C4", "H9", "H8", "H10"}, new String[]{"", "", "", "C2"}, "H11" ));
        //drawtobuild dm1
        testStates.add(new StateDTO(new String[]{"H11", "D10", "D12", "C4", "H9", "H8", "H10"}, new String[]{"", "", "", "C2"}, "H4" ));
        //drawcard d
        testStates.add(new StateDTO(new String[]{"H11", "D10", "D12", "C4", "H9", "H8", "H10"}, new String[]{"", "", "", "C2"}, "S9" ));
    }
}
