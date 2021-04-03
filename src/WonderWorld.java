import javax.imageio.IIOException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class WonderWorld {

    private static BufferedReader br;
    private static GameCharacter player = null;
    private static Battle battleScene = null;

    private static final ArrayList<String> menuList = new ArrayList<>(4);
    private static final ArrayList<String> menuOptions = new ArrayList<>(3);

    private static final ArrayList<String> childMenu1 = new ArrayList<>(3);
    private static final ArrayList<String> childOptions1 = new ArrayList<>(3);

    private static final ArrayList<String> childMenu2 = new ArrayList<>(2);
    private static final ArrayList<String> childOptions2 = new ArrayList<>(2);

    public static void main(String[] args) throws IOException {

        try {
            br = new BufferedReader(new InputStreamReader(System.in));

            //получить имя героя
            String playerName = getPlayerName();

            // создать героя
            player = new Player(100, 500, 30, playerName, 8, 200);

            fillMenu();
            printMenu(menuList);
            selectFromMenu();

            br.close();

        } catch (IIOException e){
            e.printStackTrace();
        }
    }

    public static String getPlayerName () throws IOException {

        System.out.println("Придумайте имя Игроку");

        return br.readLine();;
    }

    public static void fillMenu (){
        menuList.add(0,"Куда вы хотите пойти?");
        menuList.add(1, "1. К торговцу");
        menuList.add(2, "2. В Темный Лес");
        menuList.add(3, "3. На Выход");
        menuOptions.add(0,"1");
        menuOptions.add(1, "2");
        menuOptions.add(2, "3");


        //под-меню: 2. В Темный Лес
        childMenu1.add(0, "4. Вернуться в город");
        childMenu1.add(1, "5. Продолжить бой");
        childMenu1.add(2, "7. К торговцу");
        childOptions1.add(0, "4");
        childOptions1.add(1, "5");
        childOptions1.add(2, "7");


        //под-меню: 1. К торговцу
        childMenu2.add(0, "4. Вернуться в город");
        childMenu2.add(1, "6. Продолжить торговлю");
        childOptions2.add(0, "4");
        childOptions2.add(1, "6");
    }

    public static void printChildMenu(String menuItem){
        if ((menuItem.equals(menuOptions.get(1))) || (menuItem.equals(childOptions1.get(1)))){
            printMenu(childMenu1);
        }else if ((menuItem.equals(menuOptions.get(0))) || (menuItem.equals(childOptions2.get(1))) || (menuItem.equals(childOptions1.get(2))) ) {
            printMenu(childMenu2);
        }

    }

    public static void printMenu(ArrayList<String> menu){
        for (String item: menu){
            System.out.println(item);
        }
    }

    public static String checkSelectedMenuItem(String menuName, String menuItem) throws IOException {
        String errorMsg = "Неизвестная опция. Выберите из Меню";

        ArrayList<String> tmpMenu;
        ArrayList<String> tmpMenuOptions;

        if (menuName.equals("Под-Меню-1")) {tmpMenu = childMenu1; tmpMenuOptions = childOptions1;}
            else if (menuName.equals("Под-Меню-2")) {tmpMenu = childMenu2; tmpMenuOptions = childOptions2;}
                else {tmpMenu = menuList; tmpMenuOptions = menuOptions;}

        while (!(tmpMenuOptions.contains(menuItem))) {
                System.out.println(errorMsg);
                printMenu(tmpMenu);
                menuItem = br.readLine();
        }
        return menuItem;
    }


    public static void selectFromMenu() throws IOException {

        String tmpMenu = "Главное меню";

        boolean quit = false;
        while (!quit) {
            String selectedItem = br.readLine();
            selectedItem = checkSelectedMenuItem (tmpMenu, selectedItem);


            switch (selectedItem) {
                case "1":
                case "6":
                case "7":
                    System.out.println("Торговец еще не вышел на работу");
                    printChildMenu(selectedItem);
                    tmpMenu = "Под-Меню-2";
                    break;
                case "2":
                case "5":
                    System.out.println("!!!Сейчас начнется Битва!!!!");
                    startBattle();
                    printChildMenu(selectedItem);
                    tmpMenu = "Под-Меню-1";
                    break;
                case "4":
                    printMenu(menuList);
                    tmpMenu = "Главное меню";
                    break;
                case "3":
                    System.out.println("Выход из игры...");
                    quit = true;
                    break;
            }
        }
    }

    public static void startBattle() {

        battleScene = new Battle(player);

        battleScene.start();
        try {
            battleScene.join();
        } catch (InterruptedException e){

        }
    }
}
