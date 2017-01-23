import java.io.*;

public class IS_IT_A_TREE {
  
  static byte [][] matrix = new byte [4][4];
  
  public static boolean tree (int x, int y) {
    if (matrix [x][y] == 2) {
      return false;
    }
    else {
      matrix [x][y] = 2;
      boolean result = false;
      
      for (int i = 0; i < matrix.length; i++) {
        if (matrix [x][i] == 1) {
          result |= tree (x, i);
        }
      }
      
      return result;
    }
  }
  
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    String [] t;
    int x = -1, y = -1;
    
    for (int i = 0; i < matrix.length; i++) {
      t = in.readLine ().split (" ");
      
      for (int z = 0; z < matrix.length; z++) {
        matrix [i][z] = (byte) Character.getNumericValue (t [z].charAt (0));
        
        if (matrix [i][z] == 1) {
          x = i;
          y = z;
        }
      }
    }
    
    System.out.print (tree (x, y) ? "Yes" : "No");
  }
}