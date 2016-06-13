import java.io.*;
import java.util.*;

public class DMOPC_14_P3_GOLDEN_LILY {
  
  static int [][] maze;
  static int [][] steps;
  
  public static int solve (P gold) {
    Queue <P> queue = new ArrayDeque <P> ();
    List <P> neighbours;
    P curr;
    
    queue.offer (new P (0, 0));
    
    //preprocess
    neighbours = neighbours (0, 0);
    
    for (P p : neighbours) {
      maze [p.x][p.y] += maze [queue.peek ().x][queue.peek ().y];
    }
    
    while (!queue.isEmpty ()) {
      curr = queue.poll ();
      
      if (curr.equals (gold)) {
        break;
      }
      else {
        neighbours = neighbours (curr.x, curr.y);
        
        for (P p : neighbours) {
          if (!(p.x == 0 && p.y == 0) && (steps [p.x][p.y] == 0 || steps [curr.x][curr.y] + maze [p.x][p.y] < steps [p.x][p.y])) {
            steps [p.x][p.y] = steps [curr.x][curr.y] + maze [p.x][p.y];
            queue.offer (p);
          }
        }
      }
    }
    
    return steps [gold.x][gold.y];
  }
  
  public static boolean valid (int r, int c) {
    return r >= 0 && r < maze.length && c >= 0 && c < maze [0].length;
  }
  
  public static List <P> neighbours (int r, int c) {
    List <P> list = new ArrayList <P> ();
    
    if (valid (r + 1, c)) {
      list.add (new P (r + 1, c));
    }
    if (valid (r, c - 1)) {
      list.add (new P (r, c - 1));
    }
    if (valid (r, c + 1)) {
      list.add (new P (r, c + 1));
    }
    
    return list;
  }
  
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    String [] t = in.readLine ().split (" ");
    
    int L = Integer.parseInt (t [0]), D = Integer.parseInt (t [1]);
    maze = new int [D][L];
    steps = new int [D][L];
    
    for (int d = 0; d < D; d++) {
      t = in.readLine ().split (" ");
      
      for (int l = 0; l < L; l++) {
        maze [d][l] = Integer.parseInt (t [l]);
      }
    }
    
    t = in.readLine ().split (" ");
    P gold = new P (Integer.parseInt (t [0]), Integer.parseInt (t [1]));
    
    System.out.println (solve (gold));
    
    for (int [] arr : steps) {
      for (int i : arr) {
        System.out.print (i + " ");
      }
      System.out.println ();
    }
  }
}

class P {
  int x, y;
  
  public boolean equals (Object o) {
    P p = (P) o;
    return p.x == x && p.y == y;
  }
  
  public P (int x, int y) {
    this.x = x;
    this.y = y;
  }
}