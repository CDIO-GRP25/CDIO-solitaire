class main {
    public static void main(String[] args) {
        Gamestate game = new Gamestate();
        Controller controller = new Controller(game);
        controller.runGame();

    }
}
