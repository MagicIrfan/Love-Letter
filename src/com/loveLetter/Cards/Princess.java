package com.loveLetter.Cards;

import com.loveLetter.Player.PlayVisitor;

public class Princess extends Card{
    public Princess() {
        super("PRINCESS", 9);
    }

    public void accept(PlayVisitor visitor){
        visitor.visit(this);
    }
}
