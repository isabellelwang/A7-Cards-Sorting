import java.util.ArrayDeque;
import java.util.Collections;

public class MergeSort {

  public static CardPile merge(ArrayDeque<CardPile> unsorted) {
    CardPile sorted = new CardPile();

    if (sorted.size() == 1) {
      System.out.println("hello");
      return sorted;
    } else {
      System.out.println("be");
      CardPile pile1 = unsorted.removeFirst();
      CardPile pile2 = unsorted.removeFirst();
      System.out.println(pile1);
      System.out.println(pile2);

      int numRounds = 0; 
      CardPile largerPile; 
      if(pile1.getFirst().compareTo(pile2.getFirst()) < 0) {
        numRounds = pile1.size(); 
        largerPile = pile2; 
      }
      else {
        numRounds = pile2.size(); 
        largerPile = pile1; 
      }

      for(int i = 0; i < numRounds; i++) {
        if(pile1.get(i).compareTo(pile2.get(i)) < 0) {
          sorted.addLast(pile1.remove(i)); 
        }
        else if(pile2.get(i).compareTo(pile1.get(i)) < 0) {
          sorted.addLast(pile2.remove(i)); 
        }
        System.out.println("sorted:" + sorted);
      }
      sorted.append(largerPile); 
      System.out.println("sorted2:" + sorted);
      unsorted.addLast(sorted); 
      System.out.println("unsorted:" + unsorted);

      // for (int i = 0; i < pile1.size(); i++) {
      //   for(int j = 0; j < pile2.size(); j++) {
      //     System.out.println("inside");
      //     if (pile1.get(i).compareTo(pile2.get(j)) < 0) {
      //       System.out.println("comparing");
      //       if(!sorted.contains((pile1.get(i)))) {
      //         System.out.println("not in");
      //         sorted.addLast(pile1.get(i));
      //         System.out.println("sorted: " + sorted);
      //       }
      //     }
      //   }
       
      // }
  
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
    //Card mergeTwoCards[] = new Card[2];

    // int queueSize = queue.size();
    // int numRounds = queue.size() / 2;

    // while (queue.size() > 1) {

    // }

    // while (queue.size() > 1) {
    //   for (int i = 0; i < numRounds; i++) {
    //     System.out.println(numRounds);
    //     System.out.println("merging");

    //     // merge singles into pairs
    //     mergeTwoCards[0] = queue.removeFirst().locateCard(0, 0);
    //     System.out.println("First card:" + mergeTwoCards[0]);
    //     mergeTwoCards[1] = queue.removeFirst().locateCard(0, 0);
    //     System.out.println("Second card:" + mergeTwoCards[1]);
    //     queue.addLast(new CardPile(mergeTwoCards, 0, 0));

    //     // if odd number of cards, remove first single and move to back
    //     if (queueSize % 2 == 1 && i == (numRounds - 1)) {
    //       System.out.println("yes");
    //       System.out.println("last indexs: " + i);
    //       queue.addLast(queue.removeFirst());
    //     }

    //     System.out.println(queue);
    //     System.out.println(queue.size());
    //   }
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
    // Card.loadImages(recorder);
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
    // recorder.display("Card Sort Demo: Merge Sort");
  }
}
