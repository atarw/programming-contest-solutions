import java.io.*;
import java.util.*;

public class CCC_03_J1_TRIDENT {
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    int [] arr = new int [3];
    
    for (int i = 0; i < 3; i++) {
      arr [i] = Integer.parseInt (in.readLine ());
    }
    
    for (int t = 0; t < arr [0]; t++) {
      System.out.printf ("*%" + arr [1] + "s*%" + arr [1]+ "s*\n", "", "");
    }
    
    for (int i = 0; i < arr [1] * 2 + 3; i++) {
      System.out.print ("*");
    }
    
    System.out.println ();
    
    for (int i = 0; i < arr [2]; i++) {
      System.out.printf ("%" + (arr [1] * 2 + 3) / 2 + "s*\n", "");
    }
  }
}