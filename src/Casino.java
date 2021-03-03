import java.util.HashMap;
import java.util.Scanner;

public class Casino {
    public static void main(String[] args) {
        Player player = new Player("tony.stark@marvel.com", "Iron Man", 1500, "Earth");
        gamePlay(player);
    }

    public static void gamePlay(Player player){
        int gameRunning = 1;
        int currentBet;
        String playerChoice;
        Deck deck = new Deck();
        HashMap<String, Integer> tables = getTables();
        Scanner in = new Scanner(System.in);
        Dealer dealer = new Dealer();

        while(gameRunning == 1) {
            playerChoice="HIT";
            currentBet = player.fetchMoney(tables.get(player.getTable()));
            if (currentBet > 0) {
                deck.shuffle();
                player.addCardToHand(deck.removeCard(), true);
                dealer.addCardToHand(deck.removeCard(), false);
                player.addCardToHand(deck.removeCard(), true);
                dealer.addCardToHand(deck.removeCard(), true);
                gameRunning = checkGameStatus(player, dealer, true, currentBet);
                while (gameRunning == 1 && (playerChoice.matches("HIT") || playerChoice.matches("STAND"))) {
                    System.out.println("Do player want to call a HIT or STAND?");
                    playerChoice = in.nextLine();
                    if (playerChoice.matches("HIT")) {
                        System.out.println("Player chooses to HIT");
                        player.addCardToHand(deck.removeCard(), true);
                        dealer.addCardToHand(deck.removeCard(), true);
                        gameRunning = checkGameStatus(player, dealer, true, currentBet);
                    } else {
                        System.out.println("Player chooses to STAND");
                        dealer.revealAllCards();
                        while(dealer.getFaceValue() < 17){
                            dealer.addCardToHand(deck.removeCard(), true);
                        }
                        gameRunning = checkGameStatus(player, dealer, false, currentBet);
                    }
                }
            } else {
                System.out.println("Sorry no money left in your bankroll to continue.");
            }
            System.out.println("\n\nPlayers updated bankroll: "+ player.getBankRoll());
            player.emptyCards();
            dealer.emptyCards();
            deck.resetDeck();
            System.out.println("To Continue playing press 1");
            gameRunning = in.nextLine().matches("1") ? 1 : 0;
        }
    }

    public static int checkGameStatus(Player player, Dealer dealer, boolean isHitCalled, int currentBet){
        int playerFaceValue = player.getFaceValue();
        int dealerFaceValue = dealer.getFaceValue();
        System.out.println("Player current facevalue = "+ playerFaceValue);
        System.out.println("Dealer current facevalue = "+ dealerFaceValue);
        if(isHitCalled){
            if(playerFaceValue > 21){
                System.out.println("Its a Bust, and the dealer wins");
                return 0;
            }
            return 1;
        }
        if(playerFaceValue > 21 && dealerFaceValue <= 21){
            System.out.println("Its a Bust, and the dealer wins");
        } else if(dealerFaceValue > 21){
            System.out.println("Its a Bust, and the player wins");
            player.addMoney(currentBet * 2);
        } else if(playerFaceValue == 21 && dealerFaceValue != 21){
            System.out.println("Its a woo, and the player wins");
            player.addMoney(currentBet * 2);
        } else if(dealerFaceValue == 21){
            System.out.println("Its a woo, and the dealer wins");
        } else if(dealerFaceValue > playerFaceValue){
            System.out.println("Dealer reaches a hand value greater than player, and the dealer wins");
        } else if(dealerFaceValue < playerFaceValue){
            System.out.println("Player reaches a hand value greater than dealer, and the player wins");
            player.addMoney(currentBet * 2);
        } else {
            System.out.println("Its a draw");
            player.addMoney(currentBet);
        }
        return 0;
    }

    public static HashMap<String, Integer> getTables(){
        HashMap<String, Integer> tables = new HashMap<>();
        tables.put("Ego", 100);
        tables.put("Earth", 200);
        tables.put("Asgard", 500);
        tables.put("Vormir", 1000);
        tables.put("Titan", 2000);
        return tables;
    }
}
