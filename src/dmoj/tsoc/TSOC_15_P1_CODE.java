import java.io.*;

public class TSOC_15_P1_CODE {
  
  static char [] arr = {' ', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
  
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    int [] array = new int [Integer.parseInt (in.readLine ())];
    
    for (int i = 0; i < array.length; i++) {
      array [i] = Integer.parseInt (in.readLine ());
    }
    
    int pos = 0;
    
    while (array [pos] != 0) {
      pos += array [pos];
      
      if (array [pos] == 0) {
        break;
      }
      else {
        System.out.print (arr [array [pos]]);
        pos++;
      }
    }
  }
}