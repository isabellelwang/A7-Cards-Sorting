import java.util.ArrayDeque;
import java.util.Collections;

public class MergeSortTimer {

    public static void main(String args[]) {

        if (args.length < 1) {
            System.err.println("Please specify how many cards to sort!");
        } else {
            Card[] deck = Card.newDeck(true);
            CardPile cards = new CardPile();

            for (int i = 0; i < Integer.parseInt(args[0]); i++) {
                cards.add(deck[(int) (52 * Math.random())]);
            }

            sort(cards);
        }
    }

    public static CardPile merge(CardPile pile1, CardPile pile2) {
        CardPile sorted = new CardPile();

        while (!pile1.isEmpty() && !pile2.isEmpty()) {
            if (pile1.getFirst().compareTo(pile2.getFirst()) < 0) {
                sorted.add(pile1.removeFirst());
            } else {
                sorted.add(pile2.removeFirst());
            }
        }

        if (!pile1.isEmpty()) {
            sorted.append(pile1);
        } else if (!pile2.isEmpty()) {
            sorted.append(pile2);
        }
        return sorted;
    }

    public static CardPile sort(CardPile unsorted) {

        ArrayDeque<CardPile> queue = new ArrayDeque<CardPile>();

        // ***********************************************************
        // Here is where you'll do the "work" of MergeSort:
        // - Use queue to store the intermediate piles
        // - Don't forget to register the new state with the
        // recorder after each merge step:
        // record.next(); // tell it this is a new step
        // for (CardPile pile: queue) { // add all piles
        // record.add(pile);
        // }
        // ***********************************************************

        while (!unsorted.isEmpty()) {
            Card c = unsorted.removeFirst();
            CardPile pile = new CardPile();
            pile.add(c);
            queue.addFirst(pile);
        }

        while (queue.size() > 1) {
            CardPile p1CardPile = queue.removeFirst();
            CardPile p2CardPile = queue.removeFirst();
            queue.addLast(merge(p1CardPile, p2CardPile));
        }

        // return the sorted result here
        return queue.remove();
    }
}
