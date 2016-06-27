public class Trie {
  static int [][] trie = new int [1000][26];//set 1000 to max depth of trie
  static int insertionPoint = 1;
  
  //returns longest prefix already in array
  public static int insert (String word) {
    int curr = 1, pos, length = 0;
    boolean exists = true;//previously exists
    
    for (int i = 0; i < word.length (); ++i) {
      pos = word.charAt (i) - 'a';
      
      if (trie [curr][pos] == 0) {//no child node at this position
        ++insertionPoint;
        trie [curr][pos] = insertionPoint;
        exists = false;
      }
      else {
        ++length;
      }
      
      curr = trie [curr][pos];//set current node to child node
    }
    
    return length + (exists ? 0 : 1);
  }
  
  public static boolean contains (String word) {
    int curr = 1, pos;
    
    for (int i = 0; i < word.length (); ++i) {
      pos = word.charAt (i) - 'a';
      
      if (trie [curr][pos] == 0) {
        return false;
      }
      
      curr = trie [curr][pos];
    }
    
    return true;
  }
}