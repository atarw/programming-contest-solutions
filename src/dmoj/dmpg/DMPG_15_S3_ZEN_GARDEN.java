import java.io.*;
import java.util.*;

public class DMPG_15_S3_ZEN_GARDEN {
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    int N = Integer.parseInt (in.readLine ());
    String [] t = in.readLine ().split (" ");
    List <Integer> flowers = new ArrayList <Integer> (N);
    int sum = 0;
    int A;
    
    for (String i : t) {
      A = Integer.parseInt (i);
      flowers.add (A);
      sum += A;
    }
    
    int M = Integer.parseInt (in.readLine ());
    int D;
    
    for (int m = 0; m < M; m++) {
      t = in.readLine ().split (" ");
      A = Integer.parseInt (t [0]);
      D = Integer.parseInt (t [1]);
      
      if (D > flowers.get (A) || D > flowers.get (A - 1)) {
        sum -= Math.min (flowers.get (A), flowers.get (A - 1));
      }
      else {
        sum -= D;
      }
    }
    
    System.out.println (sum);
  }
}