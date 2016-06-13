import java.io.*;
import java.util.*;

public class OddOccurrence {
  
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    String [] t = in.readLine ().split (" ");
    int [] arr = new int [t.length];
    int num = arr [1];
    
    for (int i = 0; i < t.length; i++) {
      arr [i] = Integer.parseInt (t [i]);
    }
    
    for (int i = 2; i < arr.length; i++) {
      num ^= arr [i];
    }
    
    System.out.println (num);
  }
}