import java.util.Arrays;
import java.util.Random;

public class TSPHillClimbing {

    private static final Random RANDOM = new Random();

    // Calculate the total cost of the tour
    private static int calculateTourCost(int[][] distanceMatrix, int[] tour) {
        int totalCost = 0;
        for (int i = 0; i < tour.length - 1; i++) {
            totalCost += distanceMatrix[tour[i]][tour[i + 1]];
        }
        totalCost += distanceMatrix[tour[tour.length - 1]][tour[0]]; // Return to the starting city
        return totalCost;
    }

    // Generate a random initial tour
    private static int[] generateRandomTour(int numCities) {
        int[] tour = new int[numCities];
        for (int i = 0; i < numCities; i++) {
            tour[i] = i;
        }
        // Shuffle the tour
        for (int i = 0; i < numCities; i++) {
            int j = RANDOM.nextInt(numCities);
            int temp = tour[i];
            tour[i] = tour[j];
            tour[j] = temp;
        }
        return tour;
    }

    // Generate neighboring tours by swapping two cities
    private static int[] generateNeighbor(int[] tour) {
        int[] newTour = tour.clone();
        int i = RANDOM.nextInt(tour.length);
        int j = RANDOM.nextInt(tour.length);
        while (i == j) {
            j = RANDOM.nextInt(tour.length);
        }
        // Swap two cities
        int temp = newTour[i];
        newTour[i] = newTour[j];
        newTour[j] = temp;
        return newTour;
    }

    // Hill climbing algorithm for TSP
    public static int[] hillClimbingTSP(int[][] distanceMatrix) {
        int numCities = distanceMatrix.length;
        int[] currentTour = generateRandomTour(numCities);
        int currentCost = calculateTourCost(distanceMatrix, currentTour);
        
        boolean improved = true;
        while (improved) {
            improved = false;
            for (int i = 0; i < numCities; i++) {
                for (int j = i + 1; j < numCities; j++) {
                    int[] neighborTour = generateNeighbor(currentTour);
                    int neighborCost = calculateTourCost(distanceMatrix, neighborTour);
                    if (neighborCost < currentCost) {
                        currentTour = neighborTour;
                        currentCost = neighborCost;
                        improved = true;
                    }
                }
            }
        }
        
        return currentTour;
    }

    public static void main(String[] args) {
        // distance matrix for 4 cities
        int[][] distanceMatrix = {
            {0, 10, 15, 20},
            {10, 0, 35, 25},
            {15, 35, 0, 30},
            {20, 25, 30, 0}
        };

        int[] optimalTour = hillClimbingTSP(distanceMatrix);
        int optimalCost = calculateTourCost(distanceMatrix, optimalTour);

        System.out.println("Optimal Tour: " + Arrays.toString(optimalTour));
        System.out.println("Optimal Cost: " + optimalCost);
    }
}
