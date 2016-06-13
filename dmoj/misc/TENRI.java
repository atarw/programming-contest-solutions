import java.io.*;

public class TENRI {
  
  static int [] boxes;
  static int [][][] cache;
  
  static void ind (int d) {while (d --> 0) System.out.print ("  ");}
  
  public static int best (int vis, int N, int S, int d) {
    
    ind (d);
    System.out.println (Integer.toBinaryString (vis) + " " + N + " " + S);
    
    if (cache [vis][N][S] == 0) {
      if (vis == cache.length - 1) {
        cache [vis][N][S] = (boxes [N] + S) * (boxes [N] + S) + S;
      }
      else {
        int nvis;
        
        for (int n = 0; n < N; n++) {
          if (((vis >> n) & 1) == 0) {
            nvis = vis;
            nvis |= 1 << n;
            
            for (int n2 = n + 1; n2 < N; n2++) {
              if (((vis >> n2) & 1) == 0) {
                nvis |= 1 << n2;
                
                for (int s = 0; s <= S; s++) {
                  for (int s2 = 0; s2 <= s; s2++) {
                    cache [vis][N][S] = Math.max (cache [vis][N][S], Math.min (best (nvis, n, s2, d + 1) * best (nvis, n2, s - s2, d + 1), (boxes [N] + s) * (boxes [N] + s) + s));
                  }
                }
              }
            }
          }
        }
      }
    }
    
    return cache [vis][N][S];
  }
  
  public static void main (String [] t) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    t = in.readLine ().split (" ");
    
    int N = Integer.parseInt (t [0]), S = Integer.parseInt (t [1]);
    boxes = new int [N];
    
    for (int n = 0; n < N; n++) {
      boxes [n] = Integer.parseInt (in.readLine ());
    }
    
    cache = new int [1 << N][N + 1][S + 1];
    int max = 0;
    
    for (int n = 0; n < N; n++) {
      for (int s = 0; s <= S; s++) {
        max = Math.max (max, Math.min (best (1 << n, n, S - s, 0), (boxes [n] + s) * (boxes [n] + s) + s));
      }
    }
    
    System.out.print (max);
  }
}