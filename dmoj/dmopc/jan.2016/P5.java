import java.io.*;
import java.util.*;

public class P5 {
  
  static int [][] arr;
  static int [] x, y;
  
  public static void solve (int X, int Y) {
    
    
    x = arr [X];
    y = arr [Y];
  }
  
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    int T = Integer.parseInt (in.readLine ()), X, Y;
    String [] t;
    
    arr = new int [1000000000][10];
    x = new int [10];
    y = new int [10];
    
    for (int i = 0; i < T; i++) {
      t = in.readLine ().split (" ");
      X = Integer.parseInt (t [0]);
      Y = Integer.parseInt (t [1]);
      
      solve (X, Y);
      
      for (int z = 0; z < x.length; z++) {
        System.out.print ((y [z] - x [z]) + " ");
      }
      System.out.println ();
    }
  }
}