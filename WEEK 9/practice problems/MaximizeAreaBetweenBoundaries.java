public class MaximizeAreaBetweenBoundaries {

    // brute force: try every pair of walls
    static int maxContainerAreaBruteForce(int[] heights) {
        int best = 0;
        for (int i = 0; i < heights.length; i++) {
            for (int j = i + 1; j < heights.length; j++) {
                int area = Math.min(heights[i], heights[j]) * (j - i);
                best = Math.max(best, area);
            }
        }
        return best;
    }

    // optimal: two pointers starting at the extreme ends
    static int maxContainerArea(int[] heights) {
        int left = 0;
        int right = heights.length - 1;
        int best = 0;

        while (left < right) {
            int area = Math.min(heights[left], heights[right]) * (right - left);
            best = Math.max(best, area);

            // the shorter wall limits the area, so move it inward hoping for a taller one
            if (heights[left] < heights[right]) {
                left++;
            } else {
                right--;
            }
        }
        return best;
    }

    public static void main(String[] args) {
        int[] heights = {1, 8, 6, 2, 5, 4, 8, 3, 7};

        System.out.println(maxContainerArea(heights));
        System.out.println(maxContainerAreaBruteForce(heights));
    }

    /*
     * Complexity:
     *   Brute force : Time O(n^2), Space O(1) - checks every pair of walls.
     *   Two pointers: Time O(n),   Space O(1) - each pointer moves inward at most n times in total.
     *
     * Why the optimal solution is better for very large inputs: for n = 1,000,000 walls the
     * brute force does about 5 * 10^11 checks, while two pointers does about 10^6.
     *
     * Why moving the shorter wall is safe: the width only shrinks as the pointers move in, so the
     * area can only grow if the limiting (shorter) wall gets taller. Moving the taller wall can
     * never help, because the shorter wall would still cap the height.
     */
}
