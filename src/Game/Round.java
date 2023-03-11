package Game;

import Player.*;
import java.util.*;
import Cards.*;

public class Round {
    private final List<Player> players;
    private final Deck deck;
    private Player activePlayer;

    public Round(List<Player> players, Deck deck) {
        this.players = players;
        this.deck = deck;
        for(Player player : this.players){
            player.pickCard(deck.draw());
        }
    }

    public void play() {
        while (!isFinished()) {
            // Choisir le prochain joueur
            if(this.activePlayer == null){
                this.activePlayer = this.players.get(0);
            }
            else{
                chooseNextPlayer();
            }
            System.out.println("Au tour du " + activePlayer);
            // Le joueur actif pioche une carte
            activePlayer.pickCard(deck.draw());

            // Le joueur actif choisit une carte à jouer
            Card chosenCard = activePlayer.chooseCard();

            activePlayer.removeCard(chosenCard);

            // Le joueur actif joue la carte
            chosenCard.accept(new PlayVisitor(activePlayer, deck,getAvailablePlayers()));
        }
    }

    private void chooseNextPlayer() {
        int activeIndex = players.indexOf(activePlayer);
        int nextIndex = (activeIndex + 1) % players.size();
        activePlayer = players.get(nextIndex);
    }

    public boolean isFinished() {
        return playerIsAvailable() || deck.draw() == null;
    }

    public List<Player> getAvailablePlayers(){
        return players.stream()
                .filter(p -> p.getId() != activePlayer.getId() && !p.isLost())
                .toList();
    }

    public boolean playerIsAvailable(){
        return players.stream()
                .filter(p -> !p.isLost())
                .count() <= 1;
    }

    public void setWinner() {
        Player winner = determineWinner();
        if(winner != null){
            addPointsToWinner(winner);
            updateGameState(winner);
        }
        else{
            System.out.println("Pas de gagnants ...");
        }
    }

    private Player determineWinner() {
        Player winner = null;
        Card highestCard = null;
        if (deck.getCards().isEmpty()) {
            for (Player player : players) {
                Card playerCard = player.getCards().isEmpty() ? null : player.getCards().get(0);
                if (playerCard != null && (highestCard == null || playerCard.getValue() > highestCard.getValue())) {
                    System.out.println("Le joueur " + player + " a la carte " + player.getCards().get(0));
                    winner = player;
                    highestCard = playerCard;
                }
            }
        } else {
            for (Player player : this.players) {
                if (!player.isLost()) {
                    winner = player;
                    break;
                }
            }
        }
        return winner;
    }

    private void addPointsToWinner(Player winner) {
        winner.addPoint();
        for (Card card : winner.getDiscardedCards()) {
            if (card instanceof Spy) {
                winner.addPoint();
                break;
            }
        }
    }

    private void updateGameState(Player winner) {
        System.out.println("Le gagnant est " + winner);
        System.out.println(winner + " possède désormais " + winner.getPoints() + " points !");
        this.activePlayer = winner;
    }
}
