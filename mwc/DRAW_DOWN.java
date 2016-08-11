import java.io.*;
import java.util.*;

public class DRAW_DOWN {
  static boolean c1, c2, c3;
  static String ln;
  
  public static void main (String [] t) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    in.readLine ();
    ln = in.readLine ();
    
    Queue <Quad> queue = new ArrayDeque <Quad> ();
    Set <Quad> vis = new HashSet <Quad> ();
    Quad q = new Quad (0, 0, 0, 0);
    
    queue.offer (q);
    
    while (!queue.isEmpty () && (!c1 || !c2 || !c3)) {
      if (vis.add (q = queue.poll ())) {
        if (q.N == ln.length ()) {
          if (q.c1 <= q.c2 && q.c1 <= q.c3)
            c1 = true;
            
          if (q.c2 <= q.c1 && q.c2 <= q.c3)
            c2 = true;
            
          if (q.c3 <= q.c2 && q.c3 <= q.c1)
            c3 = true;
        }
        else if (ln.charAt (q.N) == '1')
          queue.offer (new Quad (q.N + 1, q.c1 + 1, q.c2, q.c3));
          
        else if (ln.charAt (q.N) == '2')
          queue.offer (new Quad (q.N + 1, q.c1, q.c2 + 1, q.c3));

        else if (ln.charAt (q.N) == '3')
          queue.offer (new Quad (q.N + 1, q.c1, q.c2, q.c3 + 1));

        else if (ln.charAt (q.N) == '~') {        
          if (q.c1 <= q.c2 && q.c1 <= q.c3)
            queue.offer (new Quad (q.N + 1, q.c1 + 1, q.c2, q.c3));
            
          if (q.c2 <= q.c1 && q.c2 <= q.c3)
            queue.offer (new Quad (q.N + 1, q.c1, q.c2 + 1, q.c3));
            
          if (q.c3 <= q.c2 && q.c3 <= q.c1)
            queue.offer (new Quad (q.N + 1, q.c1, q.c2, q.c3 + 1));
        }
      }
    }
    
    if (c1)
      System.out.println (1);
      
    if (c2)
      System.out.println (2);
      
    if (c3)
      System.out.println (3);
  }
}

class Quad {
  int N, c1, c2, c3;
  
  public int hashCode () {
    return N * 13 + c1 * 19 + c2 * 31 + c3 * 47;
  }
  
  public boolean equals (Object o) {
    Quad q = (Quad) o;
    return q.N == N && q.c1 == c1 && q.c2 == c2 && q.c3 == c3;
  }
  
  public Quad (int N, int c1, int c2, int c3) {
    this.N = N; this.c1 = c1; this.c2 = c2; this.c3 = c3;
  }
}