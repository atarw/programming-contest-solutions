import java.io.*;
import java.util.*;

public class CHRISTMAS_PRESENTS {
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    int P = Integer.parseInt (in.readLine ());
    int T = Integer.parseInt (in.readLine ());
    Present [] presents = new Present [P];
    Teacher [] teachers = new Teacher [T];
    
    for (int p = 0; p < P; p++) {
      presents [p] = new Present (in.readLine (), Double.parseDouble (in.readLine ()));
    }
    
    for (int t = 0; t < T; t++) {
      teachers [t] = new Teacher (in.readLine (), Integer.parseInt (in.readLine ()));
    }
    
    Arrays.sort (presents);
    Arrays.sort (teachers);
    
    for (int i = 0; i < T; i++) {
      System.out.println (teachers [i].name + " will get a " + presents [i].name);
    }
  }
}

class Present implements Comparable <Present> {
  String name;
  double price;
  
  public int compareTo (Present p) {
    return price > p.price ? 1 : price == p.price ? 0 : -1;
  }
  
  public Present (String name, double price) {
    this.name = name;
    this.price = price;
  }
}

class Teacher implements Comparable <Teacher> {
  String name;
  int rating;
  
  public int compareTo (Teacher p) {
    return rating - p.rating;
  }
  
  public Teacher (String name, int rating) {
    this.name = name;
    this.rating = rating;
  }
}