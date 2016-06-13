import java.io.*;

public class CCC_05_P1_RSA_NUMBERS {
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    int A = Integer.parseInt (in.readLine ()), B = Integer.parseInt (in.readLine ());
    int count = 0;
    int factors = 2;
    
    for (int i = A; i <= B; i++) {
      for (int x = 2; x < i && factors <= 4; x++) {
        if (i % x == 0) {
          factors++;
        }
      }
      
      if (factors == 4) {
        count++;
      }
      factors = 2;
    }
    
    System.out.println ("The number of RSA numbers between " + A + " and " + B + " is " + count);
  }
}