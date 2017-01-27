import java.io.*;
import java.util.*;

public class VMSS_15_P3_AGRIPHILISOPHICAL_DATA_SLAVES {
  
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    List <Integer> order = new LinkedList <Integer> ();
    Queue <Integer> queue = new ArrayDeque <Integer> ();
    int N = Integer.parseInt (in.readLine ()), A, B, curr;
    int [] indegree = new int [N], cache = new int [N];
    int [] list = new int [N];
    String [] t;
    
    Arrays.fill (list, -1);
    
    for (int n = 0; n < N - 1; n++) {
      t = in.readLine ().split (" ");
      A = Integer.parseInt (t [0]) - 1;
      B = Integer.parseInt (t [1]) - 1;
      
      list [B] = A;
      indegree [A]++;
    }
    
    t = in.readLine ().split (" ");
    
    for (int n = 0; n < N; n++) {
      cache [n] = Integer.parseInt (t [n]);
      
      if (indegree [n] == 0) {
        order.add (n);
        queue.offer (n);
      }
    }
    
    while (!queue.isEmpty ()) {
      curr = queue.poll ();
      
      if (list [curr] != -1) {
        indegree [list [curr]]--;
        
        if (indegree [list [curr]] == 0) {
          queue.offer (list [curr]);
          order.add (list [curr]);
        }
      }
    }
    
    int max = cache [order.get (0)];
    
    while (!order.isEmpty ()) {
      curr = order.remove (0);
      
      if (list [curr] != -1) {
        cache [list [curr]] += cache [curr];
        max = Math.max (max, cache [list [curr]]);
      }
    }
    
    System.out.print (max);
  }
}