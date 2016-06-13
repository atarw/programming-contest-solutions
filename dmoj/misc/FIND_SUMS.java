import java.io.*;
import java.util.*;

public class FIND_SUMS {
  
  public static final Set <Integer> set = new TreeSet <Integer> ();
  public static int ways = 0;
  
  public static String indent (int depth) {
    String n = "";
    
    for (int i = 0; i < depth; i++) {
      n += " ";
    }
    
    return n;
  }
  
  public static int ways (int N, int d) {
    System.out.println (indent (d) + N);
    
    if (N == 0) {
      return 1;
    }
    else if (N < 0) {
      return 0;
    }
    
    if (N - 4 >= 0) {
      System.out.println (indent (d) + "N - 4 = " + (N - 4));
       ways += ways (N - 4, d + 1);
    }
    if (N - 1 >= 0) {
      System.out.println (indent (d) + "N - 1 = " + (N - 1));
       ways += ways (N - 1, d + 1);
    }
    if (N - 3 >= 0) {
      System.out.println (indent (d) + "N - 3 = " + (N - 3));
       ways += ways (N - 3, d + 1);
    }
    return ways;
  }
  
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    int N = Integer.parseInt (in.readLine ());
    ways = ways (N, 0);
    
    System.out.println (ways);
  }
}