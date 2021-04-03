public class Battle extends Thread{

    private GameCharacter player;

    public Battle (GameCharacter player) {
        this.player = player;
    }

    @Override
    public void run () {
        GameCharacter monster = createMonster();
        runBattle(player, monster);
    }

    private static GameCharacter createMonster(){
        int random = (int) (Math.random() * 10);

        //С вероятностью 50% создается или скелет, или гоблин
        if (random % 2 == 0) return new MonsterGoblin(100,609,100,"Goblin Monster",5,30);
        else return new MonsterSkeleton(10,50,10,"Skeleton Monster",5,10);
    }

    public static void runBattle(GameCharacter player, GameCharacter monster){
        boolean battleEnded = false;
         String starString = "***********************";

        int round =0;

        System.out.println(starString);
        System.out.println(player);
        System.out.println(starString);
        System.out.println(monster);
        System.out.println(starString);


        while (!battleEnded){
            if (round % 2 == 0) {
                battleEnded = attack (player, monster);
            }
            else {
                battleEnded = attack(monster, player);
            }
            round ++;
        }
        printBattleResult(player, monster, round);
    }

    private static boolean attack (GameCharacter attacker, GameCharacter defender){
        int fightSize = attacker.fight();

        // decrease the health of the defender on the hitSize.
        // if the defender's health < 0 => make it zero.
        if (fightSize != 0) {
            int defenderHealth = defender.getHealth() - fightSize;
            defender.setHealth((defenderHealth < 0) ? 0 : defenderHealth);

            // if defender is dead and attacker was a Player => attacker gets its Gold and Experience
            defenderHealth = defender.getHealth();
            if ((defenderHealth == 0) && (attacker instanceof Player)) {
                    // attacker received Gold
                    attacker.setGold(attacker.getGold() + defender.getGold());

                    // attacker increased experience
                    attacker.setExperience(attacker.getExperience() + defender.getExperience());

            }
        }
        printAttackResult(defender, attacker, fightSize);

        if (defender.getHealth() ==0 || attacker.getHealth() == 0) return true;
        else return false;

    }

    private static void printAttackResult (GameCharacter defender, GameCharacter attacker, int fightSize){
        if (fightSize == 0) {
            System.out.println(String.format("%s Промахнулся!", attacker.getName()));
        } else {
            System.out.println(String.format("%s нанес удар на %s в размере %d", attacker.getName(), defender.getName(), fightSize));
        }
        if ((defender.getHealth() ==0) && (attacker instanceof Player)){
            System.out.println(String.format("%s выиграл эту Атаку. Получил: Золото +%d, Опыт +%d",attacker.getName(),defender.getGold(), defender.getExperience()));
        }

    }

    private static void printBattleResult (GameCharacter player, GameCharacter monster, int round){
        System.out.println(String.format("Битва прошла за %d раунд(а/ов)", round));
        if (player.getHealth()!=0) {
            System.out.println(String.format("%s Выиграл битву. У тебя: Золота= %d, Здоровья= %d, Опыта= %d", player.getName(), player.getGold(), player.getHealth(), player.getExperience()));
        } else {
            System.out.println(String.format("%s Проиграл битву.У тебя: Золота= %d, Здоровья= %d, Опыта= %d", player.getName(), player.getGold(), player.getHealth(), player.getExperience()));
        }
    }
}
