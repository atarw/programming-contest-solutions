import java.io.*;
import java.util.*;

public class CCC_14_TRIANGLE_TIMES {
  
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    int A = Integer.parseInt (in.readLine ()), B = Integer.parseInt (in.readLine ()), C = Integer.parseInt (in.readLine ());
    int sum = A + B + C;
    Set <Integer> set = new HashSet <Integer> ();
    
    set.add (A);
    set.add (B);
    set.add (C);
    
    if (sum != 180 || A <= 0 || B <= 0 || C <= 0) {
      System.out.println ("Error");
    }
    else if (A == 60 && set.size () == 1) {
      System.out.println ("Equilateral");
    }
    else if (set.size () == 2) {
      System.out.println ("Isosceles");
    }
    else if (set.size () == 3) {
      System.out.println ("Scalene");
    }
  }
}