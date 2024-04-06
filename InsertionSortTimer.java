public class InsertionSortTimer {
    public static void main(String args[]) {
        if (args.length < 1) {
            System.err.println("Please specify how many cards to sort!");
        } else {
            Card[] deck = Card.newDeck(true);
            CardPile cards = new CardPile();
            cards = cards.split(cards.get(39));

            for (int i = 0; i < Integer.parseInt(args[0]); i++) {
                cards.add(deck[(int) (52 * Math.random())]);
            }

            sort(cards);

        }
    }

    /**
     * Sorts card pile using insertion algorithm without recorder
     * 
     * @param unsorted unsorted card pile
     * @return sorted cardpile
     */
    public static CardPile sort(CardPile unsorted) {

        // Now move cards from one list to another,
        // and use the recorder to record the steps along the way.
        // In a real implementation, you would actually sort the list.
        // Here, we just move them in the same order they came in

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
                Card c = unsorted.remove(0);
                for (int j = 0; j < sorted.size(); j++) {
                    if (c.compareTo(sorted.get(j)) > 0) {
                        insertAfter = j;
                        inBetween = true;
                    }
                }
                if (sorted.isEmpty()) {
                    sorted.add(c);
                } else {
                    if (inBetween) {
                        sorted.insertAfter(c, sorted.get(insertAfter));
                    } else {
                        sorted.insertBefore(c, sorted.get(0));
                    }
                }

            }
        }

        // return the sorted result here
        return sorted;
    }

}
