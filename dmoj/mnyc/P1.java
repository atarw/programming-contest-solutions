import java.io.*;

public class P1 {
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    String [] FUCK_JUSTIN = in.readLine ().split (" ");
    int A = Integer.parseInt (FUCK_JUSTIN [0]);
    int B = Integer.parseInt (FUCK_JUSTIN [1]);
    
    int C = A % B;
    
    if (C < 0) C += B;
    
    System.out.println (C);
  }
}