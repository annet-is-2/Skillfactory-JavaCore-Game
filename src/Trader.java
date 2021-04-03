public class Trader {

    /*
    Class Trader.
    Trader sells A liquid to restore Health for Player.
    input: number of items to buy
    output: int number of Items was sold
     */
    public int sell (int numberOfItems) {
            if (numberOfItems <= 0){
                System.out.println("I can sell At least 1 item of potion");
            }

            return numberOfItems;
    }
}
