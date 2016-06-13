import java.io.*;
import java.util.*;

public class PrimeFactorization {
  
  public static void primes (int M) {
    List <Integer> p = new ArrayList <Integer> ();
    
    for (int i = 2; i <= M; i++) {
      if (M % i == 0) {
        p.add (i);
        M /= i;
        i--;
      }
    }
    
    for (Integer i : p) {
      System.out.print (i + " ");
    }
    System.out.println ();
  }
  
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    int M = Integer.parseInt (in.readLine ());
    
    for (int m = 0; m < M; m++) {
      primes (Integer.parseInt (in.readLine ()));
    }
  }
}