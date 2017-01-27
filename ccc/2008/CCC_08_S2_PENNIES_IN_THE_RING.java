import java.io.*;
import java.util.*;

public class CCC_08_S2_PENNIES_IN_THE_RING {
  
  public static int pennies (int radius) {
    int total = 0;
    
    for (int x = 1; x <= radius; x++) {
      total+= Math.sqrt (radius * radius - x * x) + 1;
    }
    
    return total * 4 + 1;
  }
  
  public static void main (String [] args) throws IOException {
    List <Integer> list = new ArrayList <Integer> ();
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    
    String input = in.readLine ();
    
    while (!input.equals ("0")) {
      list.add (Integer.parseInt (input));
      input = in.readLine ();
    }
    
    for (Integer i : list) {
      int answer = pennies (i);
      System.out.println (answer);
    }
  }
}