package _2022.day11;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainPart2 {

    private static Set<String> visibleTrees = new HashSet<>();
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(new File("src/_2022/day8/input.txt")));
        String line;


        List<String> lines = new ArrayList<>();

        while ((line = br.readLine()) != null) {
            lines.add(line);
        }

        int[][] grid = buildGrid(lines);


        int maxView = 0;

        for (int i = 0; i < grid.length-1; i++) {
            for (int j = 0; j < grid[i].length-1; j++) {

                int currentView = getView(grid, i, j);

                if(currentView > maxView) {
                    maxView = currentView;
                }
            }
        }

        System.out.println(maxView);
        System.out.println("-------------------------------------");
    }

    private static int[][] buildGrid(List<String> inputLines) {
        int[][] grid = new int[inputLines.get(0).length()][inputLines.size()];

        for (int i = 0; i < inputLines.size(); i++) {
            String[] row = inputLines.get(i).split("");

            for (int j = 0; j < row.length; j++) {

                grid[i][j] = Integer.parseInt(row[j]);

            }
            System.out.println();
        }

        return grid;
    }

    private static int getView(int[][] grid, int i, int j) {
        int currentTreeHeight = grid[i][j];
        int[] view = {0,0,0,0};

        //up
        int pos = i-1;
        while(pos >= 0) {
            view[0]++;
            if(grid[pos][j] >= currentTreeHeight) {
                break;
            }
            pos--;
        }

        // left
        pos = j-1;
        while(pos >= 0) {
            view[1]++;
            if(grid[i][pos] >= currentTreeHeight) {
                break;
            }
            pos--;
        }

        // down
        pos = i+1;
        while(pos < grid.length) {
            view[2]++;
            if(grid[pos][j] >= currentTreeHeight) {
                break;
            }
            pos++;
        }

        //right
        pos = j+1;
        while(pos < grid.length) {
            view[3]++;
            if(grid[i][pos] >= currentTreeHeight) {
                break;
            }
            pos++;
        }


        return (view[0]
                * view[1]
                * view[2]
                * view[3] );
    }
}