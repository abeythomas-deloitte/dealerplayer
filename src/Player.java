
public class Player extends DealPlayer{
    String email, username;
    int bankRoll;
    String table;

    public Player(String email, String username, int bankRoll, String table) {
        this.email = email;
        this.username = username;
        this.bankRoll = bankRoll;
        this.table = table;
    }

    public int getBankRoll() {
        return bankRoll;
    }

    public int fetchMoney(int bet){
        if(bankRoll - bet >=0){
            bankRoll -= bet;
            return bet;
        } else{
            return 0;
        }
    }

    public void addMoney(int money){
        this.bankRoll += money;
    }

    public String getTable() {
        return table;
    }

    public int getFaceValue(){
        System.out.println("Cards in player's hand:");
        return super.getFaceValue();
    }
}
