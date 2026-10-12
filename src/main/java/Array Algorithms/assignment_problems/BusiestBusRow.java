import java.util.Scanner;

public class BusiestBusRow {
    public static int[] busiestRow(int[][] grid) {
        int bestRow = 0;
        int highestTotal = -1;

        for (int i = 0; i < grid.length; i++) {
            int rowTotal = 0;

            for (int j = 0; j < grid[i].length; j++) {
                rowTotal += grid[i][j];
            }

            if (rowTotal > highestTotal) {
                highestTotal = rowTotal;
                bestRow = i;
            }
        }

        return new int[]{bestRow, highestTotal};
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int rows = sc.nextInt();
        int cols = sc.nextInt();

        int[][] grid = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = sc.nextInt();
            }
        }

        int[] result = busiestRow(grid);

        System.out.printf(
                "Row %d, Total %d%n",
                result[0], result[1]);

        sc.close();
    }
}