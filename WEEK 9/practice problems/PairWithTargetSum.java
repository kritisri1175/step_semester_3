import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// Covers Problem 3 and Problem 4 (both ask for the same task: pair with target sum in an unsorted array)
public class PairWithTargetSum {

    // Approach 1: brute force - check every pair of distinct positions
    static boolean hasPairWithSumBruteForce(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return true;
                }
            }
        }
        return false;
    }

    // Approach 2: hash set - look up the complement of each number
    static boolean hasPairWithSum(int[] nums, int target) {
        Set<Integer> seen = new HashSet<>();
        for (int num : nums) {
            // the complement must come from an earlier position, so the two elements are distinct
            if (seen.contains(target - num)) {
                return true;
            }
            seen.add(num);
        }
        return false;
    }

    // Approach 3: sort a copy, then move two pointers inward
    static boolean hasPairWithSumSorting(int[] nums, int target) {
        int[] sorted = nums.clone();
        Arrays.sort(sorted);
        int left = 0;
        int right = sorted.length - 1;
        while (left < right) {
            int sum = sorted[left] + sorted[right];
            if (sum == target) {
                return true;
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] a = {2, 7, 11, 15};
        int[] b = {3, 4, 6};

        System.out.println(hasPairWithSum(a, 9));
        System.out.println(hasPairWithSum(b, 20));

        System.out.println(hasPairWithSumBruteForce(a, 9));
        System.out.println(hasPairWithSumSorting(a, 9));
    }

    /*
     * Complexity of each approach:
     *   Brute force : Time O(n^2),       Space O(1)    - simple, but slow for large inputs.
     *   Hash set    : Time O(n),         Space O(n)    - fastest; trades extra memory for speed.
     *   Sort + two pointers : Time O(n log n), Space O(n) for the copy (O(1) if sorting in place).
     */
}
