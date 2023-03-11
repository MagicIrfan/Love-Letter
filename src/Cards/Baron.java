package Cards;

import Player.PlayVisitor;

public class Baron extends Card{
    public Baron() {
        super("BARON", 3);
    }

    public void accept(PlayVisitor visitor){
        visitor.visit(this);
    }
}
