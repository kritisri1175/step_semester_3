public class HotWeatherAlertWindows {

    static int countAlerts(int[] readings, int k, int threshold) {
        long limit = (long) k * threshold; // compare sums instead of averages (no decimals)
        long windowSum = 0;
        int alerts = 0;

        // sum of the first block
        for (int i = 0; i < k; i++) {
            windowSum += readings[i];
        }
        if (windowSum >= limit) {
            alerts++;
        }

        // slide forward: add the new reading, subtract the one that left
        for (int i = k; i < readings.length; i++) {
            windowSum += readings[i] - readings[i - k];
            if (windowSum >= limit) {
                alerts++;
            }
        }
        return alerts;
    }

    // recalculates every block from scratch
    static int countAlertsBruteForce(int[] readings, int k, int threshold) {
        long limit = (long) k * threshold;
        int alerts = 0;
        for (int start = 0; start + k <= readings.length; start++) {
            long sum = 0;
            for (int i = start; i < start + k; i++) {
                sum += readings[i];
            }
            if (sum >= limit) {
                alerts++;
            }
        }
        return alerts;
    }

    public static void main(String[] args) {
        int[] readings = {2, 2, 2, 2, 5, 5, 5, 8};

        System.out.println(countAlerts(readings, 3, 4));
        System.out.println(countAlertsBruteForce(readings, 3, 4));
    }

    /*
     * Complexity (n readings, block size k):
     *   Recalculating every block : Time O(n * k), Space O(1).
     *   Sliding window            : Time O(n),     Space O(1) - each reading is added once and removed once.
     */
}
