public class Player extends GameCharacter {
    int level;


    public Player(int health, int strength, int dexterity, String name, int experience, int gold) {
        super(health, strength, dexterity, name, experience, gold);
        this.level = 1;
    }

}
