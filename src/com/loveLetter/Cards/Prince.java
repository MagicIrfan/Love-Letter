package com.loveLetter.Cards;

import com.loveLetter.Player.PlayVisitor;

public class Prince extends Card{
    public Prince() {
        super("PRINCE", 5);
    }

    public void accept(PlayVisitor visitor){
        visitor.visit(this);
    }
}
