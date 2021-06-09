public class Move {
    private int priority;
    private String movedesc;

    public Move(int priority, String movedesc ){
        this.priority = priority;
        this.movedesc = movedesc;
        System.out.println("prio: " + priority + " " + movedesc);
    }

    public int getPriority(){
        return priority;
    }

    public String getMovedesc(){
        return movedesc;
    }



}
