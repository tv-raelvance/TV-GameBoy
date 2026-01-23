public class Tower {
    private String name;
    private int damage;
    private int range;
    private int cost;

    public Tower(String name, int damage, int range, int cost) {
        this.name = name;
        this.damage = damage;
        this.range = range;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public int getDamage() {
        return damage;
    }

    public int getRange() {
        return range;
    }

    public int getCost() {
        return cost;
    }

    public void attack(Balloon balloon) {
        // Logic for attacking a balloon
        // This could include reducing the balloon's health
    }
}