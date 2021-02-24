class main {
    public static void main(String[] args) {
        Gamestate game = new Gamestate();
        game.print();

        //game.moveCardToPile(game.buildPiles.get(1), game.buildPiles.get(0));
        game.moveCardToPile(game.buildPiles.get(2), game.buildPiles.get(0));
        game.print();
        

    }
}
