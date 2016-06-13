import java.io.*;
import java.util.*;

public class CCC_10_P5_KNIGHT_HOP {
  
  static final int [] movement = {18, 14, 31, 33, -18, -14, -31, -33};
  static final int [] board = new int [128];
  static final Map <Integer, List <Integer>> moves = new HashMap <Integer, List <Integer>> ();
  
  static int end;
  
  public static Integer least (int start) {
    Queue <Integer> queue = new LinkedList <Integer> ();
    
    queue.offer (start);
    board [start] = 0;
    int curr = start;
    
    out: while (!queue.isEmpty () && curr != end) {
      curr = queue.poll ();
      
      if (!moves.containsKey (curr)) {
        moves.put (curr, moves (curr));
      }
      
      for (Integer i : moves.get (curr)) {
        if (i == end) {
          board [end] = board [curr] + 1;
          break out;
        }
        else if (board [i] == 0) {
          board [i] = board [curr] + 1;
          queue.offer (i);
        }
      }
    }
    return board [end];
  }
  
  public static List <Integer> moves (int s) {
    List <Integer> moves = new LinkedList <Integer> ();
    
    for (int x = 0; x < movement.length; x++) {
      if (valid (s + movement [x])) {
        moves.add (s + movement [x]);
      }
    }
    
    return moves;
  }
  
  public static boolean valid (int s) {
    return (s & 0x88) == 0;
  }
  
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    
    String [] t = in.readLine ().split (" ");
    int start = 16 * (Integer.parseInt (t [1]) - 1) + (Integer.parseInt (t [0]) - 1);
    
    t = in.readLine ().split (" ");
    end = 16 * (Integer.parseInt (t [1]) - 1) + (Integer.parseInt (t [0]) - 1);
    
    System.out.println (least (start));
  }
}