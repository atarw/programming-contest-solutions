import java.io.*;
import java.util.*;

public class DRAW_DOWN {
  static boolean G, H, R;
  static String ln;
  
  public static void main (String [] t) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    in.readLine ();
    ln = in.readLine ();
    
    Queue <Quad> queue = new ArrayDeque <Quad> ();
    Set <Quad> vis = new HashSet <Quad> ();
    Quad q = new Quad (0, 0, 0, 0);
    
    queue.offer (q);
    
    while (!queue.isEmpty () && (!G || !H || !R)) {
      if (vis.add (q = queue.poll ())) {
        if (q.N == ln.length ()) {
          if (q.G <= q.H && q.G <= q.R) G = true;
          if (q.H <= q.G && q.H <= q.R) H = true;
          if (q.R <= q.H && q.R <= q.G) R = true;
        }
        else if (ln.charAt (q.N) == '1') {
          queue.offer (new Quad (q.N + 1, q.G + 1, q.H, q.R));
        }
        else if (ln.charAt (q.N) == '2') {
          queue.offer (new Quad (q.N + 1, q.G, q.H + 1, q.R));
        }
        else if (ln.charAt (q.N) == '3') {
          queue.offer (new Quad (q.N + 1, q.G, q.H, q.R + 1));
        }
        else if (ln.charAt (q.N) == '~') {        
          if (q.G <= q.H && q.G <= q.R) queue.offer (new Quad (q.N + 1, q.G + 1, q.H, q.R));
          if (q.H <= q.G && q.H <= q.R) queue.offer (new Quad (q.N + 1, q.G, q.H + 1, q.R));
          if (q.R <= q.H && q.R <= q.G) queue.offer (new Quad (q.N + 1, q.G, q.H, q.R + 1));
        }
      }
    }
    
    if (G)
      System.out.println (1);
    if (H)
      System.out.println (2);
    if (R)
      System.out.println (3);
  }
}

class Quad {
  int N, G, H, R;
  
  public int hashCode () {
    return N * 13 + G * 19 + H * 31 + R * 47;
  }
  
  public boolean equals (Object o) {
    Quad q = (Quad) o;
    return q.N == N && q.G == G && q.H == H && q.R == R;
  }
  
  public Quad (int N, int G, int H, int R) {
    this.N = N; this.G = G; this.H = H; this.R = R;
  }
}