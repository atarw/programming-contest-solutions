import java.io.*;

public class SHAHIR_IN_A_BOX {
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    int N = Integer.parseInt (in.readLine ());
    String [] t;
    
    int minX = Integer.MAX_VALUE, maxX = Integer.MIN_VALUE, minY = Integer.MAX_VALUE, maxY = Integer.MIN_VALUE;
    int x, y;
    
    for (int n = 0; n < N; n++) {
      t = in.readLine ().split (" ");
      x = Integer.parseInt (t [0]);
      y = Integer.parseInt (t [1]);
      
      minX = Math.min (x, minX);
      minY = Math.min (y, minY);
      
      maxX = Math.max (x, maxX);
      maxY = Math.max (y, maxY);
    }
    
    System.out.println ((maxY - minY) * (maxX - minX));
  }
}