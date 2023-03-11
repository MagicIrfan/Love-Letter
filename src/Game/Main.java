package Game;

import java.util.Scanner;

public class Main {
    public static void main(String [] args){
        Scanner scanner = new Scanner(System.in);
        int number = 0;
        do
        {
            System.out.println("Entrez le nombre d'adversaires (de 1 à 5): ");
            if (scanner.hasNextDouble()) {
                number = scanner.nextInt();
            }
        }
        while(number < 1 || number > 5);
        Game game = new Game(number);
        game.play();
    }
}
