import java.io.*;

public class TIC_TAC_MOE {
  
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    int [][] board = new int [5][5];//extra 2 rows and columns to store sum of row/diagonal
    
    for (int x = 1; x <= 3; x++) {
      String ln = in.readLine ();
      for (int y = 1; y <= 3; y++) {
        board [x][y] = ln.charAt (y - 1) == 'O' ? 0 : 1;
        
        board [4][y] += board [x][y];
        board [x][4] += board [x][y];
      }
    }
    
    for (int i = 1; i <= 3; i++) {
      board [4][4] += board [i][i];
    }
    
    for (int i = 3; i >= 0; i--) {
      board [0][4] += board [4 - i][i];
    }
    
    boolean g = false, f = false;
    
    for (int i = 1; i < 5 && !(g && f); i++) {
      if (board [4][i] == 0) {
        g = true;
      }
      else if (board [4][i] == 3) {
        f = true;
      }
    }
    
    if (!(g && f)) {
      for (int i = 0; i < 5 && !(g && f); i++) {
        if (board [i][4] == 0) {
          g = true;
        }
        else if (board [i][4] == 3) {
          f = true;
        }
      }
    }
    
    if (g) {
      if (f) {
        System.out.println ("Error, redo");
      }
      else {
        System.out.println ("Griffy");
      }
    }
    else {
      if (f) {
        System.out.println ("Timothy");
      }
      else {
        System.out.println ("Tie");
      }
    }
  }
}