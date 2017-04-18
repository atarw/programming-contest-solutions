import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TLE_15_P3_POETRY {
  public static void main (String [] t) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    int N = Integer.parseInt (in.readLine ());
    
    int [] arr = new int [N];
    
    for (int n = 0; n < N; n++) {
      arr [n] = Integer.parseInt (in.readLine ());
    }
    
    t = in.readLine ().split (" ");
    int length = arr [0], ln = 0;
    
    for (int i = 0; i < t.length; i++) {
      if (t [i].length () <= length) {
        length -= t [i].length ();
        System.out.print (t [i]);
        
        if (i + 1 < t.length && t [i + 1].length () + 1 <= length) {
          System.out.print (' ');
          length--;
        }
        else {
          System.out.println ();
          ln = (ln + 1) % N;
          length = arr [ln];
        }
      }
      else {        
        while (t [i].length () > 0) {
          System.out.println (t [i].substring (0, length));
          t [i] = t [i].substring (length);
          ln = (ln + 1) % N;
          length = Math.min (arr [ln], t [i].length ());
        }
        
        length = arr [ln];
      }
    }
  }
}