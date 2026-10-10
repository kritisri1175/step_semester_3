import java.util.Arrays;

public class MergingTwoTokenQueues {

    static int[] mergeTokens(int[] counterA, int[] counterB) {
        int[] merged = new int[counterA.length + counterB.length];
        int i = 0;
        int j = 0;
        int k = 0;

        // copy the smaller current value each time
        while (i < counterA.length && j < counterB.length) {
            if (counterA[i] <= counterB[j]) {
                merged[k++] = counterA[i++];
            } else {
                merged[k++] = counterB[j++];
            }
        }
        // one list ran out, copy the rest of the other
        while (i < counterA.length) {
            merged[k++] = counterA[i++];
        }
        while (j < counterB.length) {
            merged[k++] = counterB[j++];
        }
        return merged;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(mergeTokens(new int[]{3, 8, 15, 20}, new int[]{5, 8, 12})));
        System.out.println(Arrays.toString(mergeTokens(new int[]{}, new int[]{4, 9})));
    }

    /*
     * Complexity (m and n are the lengths of the two lists):
     *   Merge with two pointers : Time O(m + n), Space O(m + n) for the result list.
     *   Join and sort           : Time O((m + n) log(m + n)), Space O(m + n).
     * The two-pointer merge is faster because it uses the fact that both lists are
     * already sorted, while sorting the joined list throws that information away.
     */
}
