public class SelectionSortTimer {
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

    /** This method actually doesn't sort at all */
    public static CardPile sort(CardPile unsorted) {

        // Now move cards from one list to another,
        // and use the recorder to record the steps along the way.
        // In a real implementation, you would actually sort the list.
        // Here, we just move them in the same order they came in

        // Here is the result list you will be creating
        // register the starting configuration with the recorder

        // Here is the result list you will be creating
        CardPile sorted = new CardPile();

        // ***********************************************************
        // Here is where you'll do the "work" of SelectionSort:
        // - Use sorted to store the "sorted portion"
        // - Don't forget to register the new state with the
        // recorder after each card is transferred:
        // record.next(); // tell it this is a new step
        // record.add(sorted); // the sorted pile
        // record.add(unsorted); // the unsorted pile
        // ***********************************************************

        int minIndex;
        while (!unsorted.isEmpty()) {
            minIndex = 0;
            for (int i = 1; i < unsorted.size(); i++) {

                if (unsorted.get(i).compareTo(unsorted.get(minIndex)) < 0) {
                    minIndex = i;
                }
            }
            sorted.add(unsorted.remove(minIndex));
            System.out.println(sorted);

        }

        // return the sorted result here
        return sorted;
    }

}
