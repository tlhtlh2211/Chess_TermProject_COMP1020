package ChessGame;

import java.util.*;
public class History {
    public String name;
    public int score = 0;
    private static int size = 0;
    public static int time = 0;
    public static LinkedList<History> history = new LinkedList<>();
    History(String name, int score){
        this.name = name;
        this.score = score;
    }
    public static void addName(String name, int score){
        System.out.println(name);
        size += 1;
        if (size > 10){
            history.pollLast();
            size -= 1;
        }
        if (name == null){
            time += 1;
            String n = "Chess" + String.valueOf(time);
            history.add(new History(n, score));
        }
        else history.addFirst(new History(name, score));
    }
}
