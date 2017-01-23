import java.io.*;

public class DMOPC_15_P4_PERSONAL_ASSISTANT {
  
  static long [] cache;
  static Anime [] a;
  
  public static int search (long r, int s) {
    int low = s, high = a.length - 1, mid = (low + high) / 2;
    boolean found = false;
    
    while (low < mid) {
      System.out.println (low + " " + mid + " " + high);
      
      mid = (low + high) / 2;
      
      if (a [mid].R < r) {
        low = mid + 1;
      }
      else {
        low = mid;
        found = true;
      }
    }
    
    if (found) {
      return mid;
    }
    else {
      return -1;
    }
  }
  
  public static long best (int N) {
    if (cache [N] == 0 && N != a.length) {
      int index = search (a [N].E, N + 1);
      
      if (index != -1) {
        cache [N] = Math.max (a [N].H + best (index), best (N + 1));
      }
    }
    
    return cache [N];
  }
  
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    String [] t;
    
    int N = Integer.parseInt (in.readLine ());
    a = new Anime [N];
    cache = new long [N + 1];
    
    for (int n = 0; n < N; n++) {
      t = in.readLine ().split (" ");
      a [n] = new Anime (Long.parseLong (t [0]), Long.parseLong (t [1]), Long.parseLong (t [2]));
    }
    
    System.out.println (search (3, 0));
    
    //System.out.println (best (0));
  }
}

class Anime {
  long R, E, H;
  
  public Anime (long R, long L, long H) {
    this.R = R;
    this.E = R + L;
    this.H = H;
  }
}