package com.loveLetter.Cards;

import com.loveLetter.Player.PlayVisitor;

public class Spy extends Card {

    public Spy(){
        super("SPY",0);
    }

    public void accept(PlayVisitor visitor){
        visitor.visit(this);
    }
}
