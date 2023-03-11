package Player;

import Cards.*;
import Game.*;
import Utils.CardUtils;

import java.util.List;

public class PlayVisitor {
    private final Player activePlayer;
    private final Deck deck;
    private final List<Player> opponents;

    public PlayVisitor(Player activePlayer, Deck deck, List<Player> opponents) {
        this.activePlayer = activePlayer;
        this.deck = deck;
        this.opponents = opponents;
    }

    public void visit(Spy spy)  {
        System.out.println(this.activePlayer + " joue l'espionne");
    }

    public void visit(Guard guard)  {
        System.out.println(this.activePlayer + " joue le garde");
        Player chosenPlayer = this.getChosenPlayer();
        int chosenNumber = this.activePlayer.chooseTargetCardIndex();
        System.out.println(chosenPlayer + ", " + "tu es " + CardUtils.cardDatabase.get(chosenNumber));
        Card cardChosen = chosenPlayer.getCards().get(0);
        if(chosenNumber == cardChosen.getValue()){
            System.out.println(chosenPlayer + " est éliminé");
            chosenPlayer.setLost(true);
        }
    }

    public void visit(Priest priest)  {
        System.out.println(this.activePlayer + " joue le prêtre");
        Player chosenPlayer = this.getChosenPlayer();
        System.out.println(chosenPlayer + " a la carte " + chosenPlayer.getCards().get(0));
    }

    public void visit(Baron baron)  {
        System.out.println(this.activePlayer + " joue le baron");
        Player chosenPlayer = this.getChosenPlayer();
        if(chosenPlayer.getLastCardDiscarded() instanceof Servant){
            System.out.println("Il ne se passe rien ...");
            return;
        }
        System.out.println(this.activePlayer + " baronne " + chosenPlayer);
        if(this.activePlayer.getCards().get(0).getValue() == chosenPlayer.getCards().get(0).getValue()){
            System.out.println("Il ne se passe rien ...");
        }
        else if(this.activePlayer.getCards().get(0).getValue() < chosenPlayer.getCards().get(0).getValue()){
            this.activePlayer.setLost(true);
            System.out.println(this.activePlayer + " a perdu !");
        }
        else if(this.activePlayer.getCards().get(0).getValue() > chosenPlayer.getCards().get(0).getValue()){
            chosenPlayer.setLost(true);
            System.out.println(chosenPlayer + " a perdu !");
        }
    }

    public void visit(Servant servant)  {
        System.out.println(this.activePlayer + " joue la servante");
    }

    public void visit(Prince prince) {
        System.out.println(this.activePlayer + " joue le prince");
        if (this.activePlayer.getCards().get(0) instanceof Countess) {
            this.activePlayer.setLost(true);
            System.out.println(this.activePlayer + " a perdu !");
            return;
        }

        Player chosenPlayer = this.getChosenPlayer();

        if(chosenPlayer.getLastCardDiscarded() instanceof Servant){
            System.out.println("Il ne se passe rien ...");
            return;
        }

        System.out.println(this.activePlayer + " défausse la carte de " + chosenPlayer);
        Card discardedCard = chosenPlayer.getCards().get(0);
        chosenPlayer.removeCard(discardedCard);
        System.out.println(chosenPlayer + " avait la carte " + discardedCard);

        if (discardedCard instanceof Princess) {
            chosenPlayer.setLost(true);
        } else if (!deck.getCards().isEmpty()) {
            chosenPlayer.pickCard(deck.draw());
        }
    }

    public void visit(Chancellor chancellor)  {
        System.out.println(this.activePlayer + " joue le chancelier");
        if(deck.getCards().isEmpty()){
            return;
        }
        this.activePlayer.pickCard(deck.draw());
        this.activePlayer.pickCard(deck.draw());
        this.activePlayer.putCardUnderThePick(deck.getCards());
    }

    public void visit(King king)  {
        System.out.println(this.activePlayer + " joue le roi");
        if (this.activePlayer.getCards().get(0) instanceof Countess) {
            this.activePlayer.setLost(true);
            System.out.println(this.activePlayer + " a perdu !");
            return;
        }
        Player chosenPlayer = this.getChosenPlayer();
        if(chosenPlayer.getLastCardDiscarded() instanceof Servant){
            System.out.println("Il ne se passe rien ...");
            return;
        }
        System.out.println(this.activePlayer + " échange avec " + chosenPlayer);
        Card activeCard = this.activePlayer.getCards().get(0);
        Card chosenPlayerCard = chosenPlayer.getCards().get(0);
        this.activePlayer.getCards().clear();
        this.activePlayer.addCard(chosenPlayerCard);
        chosenPlayer.getCards().clear();
        chosenPlayer.addCard(activeCard);
    }

    public void visit(Countess countess)  {
        System.out.println(this.activePlayer + " joue la comtesse");
    }

    public void visit(Princess princess)  {
        System.out.println(this.activePlayer + " joue la princesse");
        this.activePlayer.setLost(true);
        System.out.println(this.activePlayer + " a perdu !");
    }

    private Player getChosenPlayer(){
        int chosenIndex = this.activePlayer.chooseTargetIndex(opponents);
        return opponents.get(chosenIndex);
    }
}
