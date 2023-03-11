package Cards;

import Player.PlayVisitor;

public class Spy extends Card {

    public Spy(){
        super("SPY",0);
    }

    public void accept(PlayVisitor visitor){
        visitor.visit(this);
    }
}
