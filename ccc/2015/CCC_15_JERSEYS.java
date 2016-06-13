import java.io.*;

public class CCC_15_JERSEYS {
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    int J = Integer.parseInt (in.readLine ());
    int A = Integer.parseInt (in.readLine ());
    
    char [] jerseys = new char [J];
    
    for (int j = 0; j < J; j++) {
      jerseys [j] = in.readLine ().charAt (0);
    }
    
    String [] t;
    int max = 0, pref;
    char size;
    
    for (int a = 0; a < A; a++) {
      t = in.readLine ().split (" ");
      size = t [0].charAt (0);
      pref = Integer.parseInt (t [1]);
      
      if (jerseys [pref - 1] != ' ') {
        if (jerseys [pref - 1] == size || (size == 'M' || size == 'S') && (jerseys [pref - 1] == 'L' || jerseys [pref - 1] == 'M')) {
          jerseys [pref - 1] = ' ';
          max++;
        }
      }
    }
    
    System.out.println (max);
  }
}