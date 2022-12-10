package _2022.day10;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {

    private static Set<String> visibleTrees = new HashSet<>();
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(new File("src/_2022/day10/input.txt")));
        String line;

        int x = 1;
        int cycle = 0;

        List<String> lines = new ArrayList<>();

        while ((line = br.readLine()) != null) {
            lines.add(line);
        }


        boolean inCycle = false;
        String instruction = null;

        Iterator<String> it = lines.iterator();
        int signalStrength = 0;

        int[] sprite = new int[40];
        sprite[0] = 1;
        sprite[1] = 1;
        sprite[2] = 1;
        String spriteStr = getSprite(sprite);

        while (it.hasNext() || inCycle) {
            cycle++;


            if(cycle == 20 || cycle == 60 || cycle == 100 || cycle == 140 || cycle == 180 || cycle == 220) {
                signalStrength += (x * cycle);
            }

            if(!inCycle) {
                instruction = it.next();

                if(instruction.equals("noop"))
                    inCycle = false;

                if(instruction.startsWith("addx"))
                    inCycle = true;
            }
            else {
                String[] add = instruction.split(" ");
                x += Integer.parseInt(add[1]);

                sprite = new int[40];
                try {
                    sprite[x - 1] = 1;
                    sprite[x] = 1;
                    sprite[x + 1] = 1;
                    spriteStr = getSprite(sprite);
                } catch (IndexOutOfBoundsException e) {
                    if(x == 0) {
                        sprite[x] = 1;
                        sprite[x + 1] = 1;
                    }
                    if(x == -1) {
                        sprite[x + 1] = 1;
                    }
                    if(x == 39) {
                        sprite[x - 1] = 1;
                        sprite[x] = 1;
                    }
                    if(x == 40) {
                        sprite[x - 1] = 1;
                    }
                }

                inCycle = false;
            }


            if(sprite[cycle%40] == 1)
                System.out.print("#");
            else
                System.out.print(".");

            if(cycle%40 == 0)
                System.out.println();

        }

        System.out.println("x = " + x);
        System.out.println("signalStrength = " + signalStrength);

    }

    private static String getSprite(int[] sprite) {
        String spriteStr = "";

        for (int i: sprite) {
            if(i == 1) spriteStr += "#";
            else spriteStr += ".";
        }

        return spriteStr;
    }

}

// BPJAZGAP