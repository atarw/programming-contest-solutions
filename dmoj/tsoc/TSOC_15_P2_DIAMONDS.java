import java.io.*;

public class TSOC_15_P2_DIAMONDS {
  
  public static void print (int spaces, int H) {
    if (spaces != 0) {
      int lim = (H - spaces) / 2;
      
      for (int i = 0; i < lim; i++)
        System.out.print ('*');
      
      for (int i = 0; i < spaces; i++)
        System.out.print (' ');
      
      for (int i = 0; i < lim; i++)
        System.out.print ('*');
    }
    else {
      for (int i = 0; i < H; i++)
        System.out.print ('*');
    }
    System.out.println ();
  }
  
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    int H = Integer.parseInt (in.readLine ());
    
    print (0, H);
    
    for (int h = 1; h < H; h+=2) {
      print (h, H);
    }
    
    for (int h = H - 2; h >= 1; h-=2) {
      if (h != H - 2)
        print (h, H);
    }
    
    print (0, H);
  }
}