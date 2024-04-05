import java.util.ArrayDeque;
import java.util.Collections;

public class MergeSort {

  public static CardPile merge(ArrayDeque<CardPile> unsorted) {
    CardPile sorted = new CardPile();

    if (unsorted.size() == 1) {
      System.out.println("hello");
      return sorted;
    } else {
      System.out.println("be");
      CardPile pile1 = unsorted.removeFirst();
      CardPile pile2 = unsorted.removeFirst();
      System.out.println("Pile1: " + pile1);
      System.out.println("Pile2:" + pile2);

      int numRounds = 0;
      // CardPile largerPile;
      // CardPile smallerPile;

      // if (pile1.getFirst().compareTo(pile2.getFirst()) < 0) {
      // numRounds = pile1.size();
      // smallerPile = pile1;
      // largerPile = pile2;
      // } else {
      // numRounds = pile2.size();
      // largerPile = pile1;
      // smallerPile = pile2;
      // }

      // // need two for loops
      // for (int j = 0; j < smallerPile.size(); j++) {
      // for (int i = 0; i < largerPile.size(); i++) {
      // if (smallerPile.get(j).compareTo(largerPile.get(i)) < 0) {
      // sorted.addLast(smallerPile.remove(i));
      // // } else if (largerPile.get(i).compareTo(smallerPile.get(j)) < 0) {
      // // sorted.addLast(largerPile.remove(j));
      // }
      // System.out.println("sorted:" + sorted);
      // }
      // }

      for (int i = 0; i < pile1.size(); i++) {
        for (int j = 0; j < pile2.size(); j++) {

          if (!pile1.peekFirst().equals(null) || !pile2.peekFirst().equals(null)) {
            if (pile1.peekFirst().compareTo(pile2.peekFirst()) < 0) {
              sorted.addLast(pile1.removeFirst());
              System.out.println("p1" + pile1);
            } else if (pile2.peekFirst().compareTo(pile1.peekFirst()) < 0) {
              sorted.addLast(pile2.removeFirst());
              System.out.println("p2:" + pile2);
            }
            System.out.println("sorted:" + sorted);
          }
        }
      }
      System.out.println("After p1: " + pile1);
      System.out.println("After p2" + pile2);

      if (!pile1.isEmpty() && !pile2.isEmpty()) {
        if (pile1.getFirst().compareTo(pile2.getFirst()) < 0) {
          sorted.append(pile1);
          sorted.append(pile2);
        } else {
          sorted.append(pile2);
          sorted.append(pile1);
        }
        // for (int i = 0; i < pile1.size(); i++) {
        // for (int j = 0; j < pile2.size(); j++) {
        // if (!pile1.peekFirst().equals(null) || !pile2.peekFirst().equals(null)) {
        // if (pile1.peekFirst().compareTo(pile2.peekFirst()) < 0) {
        // sorted.addLast(pile1.removeFirst());
        // System.out.println("p1" + pile1);
        // } else if (pile2.peekFirst().compareTo(pile1.peekFirst()) < 0) {
        // sorted.addLast(pile2.removeFirst());
        // System.out.println("p2:" + pile2);
        // }
        // System.out.println("sorted:" + sorted);
        // }
        // }
      } else {
        if (!pile1.isEmpty()) {
          System.out.println("appending piel1");
          sorted.append(pile1);
        } else if (!pile2.isEmpty()) {
          System.out.println("appending piel2");
          sorted.append(pile2);
        }
      }

      System.out.println("sorted2:" + sorted);
      unsorted.addLast(sorted);
      System.out.println("unsorted:" + unsorted);

      return MergeSort.merge(unsorted);
    }
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

    Card pile[] = new Card[1];
    for (Card c : unsorted) {
      pile[0] = c;
      queue.add(new CardPile(pile, 0, 0));
    }

    System.out.println("original queue: " + queue);

    CardPile sorted = MergeSort.merge(queue);
    // Card mergeTwoCards[] = new Card[2];

    // int queueSize = queue.size();
    // int numRounds = queue.size() / 2;

    // while (queue.size() > 1) {

    // }

    // while (queue.size() > 1) {
    // for (int i = 0; i < numRounds; i++) {
    // System.out.println(numRounds);
    // System.out.println("merging");

    // // merge singles into pairs
    // mergeTwoCards[0] = queue.removeFirst().locateCard(0, 0);
    // System.out.println("First card:" + mergeTwoCards[0]);
    // mergeTwoCards[1] = queue.removeFirst().locateCard(0, 0);
    // System.out.println("Second card:" + mergeTwoCards[1]);
    // queue.addLast(new CardPile(mergeTwoCards, 0, 0));

    // // if odd number of cards, remove first single and move to back
    // if (queueSize % 2 == 1 && i == (numRounds - 1)) {
    // System.out.println("yes");
    // System.out.println("last indexs: " + i);
    // queue.addLast(queue.removeFirst());
    // }

    // System.out.println(queue);
    // System.out.println(queue.size());
    // }
    // }

    queue.clear();
    queue.addFirst(sorted);
    System.out.println(queue);

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
    cards = cards.split(cards.get(39));

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
