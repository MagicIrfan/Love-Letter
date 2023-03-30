package com.loveLetter.Game;

import com.loveLetter.Cards.*;

import java.util.*;

public class Deck {
    private final List<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
        // Ajouter les cartes au deck
        initializeCards();
        shuffle();
    }

    public void initializeCards(){
        cards.add(new Spy());
        cards.add(new Spy());
        cards.add(new Guard());
        cards.add(new Guard());
        cards.add(new Guard());
        cards.add(new Guard());
        cards.add(new Guard());
        cards.add(new Guard());
        cards.add(new Priest());
        cards.add(new Priest());
        cards.add(new Baron());
        cards.add(new Baron());
        cards.add(new Servant());
        cards.add(new Servant());
        cards.add(new Prince());
        cards.add(new Prince());
        cards.add(new Chancellor());
        cards.add(new Chancellor());
        cards.add(new King());
        cards.add(new Countess());
        cards.add(new Princess());
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card draw() {
        if (cards.isEmpty()) {
            return null;
        }
        return cards.remove(cards.size() - 1);
    }

    public List<Card> getCards(){
        return cards;
    }
}
