import java.io.*;

public class BUBBLE_SORT {
  
  public static void bubble (int [] arr) {
    for (int i : arr) {
      System.out.print (i + " ");
    }
    System.out.println ();
    
    int t;
    
    for (int x = 0; x < arr.length; x++) {
      for (int i = 0; i < arr.length - 1; i++) {
        if (arr [i] > arr [i + 1]) {
          t = arr [i + 1];
          arr [i + 1] = arr [i];
          arr [i] = t;
          
          for (int z : arr) {
            System.out.print (z + " ");
          }
          System.out.println ();
        }
      }
    }
  }
  
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    in.readLine ();
    
    String [] t = in.readLine ().split (" ");
    int [] arr = new int [t.length];
    
    for (int i = 0; i < t.length; i++) {
      arr [i] = Integer.parseInt (t [i]);
    }
    
    bubble (arr);
  }
}