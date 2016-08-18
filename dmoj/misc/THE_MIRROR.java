import java.io.*;

public class THE_MIRROR {
  
  static int [] amt = new int [500];
  
  public static void precalc () {
    boolean [] primes = new boolean [500];
    int count = 0;
    
    for (int i = 2; i < primes.length; i++) {
      if (!primes [i]) {
        primes [i] = true;
        count++;
        
        for (int x = i, y = 1; x < primes.length; y++, x = i * y) {
          primes [x] = true;
        }
      }
      amt [i] = count;
    }
  }
  
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    int N = Integer.parseInt (in.readLine ());
    String [] t;
    
    int A, B;
    
    precalc ();
    
    for (int n = 0; n < N; n++) {
      t = in.readLine ().split (" ");
      A = Integer.parseInt (t [0]) - 1;
      B = Integer.parseInt (t [1]) - 1;
      
      System.out.println (amt [B] - amt [A]);
    }
  }
}