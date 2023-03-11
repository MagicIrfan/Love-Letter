package Cards;

import Player.PlayVisitor;

public class Guard extends Card {
    public Guard(){
        super("GUARD",1);
    }

    public void accept(PlayVisitor visitor){
        visitor.visit(this);
    }
}
