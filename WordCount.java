public class WordCount {
  
  //word to count occurrences of
  private String word;
  
  //number of occurences of word in first document
  private double countA;
  
  //number of occurences of word in second document
  private double countB;
  
  /* constructor
   */
  public WordCount(String w) {
    word = w;
    countA = 0;
    countB = 0;
  }
  
  /* accessor method: returns global variable word
   */
  public String getWord() {
    return word;
  }
  
  /* accessor method: returns global variable countA
   */
  public double getCountA() {
    return countA;
  }
  
  /* accessor method: returns global variable countB
   */
  public double getCountB() {
    return countB;
  }
  
  /* increments global variable countA
   */
  public void incCountA() {
    countA++;
  }
  
  /* increments global variable countB
   */
  public void incCountB() {
    countB++;
  }
}