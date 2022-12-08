package _2022.day8;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class Main {

    private static Set<String> visibleTrees = new HashSet<>();
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(new File("src/_2022/day8/input.txt")));
        String line;



        List<String> lines = new ArrayList<>();

        while ((line = br.readLine()) != null) {
            lines.add(line);
        }

        int[][] grid = new int[lines.get(0).length()][lines.size()];

        for (int i = 0; i < lines.size(); i++) {
            String[] row = lines.get(i).split("");

            for (int j = 0; j < row.length; j++) {

                grid[i][j] = Integer.parseInt(row[j]);

                //Add all edgecases
                if(i==0 || j == 0
                        || i == lines.size()-1
                        || j == row.length-1

                ) {
                    System.out.print(grid[i][j]);
                    addVisibleTree(i + "," + j + "," + grid[i][j]);
                }
                else
                    System.out.print(" ");
            }
            System.out.println();
        }

        //visibleTrees.stream().forEach(System.out::println);


        System.out.println("----------------Left-----------------");
        //left
        for (int i = 1; i < grid.length-1; i++) {
            int maxTreeHeight = grid[i][0];
            for (int j = 1; j < grid[i].length; j++) {


//                if(i==59)
//                    System.out.println();

                if (grid[i][j] > grid[i][j-1]
                        && grid[i][j] > maxTreeHeight) {
                    addVisibleTree(i + "," + j + "," + grid[i][j]);
                    System.out.print(grid[i][j]);
                    if (grid[i][j] > maxTreeHeight)
                        maxTreeHeight = grid[i][j];
                } else System.out.print(" ");
            }
            System.out.println();
        }

        System.out.println("----------------Top----------------");
        //Top
        for (int j = 1; j < grid.length-1; j++) {
            int maxTreeHeight = grid[0][j];
            for (int i = 1; i < grid[j].length; i++) {


                if(grid[i][j] > grid[i-1][j]
                        && grid[i][j] > maxTreeHeight) {
                    addVisibleTree(i + "," + j + "," + grid[i][j]);
                    System.out.print(grid[i][j]);
                    if (grid[i][j] > maxTreeHeight)
                        maxTreeHeight = grid[i][j];
                } else System.out.print(" ");
            }
            System.out.println();
        }

        System.out.println("----------------Right----------------");
        //right
        for (int i = 1; i < grid.length-1; i++) {
            int maxTreeHeight = grid[i][grid.length-1];
            for (int j = grid[i].length-2; j >= 0; j--) {


                if(grid[i][j] > grid[i][j+1]
                        && grid[i][j] > maxTreeHeight) {
                    addVisibleTree(i + "," + j + "," + grid[i][j]);
                    System.out.print(grid[i][j]);
                    if (grid[i][j] > maxTreeHeight)
                        maxTreeHeight = grid[i][j];
                } else System.out.print(" ");
            }
            System.out.println();
        }

        System.out.println("----------------Bottom----------------");
        //Top
        for (int j = 1; j < grid.length; j++) {
            int maxTreeHeight = grid[grid.length-1][j];
            maxTreeHeight = maxTreeHeight;
            for (int i = grid[j].length-2; i >= 0; i--) {


                if(grid[i][j] > grid[i+1][j]
                        && grid[i][j] > maxTreeHeight) {
                    addVisibleTree(i + "," + j + "," + grid[i][j]);
                    System.out.print(grid[i][j]);
                    if (grid[i][j] > maxTreeHeight)
                        maxTreeHeight = grid[i][j];
                } else System.out.print(" ");
            }
            System.out.println();
        }



        System.out.println("-------------------------------------");
        System.out.println("visibleTrees = " + visibleTrees.size());
        printVisibleTrees();
    }

    private static void addVisibleTree(String tree) {
        if("59,1,2".equals(tree))
            System.out.println(tree);
        visibleTrees.add(tree);
    }

    private static void printVisibleTrees() {

        String[][] grid = new String[99][99];

        for (String visibleTree: visibleTrees) {
            String[] split = visibleTree.split(",");
            grid[Integer.parseInt(split[0])][Integer.parseInt(split[1])] = split[2];
        }

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if(grid[i][j] == null || grid[i][j].isEmpty()) {
                    System.out.print(" ");
                    continue;
                }

                System.out.print(grid[i][j]);
            }
            System.out.println();
        }

    }

}