import java.io.*;

public class CCC_09_P4_LIGHTS_GOING_ON_AND_OFF {
  
  static int [] matrix;
  static int [][] cache;
  static int amt = 0;
  
  public static void ways (int row, int above) {
    if (cache [row][above] == 0) {
      cache [row][above] = 1;
      
      if (row == matrix.length) {
        amt++;
      }
      else {
        ways (row + 1, matrix [row] ^ above);//press
        ways (row + 1, matrix [row]);//no press
      }
    }
  }
  
  public static void main (String [] t) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    int R = Integer.parseInt (in.readLine ());
    int L = Integer.parseInt (in.readLine ());
    
    matrix = new int [R];
    cache = new int [R + 1][1 << L];
    
    for (int r = 0; r < R; r++) {
      t = in.readLine ().split (" ");
      
      for (int l = 0; l < L; l++) {
        matrix [r] ^= (-Integer.parseInt (t [l]) << l) & (1 << l);
      }
    }
    
    ways (1, matrix [0]);
    System.out.print (amt);
  }
}