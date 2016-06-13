import java.io.*;

public class THORNHILL_SELECTION_CONTEST_16_P5_MAX_AND_CARDS {
  
  static int [] arr;
  static int [] f;
  
  public static void factorials () {
    f [0] = 1;
    
    for (int i = 1; i < f.length; i++) {
      f [i] = f [i - 1] * (i + 1);
    }
  }
  
  public static void perm (int q)
  {
    int [] perm = new int [arr.length];
    
    for (int k = 0; k < perm.length; k++)
    {
      perm [k] = q / f [perm.length - 1 - k] + 1;
      q = q % f [perm.length - 1 - k];
    }
    
    for (int k = 0; k < perm.length; k++) {
      for (int j = k + 1; j < perm.length; j++) {
        if (perm [j] <= perm [k]) {
          perm [k]++;
        }
      }
    }
    
    for (int k = 0; k < perm.length; k++) {
      System.out.print ((perm [k] > perm.length ? perm [k] - 1 : perm [k]) + " ");
    }
    
    System.out.println ();
  }
  
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    int N = Integer.parseInt (in.readLine ());
    int Q = Integer.parseInt (in.readLine ());
    
    arr = new int [N];
    f = new int [N];
    
    factorials ();
    
    for (int n = 0; n < N; n++) {
      arr [n] = n + 1;
    }
    
    for (int q = 0; q < Q; q++) {
      perm (q);
    }
  }
}