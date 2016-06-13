import java.io.*;
import java.util.*;

public class P6 {
  
  static boolean [][] arr;
  
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    int T = Integer.parseInt (in.readLine ());
    arr = new boolean [T][T];
    String [] ln;
    
    for (int t = 0; t < T; t++) {
      ln = in.readLine ().split (" ");
      
      for (int x = 0; x < ln.length; x++) {
        arr [t][x] = ln [x].charAt (0) == '1' ? true : false;
      }
    }
    
    int A = Integer.parseInt (in.readLine ());
    ln = in.readLine ().split (" ");
    
    int [] alarms = new int [A];
    
    for (int a = 0; a < A; a++) {
      alarms [a] = Integer.parseInt (ln [x]);
    }
  }
}