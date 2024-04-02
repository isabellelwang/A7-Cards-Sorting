import java.util.Collections;

public class InsertionSort {

  public static CardPile sort(CardPile unsorted, SortRecorder record) {

    // register the starting configuration with the recorder
    record.add(unsorted);

    // Here is the result list you will be creating
    CardPile sorted = new CardPile();

    // ***********************************************************
    // Here is where you'll do the "work" of InsertionSort:
    // - Use sorted to store the "sorted portion"
    // - Don't forget to register the new state with the
    // recorder after each card is transferred:

    // ***********************************************************

    int insertAfter;
    boolean inBetween; 
    while (!unsorted.isEmpty()) {

      for (int i = 0; i < unsorted.size(); i++) {
        insertAfter = 0;
        inBetween = false; 
        System.out.println("unsorted: " + unsorted);
        System.out.println("sorted: " + sorted);
        Card c = unsorted.remove(0);
        for (int j = 0; j < sorted.size(); j++) {
          System.out.println("first card: " + c);
          System.out.println("comparing to: " + sorted.get(j));
          if (c.compareTo(sorted.get(j)) > 0) {
            System.out.println("found");
            insertAfter = j;
            inBetween = true; 
          }
        }
        if (sorted.isEmpty()) {
          sorted.add(c);
        } else if (sorted.size() == 1) {
          if (c.compareTo(sorted.get(0)) < 0) {
            sorted.insertBefore(c, sorted.get(0));
          } else {
            sorted.insertAfter(c, sorted.get(0));
          }
        } else {
          if(inBetween) {
            sorted.insertAfter(c, sorted.get(insertAfter));
          }
          else {
            sorted.insertBefore(c, sorted.get(0)); 
          }
        }
        //
        System.out.println(sorted);
        record.next(); // tell it this is a new step
        record.add(sorted); // the allegedly sorted pile
        record.add(unsorted); // the unsorted pile
      }
    }

    // return the sorted result here
    return sorted;

  }

  public static void main(String args[]) {

    // set up a class to record and display the sorting results
    SortRecorder recorder = new SortRecorder();

    // set up the deck of cards
    Card.loadImages(recorder);
    CardPile cards = new CardPile(Card.newDeck(true), 2, 2);

    // for debugging purposes, uncomment this to
    // work with a smaller number of cards:
    //cards = cards.split(cards.get(39));

    // mix up the cards
    Collections.shuffle(cards);

    // if you want to sort in array form, use:
    Card[] card_arr = cards.toArray(new Card[0]);

    // in your program, this would be a call to a real sorting algorithm
    cards = InsertionSort.sort(cards, recorder);

    // We can print out the (un)sorted result:
    System.out.println(cards);

    // make window appear showing the record
    recorder.display("Card Sort Demo: Insertion Sort");
  }
}
