import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    ArrayList<Card> cards;

    Deck() {
        cards = getDeck();
    }

    public static ArrayList<Card> getDeck() {
        ArrayList<Card> cards = new ArrayList<>();
        for (Suites suite : Suites.values()) {
            for (Ranks rank : Ranks.values())
                cards.add(new Card(suite, rank, getFaceValue(rank)));
        }
        return cards;
    }

    public void resetDeck() {
        cards.clear();
        cards = getDeck();
    }

    public static int getFaceValue(Ranks rank) {
        switch (rank) {
            case Ace:
                return 1;
            case Two:
                return 2;
            case Three:
                return 3;
            case Four:
                return 4;
            case Five:
                return 5;
            case Six:
                return 6;
            case Seven:
                return 7;
            case Eight:
                return 8;
            case Nine:
                return 9;
            case Ten:
            case Jack:
            case Queen:
            case King:
                return 10;
            default:
                return 0;

        }
    }

    public Card removeCard() {
        Card currentCard = cards.get(0);
        cards.remove(0);
        return currentCard;
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

}
