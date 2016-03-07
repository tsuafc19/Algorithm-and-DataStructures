import java.util.*; 
import java.util.HashMap; 

class KMPMatch{
  
  
  
  
  
  public static void KmpStringMatcher(String wrd,String pat){
    int wlen=wrd.length();//length of word
    if (wrd == null ||wlen==0 ){
      
      throw new NullPointerException("Word is null ");
      
    }
    else{
      int plen=pat.length();//length of pattern
      
      if (plen == 0 ){
        System.out.println("No pattern  provided") ;  // Immediate match
        
      }else{
        int []table=prefixFunction(pat);//compute prefix table
        int q=0;//length of matched characters so far
        int pos;
        
        for (int i=0;i<wlen;i+=1){//loop through the word
          while(q>0 && pat.charAt(q)!=wrd.charAt(i)){
            q=table[q];//step back
          }
          if(pat.charAt(q)==wrd.charAt(i)){
            q+=1;
          }
          if(q==plen){
            pos=i-q+1;
            System.out.println(" Matched pattern occurs at  position " + pos );
            q=table[q-1];
          }
          
        }
      }
      
    }
  }
  
  
  public static HashMap<Integer, Integer> KmpStringMatch(String wrd,String pat){
    HashMap<Integer, Integer> matches = new HashMap<Integer, Integer>();
    int pos=0;
    int wlen=wrd.length();//length of word
    if (wrd == null || wlen==0 ){
      
      throw new NullPointerException("Word is null ");
      //throw new IllegalArgumentException("The argument cannot be null");
      
    }
    else{
      int plen=pat.length();//length of pattern
      
      
      if (plen == 0 ){
        System.out.println("No pattern  provided") ;  // Immediate match
        
      }else{
        int []table=prefixFunction(pat);//compute prefix table
        int q=0;//length of matched characters so far
        int shift;
        
        for (int i=0;i<wlen;i+=1){//loop through the word
          while(q>0 && pat.charAt(q)!=wrd.charAt(i)){
            q=table[q];//step back
          }
          if(pat.charAt(q)==wrd.charAt(i)){
            q+=1;
          }
          if(q==plen){
            pos+=1;
            shift=i-q+1;
            matches.put(pos,shift);
            q=table[q-1];
          }
          
        }
      }
      
    }
    return matches;
  }
  
  
  public static int[] prefixFunction(String pat){
    
    int len=pat.length();//length of pattern
    int []tab=new int[len];
    tab[0]=0;
    int k=0;//length of match so far
    
    for(int q=1;q<len;q+=1){
      while(k>0 && pat.charAt(k)!=pat.charAt(q)){
        k=tab[k-1];
      }
      if(pat.charAt(k)==pat.charAt(q)){
        k+=1;
      }
      tab[q]=k;
    }
    return tab;  
  }
  
  
  public static void printMap(HashMap<Integer, Integer> mp) {
    Iterator<HashMap.Entry<Integer, Integer>> it = mp.entrySet().iterator();
    while (it.hasNext()) {
      HashMap.Entry<Integer, Integer> pair =it.next();
      System.out.println("occurence "+pair.getKey()+ " of the pattern happens at position " + pair.getValue()+ " of the text");
      it.remove(); // avoids a ConcurrentModificationException
    }
  }
  
  public static void main(String [ ] args)
  {
    
    String word="tetththeheehthtehtheththehehtht";
    String pat="the";
    String empty="";
    String word1=null;
    
    
    KmpStringMatcher(word,empty);
    System.out.println("=================\n") ;
    
    KmpStringMatcher(word,pat);
    System.out.println("=================\n") ;
    
    
    HashMap<Integer, Integer> matches = new HashMap<Integer, Integer>();
    matches=KmpStringMatch(word,pat);
    
    printMap(matches);
    
  }
}