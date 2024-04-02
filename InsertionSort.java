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
    while (!unsorted.isEmpty()) {
      insertAfter = 0;
      for (int i = 0; i < unsorted.size(); i++) {
        Card c = unsorted.remove(i); 
        for (int j = 0; j < sorted.size(); j++) {
          if (c.compareTo(sorted.get(j)) > 0 && c.compareTo(sorted.get(j + 1)) < 0) {
            insertAfter = j;
          }
        }
        sorted.insertAfter(c, unsorted.get(insertAfter));
      }
    }

    // return the sorted result here
    return sorted;
  }
}
