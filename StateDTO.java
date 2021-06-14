import java.util.ArrayList;

public class StateDTO {
    ArrayList<String> builds;
    ArrayList<String> suits;
    String deck;

    public StateDTO(ArrayList<String> builds, ArrayList<String> suits, String deck) {
        this.builds = builds;
        this.suits = suits;
        this.deck = deck;
    }
    public StateDTO(){

    }
    public ArrayList<String> getBuilds() {
        return builds;
    }

    public void setBuilds(ArrayList<String> builds) {
        this.builds = builds;
    }

    public ArrayList<String> getSuits() {
        return suits;
    }

    public void setSuits(ArrayList<String> suits) {
        this.suits = suits;
    }

    public String getDeck() {
        return deck;
    }

    public void setDeck(String deck) {
        this.deck = deck;
    }
}

