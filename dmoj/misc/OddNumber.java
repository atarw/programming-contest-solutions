import java.io.*;
import java.util.*;

public class OddNumber {
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    int N = Integer.parseInt (in.readLine ());
    int C = 0;
    
    for (int i = 0; i < N; i++) {
      C ^= Integer.parseInt (in.readLine ());
    }
    
    System.out.println (C);
  }
}