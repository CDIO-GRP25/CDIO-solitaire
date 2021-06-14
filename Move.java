public class Move {
    private int priority;
    private String movedesc;
    private String command;

    public Move(int priority, String movedesc, String command ){
        this.priority = priority;
        this.movedesc = movedesc;
        this.command = command;

        System.out.println("prio: " + priority + " " + movedesc);
    }

    public int getPriority(){
        return priority;
    }

    public String getMovedesc(){
        return movedesc;
    }

    public String getCommand() {
        return command;
    }
}
