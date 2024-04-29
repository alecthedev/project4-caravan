import java.util.Collections;
import java.util.LinkedList;

public class Deck extends LinkedList<Card> {

    public void create52Cards() {
        for(Suits suit : Suits.values()) {
            for (Ranks rank : Ranks.values()){
                this.add(new Card(rank, suit));
            }
        }
    }

    public void clear() {
        for (Card card : this) {remove(card);}
    }

    public void shuffle() {
        Collections.shuffle(this);
    }

    public Card drawCard() {
        return this.pop();
    }

    public void printCards(){
        for (Card card : this){
            System.out.print(card + " ");
        }
    }

    @Override
    public boolean contains(Object o){
        for (Card card : this) {
            if (card.toString().equalsIgnoreCase(o.toString())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean remove(Object o){
        for (Card card : this) {
            if (card.toString().equalsIgnoreCase(o.toString())) {
                super.remove(card);
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString(){
        StringBuilder deckString = new StringBuilder();
        for (Card card : this) {
            deckString.append(card);
            deckString.append(" ");
        }
        return deckString.toString();
    }

}
