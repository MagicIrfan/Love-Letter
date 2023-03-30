package com.loveLetter.Player;

import com.loveLetter.Cards.Card;
import java.util.*;

public class Player {

    private final List<Card> cards;
    private final List<Card> discardedCards;
    private boolean lost;
    private int points;
    private final int id;
    private TargetSelectionStrategy targetSelectionStrategy;

    public Player(int id){
        this.lost = false;
        this.points = 0;
        this.id = id;
        this.cards = new ArrayList<>();
        this.discardedCards = new ArrayList<>();
    }

    public void addCard(Card card){
        this.cards.add(card);
    }

    public void removeCard(Card card){
        this.cards.remove(card);
        this.discardedCards.add(card);
    }

    public void removeCard(int index){
        Card discardedCard = this.cards.remove(index);
        this.discardedCards.add(discardedCard);
    }

    public Card getLastCardDiscarded() {
        List<Card> discardedCards = this.getDiscardedCards();
        if (!discardedCards.isEmpty()) {
            return discardedCards.get(discardedCards.size() - 1);
        }
        return null;
    }

    public List<Card> getCards(){
        return this.cards;
    }

    public Card chooseCard()  {
        return this.targetSelectionStrategy.chooseCard(this);
    }

    public void setTargetStrategy(TargetSelectionStrategy targetSelectionStrategy) {
        this.targetSelectionStrategy = targetSelectionStrategy;
    }

    public int chooseTargetIndex(List<Player> players) {
        return targetSelectionStrategy.selectTargetPlayerIndex(players,this);
    }

    public int chooseTargetCardIndex() {
        return targetSelectionStrategy.selectTargetCardIndex(this);
    }

    public void putCardUnderThePick(List<Card> pick){
        targetSelectionStrategy.putCardUnderThePick(this, pick);
    }

    public void pickCard(Card card){
        this.cards.add(card);
    }

    public boolean isLost(){
        return this.lost;
    }

    public int getPoints(){
        return this.points;
    }

    public void addPoint(){
        this.points += 1;
    }

    public int getId(){
        return this.id;
    }

    public void setLost(boolean lost) { this.lost = lost; }

    public List<Card> getDiscardedCards(){
        return this.discardedCards;
    }

    @Override
    public String toString(){
        return "Joueur " + this.id;
    }
}
