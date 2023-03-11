package Cards;

import Player.PlayVisitor;

public class Priest extends Card{
    public Priest(){
        super("PRIEST",2);
    }

    public void accept(PlayVisitor visitor){
        visitor.visit(this);
    }
}
