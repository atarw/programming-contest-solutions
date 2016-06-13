import java.io.*;

public class CCC_03_P2_PICTURE_PERFECT {
  
  public static int [] factors (int C) {
    int [] a = {1, C};
    int rest = C;
    
    for (int i = 2; i * i < rest; i++) {
      if (C % i == 0) {
        a [0] = i;
        a [1] = C / i;
        rest = C / i;
      }
    }
    
    return a;
  } 
  
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    int C = Integer.parseInt (in.readLine ());
    
    while (C != 0) {
      
      int x = (int) (Math.sqrt (C)), y = C / x;
      
      if (x * y != C) {
        int [] a = factors (C);
        x = a [0];
        y = a [1];
      }
      
      int perimeter = x * 2 + y * 2;
      
      System.out.println ("Minimum perimeter is " + perimeter + " with dimensions " + x + " x " + y);
      
      C = Integer.parseInt (in.readLine ());
    }
  }
}