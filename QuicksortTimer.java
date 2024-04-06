public class QuicksortTimer {
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
     * Sorts card pile using quicksort algorithm without recorder
     * 
     * @param unsorted unsorted cardpile
     * @return sorted card pile
     */
    public static CardPile sort(CardPile unsorted) {

        // Now move cards from one list to another,
        // and use the recorder to record the steps along the way.
        // In a real implementation, you would actually sort the list.
        // Here, we just move them in the same order they came in

        // Here is the result list you will be creating
        if (unsorted.size() == 0 || unsorted.size() == 1) {
            return unsorted;
        } else {

            // Here are the two partitions you will be creating
            CardPile smaller = new CardPile();
            CardPile bigger = new CardPile();

            // ***********************************************************
            // Here is where you'll do the partition part of Quicksort:
            // - Choose a pivot
            // - Partition the unsorted list into two piles
            // ***********************************************************
            Card pivot = unsorted.removeFirst(); // edit this!

            for (int i = 0; i < unsorted.size(); i++) {
                if (unsorted.get(i).compareTo(pivot) < 0) {
                    smaller.add(unsorted.get(i));
                } else {
                    bigger.add(unsorted.get(i));
                }
            }

            // This will hold the assembled result
            CardPile result = new CardPile();

            // ***********************************************************
            // Here is where you'll do the remaining work of Quicksort:
            // - Make recursive calls on the partitions
            // - Assemble the sorted results into a single pile
            // ***********************************************************
            result.append(sort(smaller));
            result.add(pivot);
            result.append(sort(bigger));
            // record the sorted result

            // return the sorted result here
            return result;
        }
    }

}
