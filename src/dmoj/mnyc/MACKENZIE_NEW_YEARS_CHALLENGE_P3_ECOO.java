import java.io.*;
import java.util.*;

public class MACKENZIE_NEW_YEARS_CHALLENGE_P3_ECOO {
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    int N = Integer.parseInt (in.readLine ());
    
    NavigableSet <Team> gen = new TreeSet <Team> ();
    NavigableSet <Team> girl = new TreeSet <Team> ();
    NavigableSet <String> names = new TreeSet <String> ();
    
    for (int i = 0; i < N; i++) {
      String [] t = in.readLine ().split (" ");
      Team a = new Team (t [0], t [1].equals ("girls") ? true : false, Integer.parseInt (t [2]));
      gen.add (a);
      
      if (a.girl) girl.add (a);
    }
    
    boolean gTeam = false;//because of edge cases...output 2 teams if no grils
    
    if (!girl.isEmpty ()) {
      gen.remove (girl.last ());
      names.add (girl.pollLast ().name);
      gTeam = true;
    }
    
    if (N != 0) {
      while (!gen.isEmpty () && names.size () < (gTeam ? 3 : 2)) {
        names.add (gen.pollLast ().name);
      }
    }
    else {
      System.out.println ("No ECOO :(");
    }
    
    for (String i : names) {
      System.out.println (i);
    }
  }
}

class Team implements Comparable <Team> {
  boolean girl;
  String name;
  int score;
  
  public String toString () {
    return name;
  }
  
  public int hashCode () {
    return score;
  }
  
  public boolean equals (Object o) {
    Team p = ((Team)o);
    return name.equals (p.name) && girl == p.girl && score == p.score;
  }
  
  public int compareTo (Team t) {
    return score - t.score;
  }
  
  public Team (String name, boolean girl, int score) {
    this.name = name;
    this.girl = girl;
    this.score = score;
  }
}