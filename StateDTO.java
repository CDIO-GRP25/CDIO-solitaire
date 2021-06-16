package com.example.cdio.logic;

import java.util.ArrayList;

public class StateDTO {
    String[] builds;
    String[] suits;
    String deck;

    public StateDTO(String[] builds, String[] suits, String deck) {
        this.builds = builds;
        this.suits = suits;
        this.deck = deck;
    }
    public StateDTO(){

    }
    public String[] getBuilds() {
        return builds;
    }

    public void setBuilds(String[] builds) {
        this.builds = builds;
    }

    public String[] getSuits() {
        return suits;
    }

    public void setSuits(String[] suits) {
        this.suits = suits;
    }

    public String getDeck() {
        return deck;
    }

    public void setDeck(String deck) {
        this.deck = deck;
    }
}

