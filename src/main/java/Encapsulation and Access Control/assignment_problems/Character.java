public class Character {
    private int health;
    private final int maxHealth;

    public Character(int maxHealth) {
        if (maxHealth <= 0) {
            throw new IllegalArgumentException(
                    "Maximum health must be positive.");
        }

        this.maxHealth = maxHealth;
        this.health = maxHealth;
    }

    public void takeDamage(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException(
                    "Damage cannot be negative.");
        }

        health = Math.max(0, health - amount);
    }

    public void heal(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException(
                    "Healing cannot be negative.");
        }

        health = Math.min(maxHealth, health + amount);
    }

    public int getHealth() {
        return health;
    }

    public static void main(String[] args) {
        Character c = new Character(100);

        c.takeDamage(30);
        System.out.println("Health: " + c.getHealth());

        c.heal(50);
        System.out.println("Health: " + c.getHealth());

        c.takeDamage(150);
        System.out.println("Health: " + c.getHealth());
    }
}