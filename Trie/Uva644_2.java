import java.util.*;
import java.io.*;
import java.util.LinkedList;

public class Uva644_2{ 
  public static void main(String[] args)throws IOException, Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
    String s = br.readLine();
    int t = 0;
    
  //   while( (s = br.readLine()) != null){
    ArrayList <String> list = new ArrayList<String>();
    Trie tri = new Trie();
    while( !s.equals("9")){
      list.add(s);
      s = br.readLine();
    }
    boolean bol = false;
    
    Collections.sort(list, new StringLengthComparator());
    tri.insert(list.get(0));
    for(int i = 1; i< list.size(); i++){
      String temp = list.get(i);
      if(tri.search(temp)){
        bol = true;
         break;
      }
      tri.insert(temp);
    }
    if(!bol) pw.println("Set "+(++t)+" is immediately decodable");
    else pw.println("Set "+(++t)+" is not immediately decodable");
    
   //  }
    pw.close();
  }
  
  
  static class TrieNode 
  {
    char content; 
    boolean isEnd; 
    int count;  
    LinkedList<TrieNode> childList; 
    
    public TrieNode(char c)
    {
      childList = new LinkedList<TrieNode>();
      isEnd = false;
      content = c;
      count = 0;
    }  
    public TrieNode subNode(char c)
    {
      if (childList != null)
      for (TrieNode eachChild : childList)
        if (eachChild.content == c)
        return eachChild;
      return null;
    }
  }
  
  static class Trie
  {
    private TrieNode root;
    
    public Trie()
    {
      root = new TrieNode(' '); 
    }
    public void insert(String word)
    {
      if (search(word) == true) 
        return;        
      TrieNode current = root; 
      for (char ch : word.toCharArray() )
      {
        TrieNode child = current.subNode(ch);
        if (child != null)
          current = child;
        else 
        {
          current.childList.add(new TrieNode(ch));
          current = current.subNode(ch);
        }
        current.count++;
      }
      current.isEnd = true;
    }
    public boolean search(String word)
  {
    TrieNode current = root;  
    for (char ch : word.toCharArray() )
    {
      if (current.subNode(ch) == null)
        return false;
      else
        current = current.subNode(ch);
    }      
//    if (current.isEnd == true) 
//      return true;
    return true;
  }
  }
  
  
  static  class StringLengthComparator implements Comparator<String> {
    public int compare(String o1, String o2) {
      if (o1.length() < o2.length()) {
        return 1;
      } else if (o1.length() > o2.length()) {
        return -1;
      } else {
        return 0;
      }
    }
  }
}
