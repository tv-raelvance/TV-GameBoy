public class Game {
    private boolean running;
    private GameController gameController;

    public Game() {
        this.running = true;
        this.gameController = new GameController();
    }

    public void start() {
        initialize();
        gameLoop();
    }

    private void initialize() {
        // Initialize game components here
    }

    private void gameLoop() {
        while (running) {
            update();
            render();
            // Add delay or frame rate control if necessary
        }
    }

    private void update() {
        gameController.updateGameState();
    }

    private void render() {
        // Render game graphics here
    }

    public void stop() {
        running = false;
    }
}