package _2022.day9;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainPart2 {

    private static Set<String> uniquePositions = new HashSet<>();
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(new File("src/_2022/day9/input.txt")));
        String line;

        String[][] grid = new String[1000][1000];
        int[] startPoint = {500,500};

        uniquePositions.add(startPoint[0] + "|" + startPoint[1]);

        List<int[]> rope = new ArrayList<>();
        for (int i = 9; i >= 0; i--) {
            rope.add(startPoint.clone());
        }

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

                int[] headBefore = rope.get(0).clone();
                rope.get(0)[0] += y;
                rope.get(0)[1] += x;

//                printGrid(grid, startPoint, rope);

                rope = updateTail(rope);

//                System.out.println();
//                printGrid(grid, startPoint, rope);
                uniquePositions.add(rope.get(9)[0] + "|" + rope.get(9)[1]);

            }
        }

        System.out.println("Distinct Tail: " + uniquePositions.size());
    }

    private static List<int[]> updateTail(List<int[]> rope) {

        for (int i = 0; i < rope.size()-1; i++) {

            if(i+1 == rope.size())
                System.out.println("oob");

            int[] head = rope.get(i);
            int[] tail = rope.get(i+1);

            int moveY = head[0] - tail[0];
            int moveX = head[1] - tail[1];

            int dist = Math.abs(moveX) + Math.abs(moveY);
            int distX = Math.abs(moveX);
            int distY = Math.abs(moveY);

            if(dist == 0) {
                break;
            }
            if(dist == 1 || (distX == 1 && distY == 1)) {
                continue;
            }
            if (dist == 2) {

                if(moveX == 2 && moveY == 0)
                    tail[1]++;
                if(moveX == -2 && moveY == 0)
                    tail[1]--;
                if(moveY == 2 && moveX == 0)
                    tail[0]++;
                if(moveY == -2 && moveX == 0)
                    tail[0]--;
            }

            if (dist == 3) {

                if((moveX == 1 && moveY == 2) || moveX == 2 && moveY == 1) {
                    tail[0]++;
                    tail[1]++;
                    continue;
                }

                if((moveX == 1 && moveY == -2) || (moveX == 2 && moveY == -1)) {
                    tail[0]--;
                    tail[1]++;
                    continue;
                }

                if((moveX == -1 && moveY == 2) || (moveX == -2 && moveY == 1)) {
                    tail[0]++;
                    tail[1]--;
                    continue;
                }

                if((moveX == -1 && moveY == -2) || (moveX == -2 && moveY == -1)) {
                    tail[0]--;
                    tail[1]--;
                    continue;
                }
            }

            if (dist == 4) {
                if(moveX == 2 && moveY == 2) {
                    tail[0]++;
                    tail[1]++;
                    continue;
                }
                if(moveX == 2 && moveY == -2) {
                    tail[0]--;
                    tail[1]++;
                    continue;
                }
                if(moveX == -2 && moveY == 2) {
                    tail[0]++;
                    tail[1]--;
                    continue;
                }
                if(moveX == -2 && moveY == -2) {
                    tail[0]--;
                    tail[1]--;
                    continue;
                }
            }


        }

        return rope;
    }

    private static void printGrid(String[][] grid,
                                  int[] startPoint,
                                  List<int[]> k) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                String print = ".";

                if(startPoint[0] == i && startPoint[1] == j)
                    print = "s";

                for (int l = k.size()-1; l >= 0; l--) {
                    if(k.get(l)[0] == i && k.get(l)[1] == j) {
                        print = Integer.toString(l);
                        if (l == 0)
                            print = "H";
                    }
                }

                System.out.print(print);
            }
            System.out.println();
        }
    }
}
