package _2022.day11;

import java.io.*;
import java.util.*;

public class Main {

    private static Set<String> visibleTrees = new HashSet<>();
    public static void main(String[] args) throws IOException {
        new Main();
    }

    public Main () throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(new File("src/_2022/day11/inputTest.txt")));
        String line;

        List<Monkey> monkeys = new ArrayList<>();
        Monkey currentMonkey = null;
        while ((line = br.readLine()) != null) {

            if (line.startsWith("Monkey") && currentMonkey == null) {
                currentMonkey = new Monkey();
                monkeys.add(currentMonkey);
            }

            if (line.isEmpty()) {
                currentMonkey = null;
            }

            if (line.startsWith("  Starting items:")) {
                String[] items = line.substring(18).split(", ");

                Queue<Integer> queue = new LinkedList<>();
                Arrays.stream(items).map(Integer::parseInt).forEach(i -> queue.add(i));
                currentMonkey.setCarryList(queue);
            }

            if (line.startsWith("  Operation:")) {
                String[] items = line.substring(23).split(" ");

                currentMonkey.setOperation(Operation.fromString(items[0]).get());

                int operationValue = -1;
                if (!items[1].equals("old"))
                    operationValue = Integer.parseInt(items[1]);

                currentMonkey.setOperationValue(operationValue);
            }

            if (line.startsWith("  Test:")) {
                String divisor = line.substring(21);
                currentMonkey.setWorryDivisor(Integer.parseInt(divisor));
            }

            if (line.startsWith("    If true:")) {
                String monkey = line.substring(29);
                currentMonkey.setTrueMonkey(Integer.parseInt(monkey));
            }

            if (line.startsWith("    If false:")) {
                String monkey = line.substring(30);
                currentMonkey.setFalseMonkey(Integer.parseInt(monkey));
            }
        }




        for (Monkey monkey : monkeys) {

            if (!monkey.getCarryList().isEmpty()) {

                for (Integer item : monkey.getCarryList()) {

                    item = monkey.inspect();
                    int nextMonkey = monkey.getNextMonkey(item);

                    monkeys.get(nextMonkey).getCarryList().add(item);

                }
            }
        }

        System.out.println("Total Monkeys: " + monkeys.size());

    }
}