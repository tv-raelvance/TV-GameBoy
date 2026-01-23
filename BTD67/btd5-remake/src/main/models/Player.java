public class Player {
    private int score;
    private int lives;
    private int resources;

    public Player() {
        this.score = 0;
        this.lives = 100; // Starting lives
        this.resources = 100; // Starting resources
    }

    public int getScore() {
        return score;
    }

    public void addScore(int points) {
        this.score += points;
    }

    public int getLives() {
        return lives;
    }

    public void loseLife() {
        this.lives--;
    }

    public int getResources() {
        return resources;
    }

    public void spendResources(int amount) {
        this.resources -= amount;
    }

    public void earnResources(int amount) {
        this.resources += amount;
    }
}