import java.io.*;
import java.util.*;

public class DMOPC_14_P1_MEDIAN_MARK {
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    int I = Integer.parseInt (in.readLine ());
    double T = 0;
    
    for (int i = 0; i < I; i++) {
      T += Integer.parseInt (in.readLine ());
    }
    
    int I2 = Integer.parseInt (in.readLine ());
    
    for (int i = 1; i <= I2; i++) {
      T += Integer.parseInt (in.readLine ());
      System.out.printf ("%.3f\n", T / (I + i));
    }
  }
}