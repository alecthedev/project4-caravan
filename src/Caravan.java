public class Caravan {
    private final String name;
    private Suits player1Suit;
    private Suits player2Suit;
    private boolean inWinState;

    private int player1Sequence;
    private int player2Sequence;

    private boolean isPlayer1Owned;
    private boolean isPlayer2Owned;

    private int player1BidTotal;
    private int player2BidTotal;

    private Node player1CardRoot;
    private Node player2CardRoot;

    public Caravan(String name) {
        this.name = name;
        isPlayer1Owned = false;
        isPlayer2Owned = false;
        player1Sequence = 0;
        player2Sequence = 0;
    }

    // --- player 1 specific methods ---

    public Node getPlayer1CardRoot() {
        return player1CardRoot;
    }

    public int getPlayer1BidTotal() {
        return player1BidTotal;
    }

    public boolean getIsPlayer1Owned() {
        return isPlayer1Owned;
    }

    // --- player 2 specific methods ---

    public Node getPlayer2CardRoot() {
        return player2CardRoot;
    }

    public int getPlayer2BidTotal() {
        return player2BidTotal;
    }

    public boolean getIsPlayer2Owned() {
        return isPlayer2Owned;
    }

    // --- general methods ---

    public Suits getSuit(Player currentPlayer) {
        if (currentPlayer.getRefID() == 1) {
            return player1Suit;
        }
        return player2Suit;
    }

    public void setSuit(Player currentPlayer, Suits suit) {
        if (currentPlayer.getRefID() == 1) {
            player1Suit = suit;
        } else {
            player2Suit = suit;
        }
    }

    public int getSequence(Player currentPlayer) {
        if (currentPlayer.getRefID() == 1) {
            return player1Sequence;
        }
        return player2Sequence;
    }

    public void setSequence(Player currentPlayer, int sequence) {
        if (currentPlayer.getRefID() == 1) {
            player1Sequence = sequence;
        } else {
            player2Sequence = sequence;
        }
    }

    public void reverseSequence(Player currentPlayer){
        setSequence(currentPlayer, getSequence(currentPlayer) * -1);
    }

    public int getBidTotal(Player currentPlayer) {
        if (currentPlayer.getRefID() == 1) {
            return player1BidTotal;
        }
        return player2BidTotal;
    }

    public void setBidTotal(Player currentPlayer, int newTotal) {
        if (currentPlayer.getRefID() == 1) {
            player1BidTotal = newTotal;
        } else {
            player2BidTotal = newTotal;
        }
    }

    public Node getCardRoot(Player currentPlayer) {
        if (currentPlayer.getRefID() == 1) {
            return player1CardRoot;
        }
        return player2CardRoot;
    }

    public void setCardRoot(Player currentPlayer, Node cardRoot) {
        if (currentPlayer.getRefID() == (1)) {
            player1CardRoot = cardRoot;
        } else {
            player2CardRoot = cardRoot;
        }
    }

    public boolean isInWinState() {
        return inWinState;
    }

    public void setInWinState(boolean state) {
        this.inWinState = state;
    }

    public void checkOwnedConditions() {
        isPlayer1Owned = getPlayer1BidTotal() >= 21 && getPlayer1BidTotal() <= 26;
        isPlayer2Owned = getPlayer2BidTotal() >= 21 && getPlayer2BidTotal() <= 26;

        setInWinState((isPlayer1Owned && !isPlayer2Owned) || (isPlayer2Owned && !isPlayer1Owned));
    }

    @Override
    public String toString() {
        return name;
    }

}
