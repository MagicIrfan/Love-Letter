package Cards;

import Player.PlayVisitor;

public class Servant extends Card{
    public Servant() {
        super("SERVANT",4);
    }

    public void accept(PlayVisitor visitor){
        visitor.visit(this);
    }
}
