package Cards;

import Player.PlayVisitor;

public class King extends Card{
    public King() {
        super("KING",7);
    }

    public void accept(PlayVisitor visitor){
        visitor.visit(this);
    }
}
