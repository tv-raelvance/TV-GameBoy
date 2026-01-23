public class Balloon {
    private int health;
    private int speed;

    public Balloon(int health, int speed) {
        this.health = health;
        this.speed = speed;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void move() {
        // Logic for moving the balloon along the path
    }
}