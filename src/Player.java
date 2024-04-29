public class Player {
    final private int refID;
    Deck cardDeck;
    Deck hand;

    public Player(int refID){
        this.refID = refID;
        cardDeck = initDeck();
        hand = initHand();
    }

    public int getRefID() {
        return refID;
    }

    private Deck initDeck(){
        Deck newDeck = new Deck();
        newDeck.create52Cards();
        newDeck.shuffle();
        return newDeck;
    }

    private Deck initHand(){
        Deck newHand = new Deck();
        for (int i = 0; i < 8; i++) {
            newHand.add(cardDeck.drawCard());
        }
        return newHand;
    }

    public void discard(Card card){
        hand.remove(card);
        hand.add(cardDeck.drawCard());
    }

}
