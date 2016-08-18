import java.io.*;
import java.util.*;

public class MWC_15_C1_P1_PLAYLIST_PANIC {
  public static void main (String [] t) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    
    int N = Integer.parseInt (in.readLine ());
    int [] songs = new int [N];
    
    for (int n = 0; n < N; n++) {
      t = in.readLine ().split (" ");
      songs [n] = Integer.parseInt (t [0]) * 60 + Integer.parseInt (t [1]);
    }
    
    t = in.readLine ().split (" ");
    int total = Integer.parseInt (t [0]) * 60 + Integer.parseInt (t [1]);
    
    Arrays.sort (songs);
    int max = 0;
    
    for (int s = 0; s < songs.length; s++) {
      if (songs [s] > total) {
        break;
      }
      total -= songs [s];
      max++;
    }
    
    System.out.println (max);
  }
}