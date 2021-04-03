public abstract class GameCharacter implements Fighter{
    private int health; // единицы жизни
    private int strength; // сила
    private int dexterity; // ловкость

    private String name; // имя
    private int gold; // количество золота
    private int experience; // опыт

    public GameCharacter(int health, int strength, int dexterity, String name, int experience, int gold) {
        this.health = health;
        this.strength = strength;
        this.dexterity = dexterity;
        this.name = name;
        this.gold = gold;
        this.experience = experience;

    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }


    @Override
    public int fight() {
        if ((dexterity*3) > randomNumber() ){

            return strength;
        } else {
            return 0;
        }
    }

    // взято из подсказок
    private int randomNumber(){
        return (int) (Math.random() * 100);
    }

    @Override
    public String toString() {
        return String.format("Герой по имени:%s\nЗдоровье: %d\nСила:     %d\nЛовкость: %d\nОпыт:     %d\nЗолото:   %d",
                                name, health, strength, dexterity, experience, gold);
    }
}
