import java.io.*;
import java.util.*;

public class CCO_15_P1_CARS_ON_ICE {
  
  static char [][] maze;
  static int [][] lot;
  
  public static void main (String [] t) throws IOException {
    BufferedReader inp = new BufferedReader (new InputStreamReader (System.in));
    t = inp.readLine ().split (" ");
    
    int N = Integer.parseInt (t [0]), M = Integer.parseInt (t [1]), C = 0;
    String ln;
    
    maze = new char [N][M]; lot = new int [N][M];
    
    for (int n = 0; n < N; n++) {
      ln = inp.readLine ();
      
      for (int m = 0; m < M; m++) {
        maze [n][m] = ln.charAt (m);
        
        if (maze [n][m] != '.') {
          lot [n][m] = C;
          //map.put (C, new P (n, m));
          C++;
        }
      }
    }
    
    int [] in = new int [C], to = new int [C], ord = new int [C], xc = new int [C], yc = new int [C];
    int b = 0;
    
    for (int n = 0; n < N; n++) {
      for (int m = 0; m < M; m++) {
        if (maze [n][m] != '.') {
          xc [b] = n; yc [b] = m;
          b++;
        }
        
        if (maze [n][m] == 'N') {
          for (int x = n - 1; x >= 0; x--) {
            if (maze [x][m] != '.') {
              to [lot [n][m]] = lot [x][m];
              in [lot [x][m]]++;
              break;
            }
          }
        }
        else if (maze [n][m] == 'S') {
          for (int x = n + 1; x < N; x++) {
            if (maze [x][m] != '.') {
              to [lot [n][m]] = lot [x][m];
              in [lot [x][m]]++;
              break;
            }
          }
        }
        else if (maze [n][m] == 'E') {
          for (int x = m + 1; x < M; x++) {
            if (maze [n][x] != '.') {
              to [lot [n][m]] = lot [n][x];
              in [lot [n][x]]++;
              break;
            }
          }
        }
        else if (maze [n][m] == 'W') {
          for (int x = m - 1; x >= 0; x--) {
            if (maze [n][x] != '.') {
              to [lot [n][m]] = lot [n][x];
              in [lot [n][x]]++;
              break;
            }
          }
        }
      }
    }
    
    maze = null;
    
    Deque <Integer> queue = new ArrayDeque <Integer> (ord.length);
    
    for (int c = 0; c < ord.length; c++) {
      if (in [c] == 0) {
        queue.offer (c);
      }
    }
    
    int curr, a = 0;
    
    while (!queue.isEmpty ()) {
      curr = queue.poll ();
      in [curr]--;
      in [to [curr]]--;
      
      ord [a] = curr; a++;
      
      if (in [to [curr]] == 0) {
        queue.offerLast (to [curr]);
      }
    }
    
    for (int i = ord.length - 1; i >= 0; i--) {
      System.out.println ("(" + xc [ord [i]] + "," + yc [ord [i]] + ")");
    }
  }
}