package com.loveLetter.Cards;

import com.loveLetter.Player.PlayVisitor;

public class Countess extends Card{
    public Countess() {
        super("COUNTESS",8);
    }

    public void accept(PlayVisitor visitor){
        visitor.visit(this);
    }
}
