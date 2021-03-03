import java.util.ArrayList;

public class DealPlayer {
    ArrayList<Card> cardsInHand = new ArrayList<>();
    public void addCardToHand(Card card, boolean isExposed){
        if(isExposed)
            card.revealCard();
        cardsInHand.add(card);
    }

    public void revealAllCards(){
        cardsInHand.forEach(Card::revealCard);
    }

    /* getting the face value and handling value for ace*/
    public int getFaceValue(){
        cardsInHand.stream().filter(Card::checkIfExposed).forEach(card -> System.out.println(card.getRank() + " of "+card.getSuite()));
        int faceValue = cardsInHand.stream().filter(Card::checkIfExposed).map(Card::getFaceValue).reduce(0, Integer::sum);
        boolean aceExistInHand = cardsInHand.stream().filter(Card::checkIfExposed).anyMatch(card -> card.getRank() == Ranks.Ace);
        if(aceExistInHand){
            if(faceValue > 21)
                cardsInHand.stream().filter(Card::checkIfExposed).forEach(card -> {if(card.getRank() == Ranks.Ace && card.getFaceValue() == 11) card.changeFaceValue(1); });
            else if(faceValue + 10 < 21)
                cardsInHand.stream().filter(Card::checkIfExposed).forEach(card -> {
                    if(card.getRank() == Ranks.Ace && card.getFaceValue() == 1)
                        card.changeFaceValue(11);
                });
            faceValue = cardsInHand.stream().filter(Card::checkIfExposed).map(Card::getFaceValue).reduce(0, Integer::sum);
        }
        return faceValue;
    }

    public void emptyCards(){
        this.cardsInHand.clear();
    }
}
