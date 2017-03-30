import java.util.*;
import java.io.*;

public class Poj2001{ 
  public static void main(String[] args)throws IOException, Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
    String s = null;
    ArrayList <String> list = new ArrayList<String>();
    Trie tri = new Trie();
    while( (s = br.readLine()) != null){
      tri.insert(s);
      list.add(s);
    }
    for(int i = 0; i< list.size(); i++){
      String temp = list.get(i);
      pw.println(temp+" "+tri.prefixSearch(temp));
    }
      
    
    pw.close();
  }
  
  
  static class Trie{
    private Node root;
    public Trie(){
      root = new Node(new Node[26], 0);
    }
    public void insert(String word){
      Node current = root;
      for(int i = 0; i < word.length(); i++){
        if(current.getChildren(word.charAt(i) - 'a') != null){
          current = current.getChildren(word.charAt(i) - 'a');
          current.count++;
        }
        else{
          Node newNode = new Node(new Node[26],1);
          current.setChildren(word.charAt(i) - 'a', newNode);
          current = newNode;
        }
      }
    }
  
    public String prefixSearch(String key){
      String out = "";
      Node current = root;
      for(int i = 0; i <key.length(); i++){
        if(current.count == 1)
          return out;
        out+=""+key.charAt(i);
        current = current.getChildren(key.charAt(i) - 'a');
      }
      return key;
     }
    }
  


  static class Node{
    public Node[] children;
    public int count;
    
    public Node(Node[] child, int cnt){
      children = child;
      count = cnt;
    }
    
    public Node getChildren(int i){
      return children[i];
    }
    public void setChildren(int i, Node n){
      children[i] = n;
    }
  }
  
}