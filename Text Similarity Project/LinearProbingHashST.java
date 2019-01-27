import java.util.*;
import java.io.*;
/**
 * LinearProbingHashST.java
 * 
 * Based on code from Algorithms, 4th Edition by Robert Sedgewick and Kevin Wayne.
 * For additional documentation, "http://algs4.cs.princeton.edu/34hash"
 * 
 * Modified by Ashley Hansberry for Loomis Chaffee ISP, Fall 2015
 */
public class LinearProbingHashST<Key, Value> {
    private static final int INIT_CAPACITY = 7;

    private int N;           // number of key-value pairs in the symbol table
    private int M;           // size of linear probing table
    private Key[] keys;      // the keys
    private Value[] vals;    // the values


    /**
     * Initializes an empty symbol table.
     */
    public LinearProbingHashST() {
        this(INIT_CAPACITY);
    }

    /**
     * Initializes an empty symbol table of given initial capacity.
     * @param capacity the initial capacity
     */
    public LinearProbingHashST(int capacity) {
        M = capacity;
        keys = (Key[])   new Object[M];
        vals = (Value[]) new Object[M];
    }

    /**
     * Returns the number of key-value pairs in this symbol table.
     * @return the number of key-value pairs in this symbol table
     */
    public int size() {
        return N;
    }

    /**
     * Is this symbol table empty?
     * @return true if this symbol table is empty and false otherwise
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Does this symbol table contain the given key?
     * @param key the key
     * @return true if this symbol table contains key and
     *     false otherwise
     * @throws NullPointerException if key is null
     */
    public boolean contains(Key key) {
        return get(key) != null;
    }

    // hash function for keys - returns value between 0 and M-1
    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    // resize the hash table to the given capacity by re-hashing all of the keys
    private void resize(int capacity) {
        LinearProbingHashST<Key, Value> temp = new LinearProbingHashST<Key, Value>(capacity);
        for (int i = 0; i < M; i++) {
            if (keys[i] != null) {
                temp.put(keys[i], vals[i]);
            }
        }
        keys = temp.keys;
        vals = temp.vals;
        M    = temp.M;
    }

