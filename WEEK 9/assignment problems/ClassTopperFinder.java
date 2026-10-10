public class ClassTopperFinder {

    // returns {rowIndex, total} of the student with the highest total
    static int[] findTopper(int[][] marks) {
        int bestRow = 0;
        int bestTotal = -1;

        for (int i = 0; i < marks.length; i++) {
            int total = 0;
            for (int j = 0; j < marks[i].length; j++) {
                total += marks[i][j];
            }
            // strict ">" keeps the smallest row index when totals tie
            if (total > bestTotal) {
                bestTotal = total;
                bestRow = i;
            }
        }
        return new int[]{bestRow, bestTotal};
    }

    public static void main(String[] args) {
        int[][] marks = {
                {78, 85, 90},
                {88, 92, 79},
                {65, 70, 95}
        };

        int[] result = findTopper(marks);
        System.out.println("(" + result[0] + ", " + result[1] + ")");
    }

    /*
     * Complexity (m students, n subjects):
     *   Time  : O(m * n) - every mark is added exactly once.
     *   Space : O(1)     - only the running total and the best answer are stored.
     */
}
