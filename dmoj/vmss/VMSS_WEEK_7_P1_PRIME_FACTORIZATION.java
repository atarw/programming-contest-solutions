import java.io.*;

public class VMSS_WEEK_7_P1_PRIME_FACTORIZATION {
  
  public static void primes (int M) {
    for (int i = 2; i <= M; i++) {
      if (M % i == 0) {
        System.out.println (i);
        M /= i;
        i--;
      }
    }
  }
  
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    int M = Integer.parseInt (in.readLine ());
    
    primes (M);
  }
}