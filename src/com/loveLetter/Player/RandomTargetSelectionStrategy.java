package com.loveLetter.Player;

import com.loveLetter.Cards.Card;

import java.util.List;
import java.util.Random;

public class RandomTargetSelectionStrategy implements TargetSelectionStrategy {

    @Override
    public int selectTargetPlayerIndex(List<Player> availablePlayers, Player activePlayer) {
        if (availablePlayers.isEmpty()) {
            return -1;
        }
        Random random = new Random();
        return availablePlayers.indexOf(availablePlayers.get(random.nextInt(availablePlayers.size())));
    }

    @Override
    public int selectTargetCardIndex(Player activePlayer) {
        Random random = new Random();
        int id;
        do{
            id = random.nextInt(10);
        }
        while(id == 1);
        return id;
    }

    @Override
    public Card chooseCard(Player activePlayer) {
        List<Card> playerCards = activePlayer.getCards();
        Random random = new Random();
        return playerCards.get(random.nextInt(playerCards.size()));
    }

    @Override
    public void putCardUnderThePick(Player activePlayer, List<Card> pick) {
        List<Card> playerCards = activePlayer.getCards();
        for(int index = 1; index<=2; index++){
            if(!playerCards.isEmpty()){
                Random random = new Random();
                Card chosenCard = playerCards.get(random.nextInt(playerCards.size()));
                activePlayer.removeCard(chosenCard);
                pick.add(0,chosenCard);
            }
        }
    }
}
