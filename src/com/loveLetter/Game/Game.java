package com.loveLetter.Game;

import com.loveLetter.Cards.*;
import com.loveLetter.Player.*;

import java.util.*;

public class Game {

    private final List<Player> players;
    private Deck deck;
    private Round round;

    public Game(int numberOfOpponents){
        this.players = new ArrayList<>();
        this.deck = new Deck();
        for(int index=0; index<numberOfOpponents + 1; index++){
            Player player = new Player(index+1);
            this.players.add(player);
            player.setTargetStrategy(index==0 ? new ChooseTargetSelectionStrategy() : new RandomTargetSelectionStrategy());

        }
        deck.draw();
        this.round = new Round(players,deck);
    }

    public int getMaximumPoints(){
        List<Player> players = new ArrayList<>(this.players);
        int[] points = {0, 6, 5, 4, 3, 3};
        return points[players.size() - 1];
    }

    public boolean isFinished(){
        int maximumPoints = getMaximumPoints();
        return this.players.stream()
                .anyMatch(player -> player.getPoints() >= maximumPoints && !player.isLost());
    }

    public void processPlayerTurn(){
        System.out.println("""
        -----------------------------------------------------------
        -------------Le tour vient de commencer--------------------
        -----------------------------------------------------------
        """);
        while(!round.isFinished()){
            round.play();
        }
        System.out.println("""
        -----------------------------------------------------------
        -------------Le tour vient de se terminer------------------
        -----------------------------------------------------------
        """);
    }

    public void processGame(){
        while(!this.isFinished()){
            this.processPlayerTurn();
            //si il n'y a plus de cartes ou qu'il reste un joueur, on désigne le gagnant
            round.setWinner();
            // on commence une nouvelle manche
            this.beginTurn();
        }
    }

    public void play(){
        System.out.println("Le jeu commence, il faut " + this.getMaximumPoints() + " points pour gagner !");
        this.processGame();
    }

    public void beginTurn(){
        for(Player player : this.players){
            player.setLost(false);
            player.getCards().clear();
            player.getDiscardedCards().clear();
        }
        deck = new Deck();
        round = new Round(players,deck);
    }
}
