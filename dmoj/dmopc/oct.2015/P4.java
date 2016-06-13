import java.util.*;
import java.io.*;

public class P4 {
  
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
    
    //   System.out.println (primes);
    
    primes.trimToSize ();//only reason not to declare as list...
    return primes;
  }
  
  public static List <Integer> getMultiples (int max) {
    List <Integer> m1 = new ArrayList <Integer> ();
    int multiplication = 0;
    
    for (int i = 1; i <= max && multiplication <= max; i++) {
      multiplication = num * i;
      
      if (multiplication <= max) {
        m1.add (multiplication);
      }
    }
    return m1;
  }
  
  public static int getPossibilities (List <Integer> primes, List <Integer> multiples) {
    
    int possibilities = 0;
    
    for (int i = 0; i < primes.size (); i++) {
      //System.out.println ("C: " + candies + " P: " + primes.get (i));
      
      if (candies - primes.get (i) == 0) {
        possibilities++;
      }
      else if (candies - primes.get (i) >= 1) {
        possibilities += 2;
        
        for (int x = 0; x < multiples.size (); x++) {
          if (multiples.get (x) + primes.get (i) < candies) {
            //System.out.println ("PRIME: " + primes.get (i) + " M: " + multiples.get (x) + " ----------- 2");
            possibilities += 2;
          }
          else if (multiples.get (x) + primes.get (i) == candies) {
            //System.out.println ("PRIME: " + primes.get (i) + " M: " + multiples.get (x) +  " ---------- 1");
            possibilities++;
            break;
          }
          else {
            //System.out.println ("PRIME: " + primes.get (i) + " M: " + multiples.get (x) + " --------- 0");
          }
        }
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
    List <Integer> multiples = getMultiples (candies);
    
    // System.out.println (primes);
    //System.out.println (multiples);
    
    System.out.println (getPossibilities (primes, multiples));
  }
}