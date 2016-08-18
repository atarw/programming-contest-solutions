import java.io.*;
import java.util.*;

public class CCC_07_S2_BOXES {
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    int N = Integer.parseInt (in.readLine ());
    int [][] boxes = new int [N][3];
    
    for (int n = 0; n < N; n++) {
      String [] t = in.readLine ().split (" ");
      
      for (int i = 0; i < 3; i++) {
        boxes [n][i] = Integer.parseInt (t [i]);
      }
    }
    
    int M = Integer.parseInt (in.readLine ());
    
    for (int m = 0; m < M; m++) {
      String [] t = in.readLine ().split (" ");
      
      int L = Integer.parseInt (t [0]);
      int W = Integer.parseInt (t [1]);
      int H = Integer.parseInt (t [2]);
      int v = L * W * H;
      
      int bl = -1, bw = -1, bh = -1;
      
      for (int x = 0; x < boxes.length; x++) {
        if (boxes [x][0] * boxes [x][1] * boxes [x][2] >= v && Math.max (boxes [x][0], Math.max (boxes [x][1], boxes [x][2])) >= Math.min (Math.min (W, H), L)) {
          if (bl == -1 || bl * bw * bh > boxes [x][0] * boxes [x][1] * boxes [x][2]) {
            bl = boxes [x][0];
            bw = boxes [x][1];
            bh = boxes [x][2];
          }
        }
      }
      
      if (bl == -1) {
        System.out.println ("Item does not fit.");
      }
      else {
        System.out.println (bl * bw * bh);
      }
    }
  }
}