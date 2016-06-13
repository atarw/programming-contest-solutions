import java.io.*;

public class VMSS_16_W2_P1_G {
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    int N = Integer.parseInt (in.readLine ());
    
    for (int x = 0; x < N; x++) {
      for (int y = 0; y < N * 5; y++) {
        System.out.print ('G');
      }
      System.out.println ();
    }
    
    for (int i = 0; i < N; i++) {
      for (int x = 0; x < N; x++) {
        System.out.print ('G');
      }
      
      for (int x = 0; x < 4 * N; x++) {
        System.out.print ('.');
      }
      
      System.out.println ();
    }
    
    for (int z = 0; z < N; z++) {
      for (int x = 0; x < N; x++) {
        System.out.print ('G');
      }
      
      for (int i = 0; i < N * 2; i++) {
        System.out.print ('.');
      }
      
      for (int i = 0; i < N * 2; i++) {
        System.out.print ('G');
      }
      System.out.println ();
    }
    
    for (int i = 0; i < N; i++) {
      for (int x = 0; x < N; x++) {
        System.out.print ('G');
      }
      
      for (int x = 0; x < 3 * N; x++) {
        System.out.print ('.');
      }
      
      for (int x = 0; x < N; x++) {
        System.out.print ('G');
      }
      
      System.out.println ();
    }
    
    for (int x = 0; x < N; x++) {
      for (int y = 0; y < N * 5; y++) {
        System.out.print ('G');
      }
      System.out.println ();
    }
  }
}