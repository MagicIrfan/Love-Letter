package com.loveLetter.Player;

import java.util.List;
import java.util.Scanner;
import com.loveLetter.Cards.Card;

public class ChooseTargetSelectionStrategy implements TargetSelectionStrategy {
    @Override
    public int selectTargetPlayerIndex(List<Player> availablePlayers, Player activePlayer) {
        Scanner scanner = new Scanner(System.in);
        Player chosenPlayer = null;
        int chosenIndex = -1;
        while (chosenPlayer == null) {
            System.out.println(activePlayer + ", vous voulez viser quel joueur ?");
            for (int i = 0; i < availablePlayers.size(); i++) {
                System.out.println(i + " - " + availablePlayers.get(i));
            }
            if (scanner.hasNextInt()) {
                chosenIndex = scanner.nextInt();
                if (chosenIndex >= 0 && chosenIndex < availablePlayers.size() && availablePlayers.get(chosenIndex).getId() != activePlayer.getId()) {
                    chosenPlayer = availablePlayers.get(chosenIndex);
                }
            }
            scanner.nextLine(); // Clear the scanner buffer
        }
        return chosenIndex;
    }

    @Override
    public int selectTargetCardIndex(Player activePlayer) {
        int chosenNumber = -1;
        Scanner scanner = new Scanner(System.in);
        do
        {
            System.out.println(activePlayer + ", " + "vous voulez choisir quel carte (sauf le garde) ?");
            System.out.println("""
                    0 - Espionne
                    2 - Prêtre
                    3 - Baron
                    4 - Servante
                    5 - Prince
                    6 - Chancelier
                    7 - Roi
                    8 - Comtesse
                    9 - Princesse
                    """);
            if(scanner.hasNextInt()){
                chosenNumber = scanner.nextInt();
            }
        }
        while(chosenNumber == 1 || chosenNumber < 0 || chosenNumber > 9);
        return chosenNumber;
    }

    @Override
    public Card chooseCard(Player activePlayer) {
        Scanner scanner = new Scanner(System.in);
        List<Card> playerCards = activePlayer.getCards();
        int index = 0;
        do
        {
            for(int JIndex = 0; JIndex < playerCards.size(); JIndex++)
            {
                System.out.println(JIndex + " - " + playerCards.get(JIndex).getName());
            }

            if(scanner.hasNextInt()){
                index = scanner.nextInt();
            }
        }
        while(index < 0 || index >= playerCards.size());
        return playerCards.get(index);
    }

    @Override
    public void putCardUnderThePick(Player activePlayer, List<Card> pick) {
        Scanner scanner = new Scanner(System.in);
        for(int index = 1; index<=2; index++){
            if(!activePlayer.getCards().isEmpty()){
                System.out.println("Choisissez la " + index + " ème carte à mettre sous la pioche");
                int chosenIndex = 0;
                do {
                    for(int JIndex = 0; JIndex < activePlayer.getCards().size(); JIndex++){
                        System.out.println(JIndex + " - " + activePlayer.getCards().get(JIndex));
                    }
                    if(scanner.hasNextInt()){
                        chosenIndex = scanner.nextInt();
                    }
                }
                while(chosenIndex < 0 || chosenIndex >= activePlayer.getCards().size());
                Card chosenCard = activePlayer.getCards().get(chosenIndex);
                activePlayer.removeCard(chosenCard);
                pick.add(0,chosenCard);
            }
        }
    }
}