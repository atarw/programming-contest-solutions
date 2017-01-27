import java.io.*;
import java.util.*;

public class LEFTOVER_EGGNOG {
  public static void main (String [] t) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    PrintWriter out = new PrintWriter (System.out);
    
    t = in.readLine ().split (" ");
    int VA = Integer.parseInt (t [0]), VB = Integer.parseInt (t [1]), M = Integer.parseInt (t [2]);
    
    if (M > VA && M > VB) {
      out.print ("Not possible");
      out.close ();
      return;
    }
    
    String [] steps = {"fill A", "fill B", "pour A B", "pour B A", "chug A", "chug B", ""};
    int FILL_A = 0, FILL_B = 1, POUR_A_B = 2, POUR_B_A = 3, CHUG_A = 4, CHUG_B = 5, NA = 6;
    
    int [][] cache = new int [VA + 1][VB + 1];
    State [][] parent = new State [VA + 1][VB + 1];
    
    for (int i = 0; i < cache.length; ++i) {
      for (int j = 0; j < cache [0].length; ++j) {
        cache [i][j] = Integer.MAX_VALUE;
      }
    }
    
    State curr = new State (0, 0, NA);
    Queue <State> queue = new ArrayDeque <State> ();
    queue.offer (curr);
    
    cache [0][0] = 0; parent [0][0] = curr;
    
    int pourA, pourB, min = Integer.MAX_VALUE;
    State minState = null;
    
    while (!queue.isEmpty ()) {
      curr = queue.poll ();
      
      if ((curr.A == M || curr.B == M) && min > cache [curr.A][curr.B]) {
        min = cache [curr.A][curr.B];
        minState = curr;
      }
      
      //fill A
      if (cache [VA][curr.B] > cache [curr.A][curr.B] + 1) {
        cache [VA][curr.B] = cache [curr.A][curr.B] + 1;
        queue.offer (new State (VA, curr.B, FILL_A));
        
        parent [VA][curr.B] = curr;
      }
      
      //fill B
      if (cache [curr.A][VB] > cache [curr.A][curr.B] + 1) {
        cache [curr.A][VB] = cache [curr.A][curr.B] + 1;
        queue.offer (new State (curr.A, VB, FILL_B));
        
        parent [curr.A][VB] = curr;
      }
      
      //pour A -> B
      if (curr.A + curr.B <= VB) {
        pourB = curr.A + curr.B; pourA = 0;
      }
      else {
        pourB = VB; pourA = curr.A + curr.B - VB;
      }
      
      if (cache [pourA][pourB] > cache [curr.A][curr.B] + 1) {
        cache [pourA][pourB] = cache [curr.A][curr.B] + 1;
        queue.offer (new State (pourA, pourB, POUR_A_B));
        
        parent [pourA][pourB] = curr;
      }
      
      //pour B -> A
      if (curr.A + curr.B <= VA) {
        pourA = curr.B + curr.A; pourB = 0;
      }
      else {
        pourA = VA; pourB = curr.A + curr.B - VA;
      }
      
      if (cache [pourA][pourB] > cache [curr.A][curr.B] + 1) {
        cache [pourA][pourB] = cache [curr.A][curr.B] + 1;
        queue.offer (new State (pourA, pourB, POUR_B_A));
        
        parent [pourA][pourB] = curr;
      }
      
      //chug A
      if (cache [0][curr.B] > cache [curr.A][curr.B] + 1) {
        cache [0][curr.B] = cache [curr.A][curr.B] + 1;
        queue.offer (new State (0, curr.B, CHUG_A));
        
        parent [0][curr.B] = curr;
      }
      
      //chug B
      if (cache [curr.A][0] > cache [curr.A][curr.B] + 1) {
        cache [curr.A][0] = cache [curr.A][curr.B] + 1;
        queue.offer (new State (curr.A, 0, CHUG_B));
        
        parent [curr.A][0] = curr;
      }
    }
    
    if (min == Integer.MAX_VALUE) {
      out.print ("Not possible");
    }
    else {
      curr = minState;
      int [] path = new int [min];
      int s = 0;
      
      while (curr.O != NA) {
        path [s] = curr.O; ++s;
        curr = parent [curr.A][curr.B];
      }
      
      for (int j = min - 1; j >= 0; --j) {
        out.println (steps [path [j]]);
      }
    }
    
    out.close ();
  }
}

class State {
  int A, B, O;
  
  public State (int A, int B, int O) {
    this.A = A; this.B = B; this.O = O;
  }
}