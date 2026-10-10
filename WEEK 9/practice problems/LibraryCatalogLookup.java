public class LibraryCatalogLookup {

    // catalog is sorted by ISBN in ascending order; each row is {isbn, title}
    static String findBook(String[][] catalog, String targetIsbn) {
        int low = 0;
        int high = catalog.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int cmp = catalog[mid][0].compareTo(targetIsbn);

            if (cmp == 0) {
                return catalog[mid][1];
            } else if (cmp < 0) {
                low = mid + 1;   // target is in the right half
            } else {
                high = mid - 1;  // target is in the left half
            }
        }
        return "Not Found";
    }

    public static void main(String[] args) {
        String[][] catalog = {
                {"0001112223", "Introduction to Algebra"},
                {"0002223334", "Beginning Python"},
                {"0003334445", "Classic Mythology"},
                {"0004445556", "Data and Society"},
                {"0005556667", "European History"}
        };

        System.out.println(findBook(catalog, "0003334445"));
        System.out.println(findBook(catalog, "0009998887"));
    }

    /*
     * Complexity (binary search):
     *   Time  : O(log n) - the search range is halved on every step.
     *   Space : O(1)     - only a few index variables are used (iterative version).
     */
}
