import java.io.*;

public class CCC_09_J2_OLD_FISHIN_HOLE {
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    int [] P = {Integer.parseInt (in.readLine ()), Integer.parseInt (in.readLine ()), Integer.parseInt (in.readLine ()), Integer.parseInt (in.readLine ())};
    int C = 0;
    
    for (int i = 0; i <= P [3] / P [0]; i++) {
      for (int x = 0; x <= P [3] / P [1]; x++) {
        for (int y = 0; y <= P [3] / P [2]; y++) {
          if ((i > 0 || x > 0 || y > 0) && i * P [0] + x * P [1] + y * P [2] <= P [3]) {
            C++;
            System.out.println (i + " Brown Trout, " + x + " Northern Pike, " + y + " Yellow Pickerel");
          }
        }
      }
    }
    
    System.out.println ("Number of ways to catch fish: " + C);
  }
}