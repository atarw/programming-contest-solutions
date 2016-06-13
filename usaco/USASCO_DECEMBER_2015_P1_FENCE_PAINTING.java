import java.io.*;
import java.util.*;

public class USASCO_DECEMBER_2015_P1_FENCE_PAINTING {
  
  public static int units (int A, int B, int C, int D) {
    if (C < B && A < D) {// overlap
      return Math.max (B, D) - Math.min (A, C);
    }
    else {
      return B - A + D - C;
    }
  }
  
  // if (B > C && A < D)
  
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new FileReader (new File ("paint.in"))); //(new InputStreamReader (System.in));
    PrintWriter out = new PrintWriter (new FileWriter (new File ("paint.out"))); //(new OutputStreamWriter (System.out));
    
    int A, B, C, D;
    String [] t = in.readLine ().split (" ");
    A = Integer.parseInt (t [0]);
    B = Integer.parseInt (t [1]);
    
    t = in.readLine ().split (" ");
    C = Integer.parseInt (t [0]);
    D = Integer.parseInt (t [1]);

    out.println (units (A, B, C, D));
    
    out.close ();
    in.close ();
  }
}