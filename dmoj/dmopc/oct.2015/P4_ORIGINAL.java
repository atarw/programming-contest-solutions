import java.util.*;
import java.io.*;

public class P4_ORIGINAL {
  
  static int candies;
  static int num;
  static boolean [] sieve;
  
  public static ArrayList <Integer> primes (int N) {
    sieve = new boolean [N + 1];
    ArrayList <Integer> primes = new ArrayList <Integer> (N);
    
    for (int i = 2; i <= N; i++) {
      if (!sieve [i]) {
        for (int x = i, y = 2; x <= N; x = i * y, y++) {
          sieve [x] = true;
        }
        primes.add (i);
      }
    }
    
    primes.trimToSize ();//only reason not to declare as list...
    return primes;
  }
  
  public static int getPossibilities (List <Integer> primes, int R) {
    
    int possibilities = 0;
    
    for (int i = 0; i < primes.size (); i++) {
      
      if (candies - primes.get (i) == 0) {
        possibilities++;
      }
      else if (candies - primes.get (i) >= 1) {
        possibilities += 2;
        
        if ((candies - primes.get (i)) / R > 0) {
          possibilities += 2 * (candies - primes.get (i)) / R;
        }
        else if ((candies - primes.get (i)) % R == 0) {
          possibilities++;
        }
        
        //possibilities += ((candies - primes.get (i)) / (R)) * 2;
      }
    }
    return possibilities;
  }
  
  public static void main (String [] args) throws IOException {
    
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    String [] contents = in.readLine ().split (" ");
    
    candies = Integer.parseInt (contents [0]);
    num = Integer.parseInt (contents [1]);
    
    List <Integer> primes = primes (candies);
    
    // System.out.println (primes);
    //System.out.println (multiples);
    
    System.out.println (getPossibilities (primes, num));
  }
}