import java.util.ArrayDeque;
import java.util.Collections;

public class MergeSort {
  

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

  public static CardPile sort(CardPile unsorted, SortRecorder record) {

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

      record.next(); // tell it this is a new step
      record.add(unsorted); // the unsorted pile
      for (CardPile p : queue) { // add all piles
        record.add(p);
      }
    }

    while (queue.size() > 1) {
      CardPile p1CardPile = queue.removeFirst();
      CardPile p2CardPile = queue.removeFirst();
      queue.addLast(merge(p1CardPile, p2CardPile));

      record.next(); // tell it this is a new step
      for (CardPile p : queue) { // add all piles
        record.add(p);
      }
    }

    // System.out.println(queue);
    // return the sorted result here
    return queue.remove();
  }

  public static void main(String args[]) {

    // set up a class to record and display the sorting results
    SortRecorder recorder = new SortRecorder();

    // set up the deck of cards
    Card.loadImages(recorder);
    CardPile cards = new CardPile(Card.newDeck(true), 2, 2);

    // for debugging purposes, uncomment this to
    // work with a smaller number of cards:
    // cards = cards.split(cards.get(39));

    // mix up the cards
    Collections.shuffle(cards);

    // if you want to sort in array form, use:
    Card[] card_arr = cards.toArray(new Card[0]);

    // in your program, this would be a call to a real sorting algorithm
    cards = MergeSort.sort(cards, recorder);

    // We can print out the (un)sorted result:
    System.out.println(cards);

    // make window appear showing the record
    recorder.display("Card Sort Demo: Merge Sort");
  }
}
