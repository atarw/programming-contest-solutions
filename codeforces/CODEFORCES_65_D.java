import java.io.*;
import java.util.*;

public class CODEFORCES_65_D {
  static boolean G, S, H, R;
  static String ln;
  
  /*public static void dfs (int n, int g, int s, int h, int r) {
   if (n == ln.length ()) {
   if (g <= s && g <= h && g <= r) G = true;
   if (s <= g && s <= h && s <= r) S = true;
   if (h <= s && h <= g && h <= r) H = true;
   if (r <= s && r <= h && r <= g) R = true;
   }
   else if (ln.charAt (n) == 'G') {
   dfs (n + 1, g + 1, s, h, r);
   }
   else if (ln.charAt (n) == 'H') {
   dfs (n + 1, g, s, h + 1, r);
   }
   else if (ln.charAt (n) == 'R') {
   dfs (n + 1, g, s, h, r + 1);
   }
   else if (ln.charAt (n) == 'S') {
   dfs (n + 1, g, s + 1, h, r);
   }
   else if (ln.charAt (n) == '?') {
   if (g <= s && g <= h && g <= r) dfs (n + 1, g + 1, s, h, r);
   if (s <= g && s <= h && s <= r) dfs (n + 1, g, s + 1, h, r);
   if (h <= s && h <= g && h <= r) dfs (n + 1, g, s, h + 1, r);
   if (r <= s && r <= h && r <= g) dfs (n + 1, g, s, h, r + 1);
   }
   }*/
  
  public static void main (String [] t) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    in.readLine ();
    ln = in.readLine ();
    
    Queue <Quad> queue = new ArrayDeque <Quad> ();
    Set <Quad> vis = new HashSet <Quad> ();
    Quad q = new Quad (0, 0, 0, 0, 0);
    
    queue.offer (q);
    
    while (!queue.isEmpty () && (!G || !S || !H || !R)) {
      if (vis.add (q = queue.poll ())) {
        if (q.N == ln.length ()) {
          //System.out.println (q.N + " " + q.G + " " + q.S + " " + q.H + " " + q.R);
          
          if (q.G <= q.S && q.G <= q.H && q.G <= q.R) G = true;
          if (q.S <= q.G && q.S <= q.H && q.S <= q.R) S = true;
          if (q.H <= q.S && q.H <= q.G && q.H <= q.R) H = true;
          if (q.R <= q.S && q.R <= q.H && q.R <= q.G) R = true;
        }
        else if (ln.charAt (q.N) == 'G') {
          queue.offer (new Quad (q.N + 1, q.G + 1, q.S, q.H, q.R));
        }
        else if (ln.charAt (q.N) == 'H') {
          queue.offer (new Quad (q.N + 1, q.G, q.S, q.H + 1, q.R));
        }
        else if (ln.charAt (q.N) == 'R') {
          queue.offer (new Quad (q.N + 1, q.G, q.S, q.H, q.R + 1));
        }
        else if (ln.charAt (q.N) == 'S') {
          queue.offer (new Quad (q.N + 1, q.G, q.S + 1, q.H, q.R));
        }
        else if (ln.charAt (q.N) == '?') {        
          if (q.G <= q.S && q.G <= q.H && q.G <= q.R)         queue.offer (new Quad (q.N + 1, q.G + 1, q.S, q.H, q.R));
          if (q.S <= q.G && q.S <= q.H && q.S <= q.R)         queue.offer (new Quad (q.N + 1, q.G, q.S + 1, q.H, q.R));
          if (q.H <= q.S && q.H <= q.G && q.H <= q.R)         queue.offer (new Quad (q.N + 1, q.G, q.S, q.H + 1, q.R));
          if (q.R <= q.S && q.R <= q.H && q.R <= q.G)         queue.offer (new Quad (q.N + 1, q.G, q.S, q.H, q.R + 1));
        }
      }
    }
    
    if (G)
      System.out.println ("Gryffindor");
    if (H)
      System.out.println ("Hufflepuff");
    if (R)
      System.out.println ("Ravenclaw");
    if (S)
      System.out.println ("Slytherin");
  }
}

class Quad {
  int N, G, S, H, R;
  
  public int hashCode () {
    return N * 13 + G * 19 + S * 23 + H * 31 + R * 47;
  }
  
  public boolean equals (Object o) {
    Quad q = (Quad) o;
    return q.N == N && q.G == G && q.S == S && q.H == H && q.R == R;
  }
  
  public Quad (int N, int G, int S, int H, int R) {
    this.N = N; this.G = G; this.S = S; this.H = H; this.R = R;
  }
}