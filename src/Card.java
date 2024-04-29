public class Card {

    private final Ranks rank;
    private final Suits suit;
    private boolean isFaceCard = false;

    public Card(Ranks rank, Suits suit){
        this.rank = rank;
        this.suit = suit;
        if (this.getRank() == Ranks.JACK || this.getRank() == Ranks.QUEEN || this.getRank() == Ranks.KING) {
            isFaceCard = true;
        }
    }

    public int getValue(){
        return switch (this.rank){
            case ACE   ->   1;
            case TWO   ->   2;
            case THREE ->   3;
            case FOUR  ->   4;
            case FIVE  ->   5;
            case SIX   ->   6;
            case SEVEN ->   7;
            case EIGHT ->   8;
            case NINE  ->   9;
            case TEN   ->  10;
            default    ->   0;
        };
    }

    public Ranks getRank() {
        return rank;
    }

    public Suits getSuit() {
        return suit;
    }

    public static Card findCard(String name) {
        Deck searchDeck = new Deck();
        searchDeck.create52Cards();
        for (Card card : searchDeck) {
            if (card.toString().equalsIgnoreCase(name)){
                return card;
            }
        }
        return null;
    }

    public boolean getIsFaceCard(){
        return isFaceCard;
    }

    @Override
    public String toString(){
        return rank + "_" + suit;
    }

}
