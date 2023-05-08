package ChessGame;

package Chess_TermProject_COMP1020.ChessGame;

import java.util.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.PrintWriter; // Step 1
import java.io.IOException;


public class History {
    public String name;
    private int score = 0;
    private int time;
    static LinkedList<String> history = new LinkedList<>();
    History(int score){
        this.score = score;
        BufferedReader br = null;
        try{
            br = new BufferedReader(new FileReader("/Users/tranlehai/TERM_PROJECT/Term_Project/src/Chess_TermProject_COMP1020/ChessGame/History"));
            Scanner sc = new Scanner(br);
            time = Integer.parseInt(sc.nextLine());
            while (sc.hasNextLine())
                history.add(sc.nextLine());
            addName(time, this.score);
        }
        catch(Exception e){
            System.out.print(e);
        }
    }
    public static void addName(int time, int score){
        if (history.size() > 10){
            history.pollFirst();
        }
        String st = "Attempt " + time;
        System.out.println(String.valueOf(time).length());
        for (int i = 0; i < 20 - String.valueOf(time).length(); i++){
            st += " ";
        }
        st += score;
        history.add(st);
        if (time > 1000){
            time = -1;
        }
        try{
            PrintWriter out = new PrintWriter("/Users/tranlehai/TERM_PROJECT/Term_Project/src/Chess_TermProject_COMP1020/ChessGame/History"); 

            time += 1;
            out.println(String.valueOf(time));   

            for (int i = 0; i < history.size(); i++){
                out.println(history.get(i));
            }

            out.close(); 
        }
        catch(Exception e){
            System.out.print(e);
        }
    }
}
