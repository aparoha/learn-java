package datastructures.graphs.bfs;

import java.util.*;

public class SnakesAndLadders {

    public static int play(List<List<Integer>> ladders, List<List<Integer>> snakes, int boardSize) {
        Map<Integer, Integer> map = new HashMap<>();
        for (List<Integer> ladder : ladders)
            map.put(ladder.get(0), ladder.get(1));
        for (List<Integer> snake : snakes)
            map.put(snake.get(0), snake.get(1));
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        queue.add(1);
        visited.add(1);
        int level = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int x = queue.poll();
                for (int diceCounter = 1; diceCounter < 7; diceCounter++) {
                    int finalPosition = x + diceCounter;
                    // Reset the position if ladder or snake encounters
                    if (map.containsKey(finalPosition))
                        finalPosition = map.get(finalPosition);
                    // Return level once reaches to the final cell
                    if (finalPosition == boardSize)
                        return level;
                    if (!visited.contains(finalPosition)) {
                        visited.add(finalPosition);
                        queue.add(finalPosition);
                    }
                }
            }
            level++;
        }
        return -1;
    }

    public static void main(String[] args) {
        testcase1();
        testcase2();
    }

    private static void testcase1() {
        List<List<Integer>> ladders = List.of(
                List.of(1, 38),
                List.of(4, 14),
                List.of(9, 31),
                List.of(21, 42),
                List.of(28, 84),
                List.of(51, 67),
                List.of(72, 91),
                List.of(80, 99)
        );
        List<List<Integer>> snakes = List.of(
                List.of(17, 7),
                List.of(54, 34),
                List.of(62, 19),
                List.of(64, 60),
                List.of(87, 36),
                List.of(93, 73),
                List.of(95, 75),
                List.of(98, 79)
        );
        System.out.println(play(ladders, snakes, 100));
    }

    private static void testcase2() {
        List<List<Integer>> ladders = List.of(
                List.of(3, 22),
                List.of(5, 8),
                List.of(11, 26),
                List.of(20, 29)
        );
        List<List<Integer>> snakes = List.of(
                List.of(27, 1),
                List.of(21, 9),
                List.of(17, 4),
                List.of(19, 7)
        );
        System.out.println(play(ladders, snakes, 30));
    }
}
