public class TicketPriceSlotFinder {

    // index of newPrice if present, otherwise the index where it should be inserted
    static int findSlot(int[] prices, int newPrice) {
        int low = 0;
        int high = prices.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (prices[mid] == newPrice) {
                return mid;
            } else if (prices[mid] < newPrice) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        // not found: low sits exactly where the price belongs
        return low;
    }

    // linear scan, for comparison
    static int findSlotLinear(int[] prices, int newPrice) {
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] >= newPrice) {
                return i;
            }
        }
        return prices.length;
    }

    public static void main(String[] args) {
        int[] prices = {120, 150, 200, 260};

        System.out.println(findSlot(prices, 150));
        System.out.println(findSlot(prices, 210));
        System.out.println(findSlot(prices, 300));
        System.out.println(findSlotLinear(prices, 210));
    }

    /*
     * Complexity (n prices):
     *   Linear scan   : Time O(n),     Space O(1).
     *   Binary search : Time O(log n), Space O(1) - the search range is halved on every step.
     */
}
