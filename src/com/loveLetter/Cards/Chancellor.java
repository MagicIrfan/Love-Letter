package com.loveLetter.Cards;

import com.loveLetter.Player.PlayVisitor;

public class Chancellor extends Card{
    public Chancellor() {
        super("CHANCELLOR", 6);
    }

    public void accept(PlayVisitor visitor){
        visitor.visit(this);
    }
}
