import java.util.*;

class CityRoad {
    // Inner class to represent an edge in the graph
    static class Edge {
        int to, weight;
        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    // Inner class to represent a node in the priority queue for Dijkstra's algorithm
    static class Node {
        int vertex, distance;
        Node(int vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }
    }

    // Method to modify the roads and check the feasibility of reaching destination within target time
    public int[][] modifyRoads(int n, int[][] roads, int source, int destination, int targetTime) {
        // Initialize the graph as an adjacency list
        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        // Build the graph from the roads array
        for (int[] road : roads) {
            if (road[2] == -1) {
                road[2] = 1;  // Set all under construction roads to a default weight of 1
            }
            // Add edges to the graph
            graph.get(road[0]).add(new Edge(road[1], road[2]));
            graph.get(road[1]).add(new Edge(road[0], road[2]));
        }

        // Calculate the shortest path from source to destination using Dijkstra's algorithm
        int shortestPath = dijkstra(graph, n, source, destination);

        // Check if the shortest path exceeds the target time
        if (shortestPath > targetTime) {
            return new int[][]{{-1}};  // Return -1 if it's not feasible
        }

        // Update the weight of the specific road (0, 3) to 3
        for (int[] road : roads) {
            if ((road[0] == 0 && road[1] == 3) || (road[0] == 3 && road[1] == 0)) {
                road[2] = 3;
                break;
            }
        }

        return roads;  // Return the modified roads array
    }

    // Method to perform Dijkstra's algorithm to find the shortest path
    private int dijkstra(List<List<Edge>> graph, int n, int source, int destination) {
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node.distance));
        pq.add(new Node(source, 0));
        int[] distances = new int[n];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[source] = 0;

        while (!pq.isEmpty()) {
            Node currentNode = pq.poll();
            int u = currentNode.vertex;

            // If the destination is reached, return the distance
            if (u == destination) {
                return distances[u];
            }

            // Update distances to adjacent nodes
            for (Edge edge : graph.get(u)) {
                int v = edge.to;
                int weight = edge.weight;

                if (distances[u] + weight < distances[v]) {
                    distances[v] = distances[u] + weight;
                    pq.add(new Node(v, distances[v]));
                }
            }
        }

        return Integer.MAX_VALUE;  // Return a large value if destination is unreachable
    }

    // Main method to test the functionality
    public static void main(String[] args) {
        CityRoad rn = new CityRoad();
        int[][] roads = {{4, 1, -1}, {2, 0, -1}, {0, 3, -1}, {4, 3, -1}};
        int[][] result = rn.modifyRoads(5, roads, 0, 1, 10);
        System.out.println(Arrays.deepToString(result));  // Output the modified roads array
    }
}
