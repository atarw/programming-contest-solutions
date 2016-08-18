import java.io.*;

public class FHC_15_R2_LAZY_SORT {
  
  static int [] arr;
  
  public static boolean result (int min, int max, int bot, int top) {
    while (min != 1 || max != arr.length) {
      if (arr [bot] == min - 1) {
        min--;
        bot++;
      }
      else if (arr [bot] == max + 1) {
        max++;
        bot++;
      }
      else if (arr [top] == min - 1) {
        min--;
        top--;
      }
      else if (arr [top] == max + 1) {
        max++;
        top--;
      }
      else {
        return false;
      }
    }
    return true;
  }
  
  public static void main (String [] t) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    int T = Integer.parseInt (in.readLine ());
    
    for (int i = 1; i <= T; i++) {
      arr = new int [Integer.parseInt (in.readLine ())];
      t = in.readLine ().split (" ");
      
      for (int a = 0; a < arr.length; a++) {
        arr [a] = Integer.parseInt (t [a]);
      }
      
      if (result (arr [0], arr [0], 1, arr.length - 1) || result (arr [arr.length - 1], arr [arr.length - 1], 0, arr.length - 2)) {
        System.out.println ("Case #" + i + ": yes");
      }
      else {
        System.out.println ("Case #" + i + ": no");
      }
    }
  }
}