package _2022.day9;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {

    private static Set<String> uniquePositions = new HashSet<>();
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(new File("src/_2022/day9/input.txt")));
        String line;



        String[][] grid = new String[1000][1000];


        int[] start = {grid.length/2,grid[0].length/2};
        int[] head = start.clone();
        int[] tail = start.clone();
        uniquePositions.add(start[0] + "|" + start[1]);

        System.out.println("---Start---");


        while ((line = br.readLine()) != null) {
            String[] move = line.split(" ");

            // get move instructions
            int x = 0;
            int y = 0;

            if("R".equals(move[0])) {
                x = 1;
                y = 0;
            } else if ("L".equals(move[0])) {
                x = -1;
                y = 0;
            }else if ("U".equals(move[0])) {
                x = 0;
                y = -1;
            }else if ("D".equals(move[0])) {
                x = 0;
                y = 1;
            }
            for (int i = 0; i < Integer.parseInt(move[1]); i++) {
                int[] headStart = head.clone();
                head[0] += y;
                head[1] += x;

                tail = getNewTail(head, headStart, tail, x, y);
                uniquePositions.add(tail[0] + "|" + tail[1]);

            }

        }


        System.out.println();

//        printGrid(grid, start, head, tail);

        System.out.println("Distinct Tail: " + uniquePositions.size());
    }

    private static int[] getNewTail(int[] head, int[] headStart, int[] tail, int moveX, int moveY) {

        if ((headStart[0] == tail[0] && headStart[1] == tail[1])
                || (head[0] == tail[0] && head[1] == tail[1])) {
            return tail;
        }
        if(Math.abs(head[0]-tail[0]) > 1
                || Math.abs(head[1]-tail[1]) > 1) {
            return headStart;
        }
        if(Math.abs(head[0]-tail[0]) == 1
                || Math.abs(head[1]-tail[1]) == 1) {
            return tail;
        }

        if(moveX == 1) {
            tail[1]++;
            return tail;
        }
        if(moveX == -1) {
            tail[1]--;
            return tail;
        }
        if(moveY == 1) {
            tail[0]++;
            return tail;
        }
        if(moveY == -1) {
            tail[0]--;
            return tail;
        }

        return tail;
    }

    private static void printGrid(String[][] grid, int[] startPoint, int[] head, int[] tail) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                String print = ".";

                if(startPoint[0] == i && startPoint[1] == j)
                    print = "s";
                if (tail[0] == i && tail[1] == j)
                    print = "T";

                if (head[0] == i && head[1] == j)
                    print = "H";

                System.out.print(print);
            }
            System.out.println();
        }
    }

}