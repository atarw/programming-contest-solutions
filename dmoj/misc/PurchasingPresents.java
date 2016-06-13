import java.io.*;
import java.util.*;

public class PurchasingPresents {
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    int N = Integer.parseInt (in.readLine ());
    int C = (int) (Double.parseDouble (in.readLine ()) * 100);
    
    for (int i = 0; i < N; i++) {
      if (C < 0) {
        break;
      }
      C -= Double.parseDouble (in.readLine ()) * 100;
    }
    
    if (C < 0) {
      System.out.println ("Fardin's broke");
    }
    else {
      double c = C / 100.00;
      System.out.printf ("%.2f", c);
    }
    
  }
}