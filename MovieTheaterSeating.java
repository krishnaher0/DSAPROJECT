import java.util.HashMap;

public class MovieTheaterSeating {

    public boolean canFriendsSitTogether(int[] nums, int indexDiff, int valueDiff) {
        if (nums == null || nums.length < 2) {
            return false;
        }

        // This HashMap will map each number to its most recent index in the sliding window
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            // Check if there exists any value in the current sliding window such that
            // nums[i] - valueDiff <= value <= nums[i] + valueDiff
            for (int j = Math.max(0, nums[i] - valueDiff); j <= nums[i] + valueDiff; j++) {
                if (map.containsKey(j) && i - map.get(j) <= indexDiff) {
                    return true;
                }
            }

            // Add/update the current number's index in the map
            map.put(nums[i], i);

            // Maintain the sliding window
            if (i >= indexDiff) {
                map.remove(nums[i - indexDiff]);
            }
        }

        return false;
    }

    public static void main(String[] args) {
        MovieTheaterSeating m = new MovieTheaterSeating();

        int[] nums = {1, 2, 4, 6, 7};
        int indexDiff = 2;
        int valueDiff = 1;

        System.out.println(m.canFriendsSitTogether(nums, indexDiff, valueDiff)); // Output: true
    }
}
