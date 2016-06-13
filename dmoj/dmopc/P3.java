import java.io.*;
import java.util.*;
import java.math.*;

public class P3 {
  
  public static long amt (long num) {
    long amt = 0;
    long add = 9;
    
    for (int i = 0; i < num; i++) {
      if (i % 2 == 0 && i != 0) {
        add *= 10;
      }
      
      amt += add;
      amt %= 1000000000;
    }
    
    return amt;
  }
  
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    long num = Long.parseLong (in.readLine ());
    
    System.out.println (amt (num));
  }
}