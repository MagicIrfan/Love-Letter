package Cards;

import Player.PlayVisitor;

public abstract class Card {

    private final String name;
    private final int value;

    public Card(String name, int value){
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return this.name;
    }

    public int getValue(){
        return this.value;
    }

    @Override
    public String toString(){
        return this.value + " : " + this.name;
    }

    public abstract void accept(PlayVisitor visitor);
}
