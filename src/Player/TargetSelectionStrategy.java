package Player;

import Cards.Card;

import java.util.List;

public interface TargetSelectionStrategy {
    int selectTargetPlayerIndex(List<Player> players, Player activePlayer);
    int selectTargetCardIndex(Player activePlayer);
    Card chooseCard(Player activePlayer);
    void putCardUnderThePick(Player activePlayer, List<Card> pick);
}
