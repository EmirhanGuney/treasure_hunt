/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aramaalgoritmasi;



import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import javax.swing.JButton;

public class Carakter {
    
   
    public static List<Point> findShortestPath(Point start, Point end, JButton[][] butonlar, int boyutX, int boyutY) {
        List<Point> shortestPath = new ArrayList<>();
        int[][] distances = new int[boyutX][boyutY];
        boolean[][] visited = new boolean[boyutX][boyutY];
        Point[][] previous = new Point[boyutX][boyutY];

        // Priority queue for open list (sorted by total cost)
        PriorityQueue<Point> openList = new PriorityQueue<>((p1, p2) -> {
            int totalCost1 = distances[p1.x][p1.y] + heuristic(p1, end);
            int totalCost2 = distances[p2.x][p2.y] + heuristic(p2, end);
            return Integer.compare(totalCost1, totalCost2);
        });

        // Initialization
        for (int i = 0; i < boyutX; i++) {
            for (int j = 0; j < boyutY; j++) {
                distances[i][j] = Integer.MAX_VALUE;
                visited[i][j] = false;
                previous[i][j] = null;
            }
        }

        distances[start.x][start.y] = 0;
        openList.add(start);

        // A* algorithm
        while (!openList.isEmpty()) {
            Point current = openList.poll();

            if (current.equals(end)) {
                break; // Reached the destination
            }

            visited[current.x][current.y] = true;

            // Check neighbors (up, down, left, right)
            int[] dx = {0, 0, -1, 1};
            int[] dy = {-1, 1, 0, 0};
            for (int i = 0; i < 4; i++) {
                int newRow = current.x + dx[i];
                int newCol = current.y + dy[i];

                if (isValid(newRow, newCol, boyutX, boyutY) && !visited[newRow][newCol] && !isBlack(newRow, newCol, butonlar)) {
                    int tentativeDistance = distances[current.x][current.y] + 1; // Assuming each move has a cost of 1

                    if (tentativeDistance < distances[newRow][newCol]) {
                        distances[newRow][newCol] = tentativeDistance;
                        previous[newRow][newCol] = current;
                        openList.add(new Point(newRow, newCol));
                    }
                }
            }
        }

        // Reconstruct the path
        Point current = end;
        while (current != null) {
            shortestPath.add(0, current);
            current = previous[current.x][current.y];
        }

        return shortestPath;
    }

    public static int heuristic(Point a, Point b) {
        // Manhattan distance heuristic
        return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
    }

    public static boolean isValid(int row, int col, int rows, int cols) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    public static boolean isBlack(int row, int col, JButton[][] matris) {
        return matris[row][col].getBackground().equals(Color.BLACK);
    }
    
}

