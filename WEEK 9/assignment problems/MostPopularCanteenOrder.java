import java.util.HashMap;
import java.util.Map;

public class MostPopularCanteenOrder {

    // hash-map solution: one pass to count, one pass to break ties by first appearance
    static String mostPopular(String[] orders) {
        Map<String, Integer> counts = new HashMap<>();
        for (String item : orders) {
            counts.put(item, counts.getOrDefault(item, 0) + 1);
        }

        String bestItem = null;
        int bestCount = 0;
        for (String item : orders) {
            int count = counts.get(item);
            // strict ">" keeps the item that appears first when counts tie
            if (count > bestCount) {
                bestCount = count;
                bestItem = item;
            }
        }
        return "(\"" + bestItem + "\", " + bestCount + ")";
    }

    // brute force: rescan the whole list for every item
    static String mostPopularBruteForce(String[] orders) {
        String bestItem = null;
        int bestCount = 0;
        for (int i = 0; i < orders.length; i++) {
            int count = 0;
            for (int j = 0; j < orders.length; j++) {
                if (orders[j].equals(orders[i])) {
                    count++;
                }
            }
            if (count > bestCount) {
                bestCount = count;
                bestItem = orders[i];
            }
        }
        return "(\"" + bestItem + "\", " + bestCount + ")";
    }

    public static void main(String[] args) {
        String[] orders1 = {"dosa", "idli", "vada", "dosa", "idli", "dosa", "tea"};
        String[] orders2 = {"tea", "coffee", "coffee", "tea"};

        System.out.println(mostPopular(orders1));
        System.out.println(mostPopular(orders2));
        System.out.println(mostPopularBruteForce(orders1));
    }

    /*
     * Complexity (n orders, k distinct items):
     *   Rescanning for every item : Time O(n^2), Space O(1).
     *   Hash map                  : Time O(n),   Space O(k), which is at most O(n).
     */
}
