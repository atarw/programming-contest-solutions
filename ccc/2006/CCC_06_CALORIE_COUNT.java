import java.io.*;

public class CCC_06_CALORIE_COUNT {
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    int I = 0;
    int total = 0;
    
    for (int i = 0; i < 4; i++) {
      I = Integer.parseInt (in.readLine ());
      
      if (i == 0) {
        if (I == 1) {
          total += 461;
        }
        else if (I == 2) {
          total += 431;
        }
        else if (I == 3) {
          total += 420;
        }
      }
      else if (i == 1) {
        if (I == 1) {
          total += 100;
        }
        else if (I == 2) {
          total += 57;
        }
        else if (I == 3) {
          total += 70;
        }
      }
      else if (i == 2) {
        if (I == 1) {
          total += 130;
        }
        else if (I == 2) {
          total += 160;
        }
        else if (I == 3) {
          total += 118;
        }
      }
      else if (i == 3) {
        if (I == 1) {
          total += 167;
        }
        else if (I == 2) {
          total += 266;
        }
        else if (I == 3) {
          total += 75;
        }
      }
    }
    
    System.out.println ("Your total Calorie count is " + total + ".");
  }
}