    /**
     * Inserts the key-value pair into the symbol table, overwriting the old value
     * with the new value if the key is already in the symbol table.
     * If the value is null, this effectively deletes the key from the symbol table.
     * @param key the key
     * @param val the value
     * @throws NullPointerException if key is null
     */
    public void put(Key key, Value val) {
        if (val == null) {
            delete(key);
            return;
        }

        // double table size if 50% full
        if (N >= M/2) resize(2*M);

        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % M) {
            if (keys[i].equals(key)) {
                vals[i] = val;
                return;
            }
        }
        keys[i] = key;
        vals[i] = val;
        N++;
    }

    /**
     * Returns the value associated with the given key.
     * @param key the key
     * @return the value associated with the given key if the key is in the symbol table
     *     and null if the key is not in the symbol table
     * @throws NullPointerException if key is null
     */
    public Value get(Key key) {
        for (int i = hash(key); keys[i] != null; i = (i + 1) % M) 
            if (keys[i].equals(key))
                return vals[i];
        return null;
    }

    /**
     * Removes the key and associated value from the symbol table
     * (if the key is in the symbol table).
     * @param key the key
     * @throws NullPointerException if key is null
     */
    public void delete(Key key) {
        if (!contains(key)) return;

        // find position i of key
        int i = hash(key);
        while (!key.equals(keys[i])) {
            i = (i + 1) % M;
        }

        // delete key and associated value
        keys[i] = null;
        vals[i] = null;

        // rehash all keys in same cluster
        i = (i + 1) % M;
        while (keys[i] != null) {
            // delete keys[i] an vals[i] and reinsert
            Key   keyToRehash = keys[i];
            Value valToRehash = vals[i];
            keys[i] = null;
            vals[i] = null;
            N--;  
            put(keyToRehash, valToRehash);
            i = (i + 1) % M;
        }

        N--;        

        // halves size of array if it's 12.5% full or less
        if (N > 0 && N <= M/8) resize(M/2);

        assert check();
    }

    /**
     * Returns all keys in the symbol table as an Iterable
     * To iterate over all of the keys in the symbol table named Iterable,
     * use the foreach notation: for (Key key : st.keys()).
     * @return all keys in the sybol table as an Iterable
     */
    public Iterable<Key> keys() {
        ArrayDeque<Key> queue = new ArrayDeque<Key>();
        for (int i = 0; i < M; i++)
            if (keys[i] != null) queue.add(keys[i]);
        return queue;
    }

    // integrity check - don't check after each put() because
    // integrity not maintained during a delete()
    private boolean check() {

        // check that hash table is at most 50% full
        if (M < 2*N) {
            System.err.println("Hash table size M = " + M + "; array size N = " + N);
            return false;
        }

        // check that each key in table can be found by get()
        for (int i = 0; i < M; i++) {
            if (keys[i] == null) continue;
            else if (get(keys[i]) != vals[i]) {
                System.err.println("get[" + keys[i] + "] = " + get(keys[i]) + "; vals[i] = " + vals[i]);
                return false;
            }
        }
        return true;
    }
    
    /**
     * Recursively sorts given array using merge sort.
     */
    public static void sortArray(String[] a) {
      if (a.length >= 2) {
        String[] left = new String[a.length/2];
        String[] right = new String[a.length-a.length/2];
        for (int i = 0; i < left.length; i++) {
          left[i] = a[i];
        }
        for (int i = 0; i < right.length; i++) {
          right[i] = a[i + a.length/2];
        }
        sortArray(left);
        sortArray(right);
        merge(a, left, right);
      }
    }

    /**
     * Helper method for sortArray().
     */
    public static void merge(String[] result, String[] left, String[] right) {
      int one = 0;
      int two = 0;
      for (int i = 0; i < result.length; i++) {
        if (two >= right.length || 
           (one < left.length && left[one].compareToIgnoreCase(right[two]) < 0)) {
          result[two] = left[one];
          one++;
        } 
        else {
          result[i] = right[two];
          two++;
        }
      }
    }
    
    /**
     * Searches given array for given key.
     * @returns index of key
     */
    public static int binarySearch(String key, String[] arr) {
      return binarySearch(key, arr, 0, arr.length);
    }
    
    /**
     * Recursive helper method for above binarySearch().
     * @returns index of key
     */
    public static int binarySearch(String key, String[] arr, int lo, int hi) {
      if (hi <= lo)
        return -1;
      int mid = (hi + lo)/2;
      int result = arr[mid].compareToIgnoreCase(key);
      if (result > 0)
        return binarySearch(key, arr, lo, mid);
      else if (result < 0)
        return binarySearch(key, arr, mid+1, hi);
      else
        return mid;
    }
      
      
    /**
     * Unit tests the LinearProbingHashST data type.
     */
    public static void main(String[] args) { 
      
      //Creates new hashtable: Key = String, Value = WordCount Object
      LinearProbingHashST<String, WordCount> textSimHash = new LinearProbingHashST<String, WordCount>();
      
      //Creates array of common words from CommonWords.txt
      String[] commonWords = new String[100];
      try{
        FileReader reader = new FileReader("CommonWords.txt");
        BufferedReader buffer = new BufferedReader(reader);
        
        int index = 0;
        String line;
        String firstLetter = "";
        while((line = buffer.readLine()) != null) {
          if (index < 9)
            commonWords[index] = line.substring(2);
          else if (index < 99)
            commonWords[index] = line.substring(3);
          else
            commonWords[index] = line.substring(4);
          index++;
        }
        buffer.close();
      }
      catch(IOException e) {
        System.out.println("File not found.");
      }
      sortArray(commonWords);   
//      for(String element: commonWords)
//      {
//        System.out.print(element + ", ");
//      }
      
      
      //Instructs user to provide names of both documents.
      Scanner scan = new Scanner(System.in);
      System.out.println("Enter name of first document. Include extenstions (e.g. .txt, .rtf, etc.).");
      String doc1Name = scan.next();
      System.out.println("Enter name of second document.");
      String doc2Name = scan.next();
      
      
      //Reads in documents and save them as strings
      String doc1 = "";
      String doc2 = "";
      
      try{
        FileReader reader = new FileReader(doc1Name);
        BufferedReader buffer = new BufferedReader(reader);
        
        String line;
        while((line = buffer.readLine()) != null) {
          doc1 += line + " ";
        }
        buffer.close();
      }
      catch(IOException e) {
        System.out.println("File not found.");
      }
      
      try{
        FileReader reader = new FileReader(doc2Name);
        BufferedReader buffer = new BufferedReader(reader);
        
        String line;
        while((line = buffer.readLine()) != null) {
          doc2 += line + " ";
        }
        buffer.close();
      }
      catch(IOException e) {
        System.out.println("File not found.");
      }
      
      
      //Split strings (at spaces) into arrays
      String[] doc1Arr = doc1.split(" ");
      String[] doc2Arr = doc2.split(" ");
      
      
//      boolean punctuation = true;
//      while (punctuation) {
//        int i = 0;
//        for (String s: doc1Arr) {
//          if (s.charAt(s.length() - 1) == '.' ||
//              s.charAt(s.length() - 1) == '!' ||
//              s.charAt(s.length() - 1) == '?' ||
//              s.charAt(s.length() - 1) == ',' ||
//              s.charAt(s.length() - 1) == '-' ) {
//            s = s.substring(0, s.length() - 1);
//            i++;
//          }
//        }
//        if (i == 0)
//          punctuation = false;
//      }
//      for (String element: doc1Arr) {
//        System.out.println(element + " ");
//      }
      
      /* Asks user if (s)he wants common words to be filtered out.
       * Inserts words into hashtable.
       * Increments countA and countB of WordCount Objects
       */
      boolean filtration = false;
      while(filtration == false) {
        System.out.println("Filter out common words? Type 'yes' or 'no'.");
        String filter = scan.next();
        if(filter.toLowerCase().equals("yes")) {
          for (String s: doc1Arr) {
            if (binarySearch(s, commonWords) == -1) {
              if (!textSimHash.contains(s))
                textSimHash.put(s, new WordCount(s));
              (textSimHash.get(s)).incCountA();
            }
          } 
          for (String s: doc2Arr) {
            if (binarySearch(s, commonWords) >= -1) {
              if (!textSimHash.contains(s))
                textSimHash.put(s, new WordCount(s));
              (textSimHash.get(s)).incCountB();
            }
          }
          filtration = true;
        }
        else if (filter.toLowerCase().equals("no")) {
          for (String s: doc1Arr) {
              if (!textSimHash.contains(s))
                textSimHash.put(s, new WordCount(s));
              (textSimHash.get(s)).incCountA();
          }
          for (String s: doc2Arr) {
              if (!textSimHash.contains(s))
                textSimHash.put(s, new WordCount(s));
              (textSimHash.get(s)).incCountB();
          }
          filtration = true;
        }
        else
          System.out.println("Invalid response.");
      }
        
      /* Retrieves information from WordCount Objects
       * and saves in vectors to be used for cosine similarity
       * and binary cosine similarity calculations.
       */
      int i = 0;
      double[] a = new double[doc1Arr.length];
      double[] b = new double[doc1Arr.length];
      double[] binA = new double[doc1Arr.length];
      double[] binB = new double[doc1Arr.length];
      while (i < a.length) {
        for (String s: doc1Arr) {
          a[i] = (textSimHash.get(s)).getCountA();
          b[i] = (textSimHash.get(s)).getCountB();
          if (a[i] > 0)
            binA[i] = 1;
          else
            binA[i] = 0;
          if (b[i] > 0)
            binB[i] = 1;
          else
            binB[i] = 0;
          i++;
        }
      }
      
      
      // Calculated cosine similarity and binary cosine similarity.
      int n = 0;
      double numerator = 0;
      double bottomA = 0;
      double bottomB = 0;
      double binTop = 0;
      double binBottomA = 0;
      double binBottomB = 0;
      while (n < a.length) { //&& a[n] != null) {
          numerator += (a[n] * b[n]);
          bottomA += (a[n] * a[n]);
          bottomB += (b[n] * b[n]);
          binTop += (binA[n] * binB[n]);
          binBottomA += (binA[n] * binA[n]);
          binBottomB += (binB[n] * binB[n]);
          n++;
      }
      double cosineSim = (numerator / (Math.sqrt(bottomA) * Math.sqrt(bottomB)));
      double binarySim = (binTop / (Math.sqrt(binBottomA) * Math.sqrt(binBottomB)));
      
      //Prints results.
      System.out.println("Cosine Similarity: " + cosineSim);
      System.out.println("Binary Similarity: " + binarySim);
      
      
      
//        LinearProbingHashST<String, Integer> st = new LinearProbingHashST<String, Integer>();
//        Scanner console = new Scanner(System.in);
//        for (int i = 0; console.hasNext(); i++) {
//            String key = console.next();
//            if (key.equals("!q")) {
//                break;
//            }
//            st.put(key, i);
//        }
//
//        // print keys
//        for (String s : st.keys()) 
//            System.out.println(s + " " + st.get(s)); 
    }
}