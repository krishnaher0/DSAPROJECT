package org.example;

public class LongestHike {

    // Method to find the longest valid hike and its starting index
    public static Result findLongestHike(int[] nums, int k) {
        int maxLength = 0; // Maximum length of the longest hike
        int currentLength = 1; // Current length of the ongoing hike
        int startIndex = 0; // Start index of the current valid hike
        int bestStartIndex = 0; // Start index of the longest hike found

        for (int i = 1; i < nums.length; i++) {
            // Check if the current hike segment is valid
            if (nums[i] > nums[i - 1] && nums[i] - nums[i - 1] <= k) {
                currentLength++; // Increase the current hike length
            } else {
                // Reset hike length and start index when the hike segment is invalid
                currentLength = 1;
                startIndex = i;
            }
            // Update the longest hike details if the current one is longer
            if (currentLength > maxLength) {
                maxLength = currentLength;
                bestStartIndex = startIndex;
            }
        }

        // Return the result containing the longest hike details
        return new Result(maxLength, bestStartIndex, nums);
    }

    // Class to hold the details of the longest hike
    static class Result {
        int length; // Length of the longest hike
        int startIndex; // Start index of the longest hike
        int[] trail; // Array representing the hike trail

        Result(int length, int startIndex, int[] trail) {
            this.length = length;
            this.startIndex = startIndex;
            this.trail = trail;
        }
    }

    public static void main(String[] args) {
        int[] trail = {4, 2, 1, 4, 3, 4, 5, 8, 15};
        int k = 3;

        // Find the longest valid hike
        Result result = findLongestHike(trail, k);

        // Print the full trail
        System.out.println("Trail: ");
        for (int elevation : trail) {
            System.out.print(elevation + " ");
        }
        System.out.println(); // New line after printing the trail

        // Print the length of the longest hike
        System.out.println("The longest continuous hike length is: " + result.length);

        // Print the longest hike segment
        System.out.println("The longest continuous hike trail is: ");
        for (int i = result.startIndex; i < result.startIndex + result.length; i++) {
            System.out.print(result.trail[i] + " ");
        }
    }
}
