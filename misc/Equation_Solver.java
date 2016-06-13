import java.io.*;

public class Equation_Solver {
  
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    String [] tokens = in.readLine ().split (" ");
    int result = Integer.parseInt (tokens [0]);
    
    for (int i = 1; i < tokens.length - 1; i+=2) {
      if (tokens [i].equals ("P")) {
        result = result + Integer.parseInt (tokens [i + 1]);
      }
      else {
        result = result - Integer.parseInt (tokens [i + 1]);
      }
    }
    
    System.out.println (result);
  }
}