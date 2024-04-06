import java.util.Collections;

public class InsertionSort {

  /**
   * Sorts cards using insertion sort algorithm
   * 
   * @param unsorted unsorted card pile
   * @param record   keeping record of sorting process
   * @return CardPile of sorted cards
   */
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

    int insertAfter; // index tracker of card will be inserted after
    boolean inBetween; // keeps track if card can be inserted inbetween two cards
    while (!unsorted.isEmpty()) {

      for (int i = 0; i < unsorted.size(); i++) {
        insertAfter = 0;
        inBetween = false;
        Card c = unsorted.remove(0);
        for (int j = 0; j < sorted.size(); j++) {
          if (c.compareTo(sorted.get(j)) > 0) {
            insertAfter = j;
            inBetween = true;
          }
        }

        // add card to sorted pile
        if (sorted.isEmpty()) {
          sorted.add(c);
        } else {
          // if card can be inserted between two card, then insert after. otherwise insert
          // in front
          if (inBetween) {
            sorted.insertAfter(c, sorted.get(insertAfter));
          } else {
            sorted.insertBefore(c, sorted.get(0));
          }
        }

        record.next(); // tell it this is a new step
        record.add(sorted); // the allegedly sorted pile
        record.add(unsorted); // the unsorted pile
      }
    }

    // return the sorted result here
    return sorted;

  }

  // running algorithm
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
    cards = InsertionSort.sort(cards, recorder);

    // We can print out the (un)sorted result:
    System.out.println(cards);

    // make window appear showing the record
    recorder.display("Card Sort Demo: Insertion Sort");
  }
}
