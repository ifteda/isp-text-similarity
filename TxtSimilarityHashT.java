import java.util.*;
import java.io.*;
/**
 * TxtSimilarityHashT.java
 * 
 * Based on code from Algorithms, 4th Edition by Robert Sedgewick and Kevin Wayne.
 * For additional documentation, "http://algs4.cs.princeton.edu/34hash"
 * 
 * Modified by Ashley Hansberry for Loomis Chaffee ISP, Fall 2015
 */
public class TxtSimilarityHashT<Key, Value> {
    private static final int INIT_CAPACITY = 7;

    private int N;           // number of key-value pairs in the symbol table
    private int M;           // size of linear probing table
    private Key[] keys;      // the keys
    private Value[] vals;    // the values


    /**
     * Initializes an empty symbol table.
     */
    public TxtSimilarityHashT() {
        this(INIT_CAPACITY);
    }

    /**
     * Initializes an empty symbol table of given initial capacity.
     * @param capacity the initial capacity
     */
    public TxtSimilarityHashT(int capacity) {
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

//    /**
//     * Is this symbol table empty?
//     * @return true if this symbol table is empty and false otherwise
//     */
//    public boolean isEmpty() {
//        return size() == 0;
//    }
//
//    /**
//     * Does this symbol table contain the given key?
//     * @param key the key
//     * @return true if this symbol table contains key and
//     *     false otherwise
//     * @throws NullPointerException if key is null
//     */
//    public boolean contains(Key key) {
//        return get(key) != null;
//    }
//
//    // hash function for keys - returns value between 0 and M-1
//    private int hash(Key key) {
//        return (key.hashCode() & 0x7fffffff) % M;
//    }
//
//    // resize the hash table to the given capacity by re-hashing all of the keys
//    private void resize(int capacity) {
//        TxtSimilarityHashT<Key, Value> temp = new TxtSimilarityHashT<Key, Value>(capacity);
//        for (int i = 0; i < M; i++) {
//            if (keys[i] != null) {
//                temp.put(keys[i], vals[i]);
//            }
//        }
//        keys = temp.keys;
//        vals = temp.vals;
//        M    = temp.M;
//    }
//
//    /**
//     * Inserts the key-value pair into the symbol table, overwriting the old value
//     * with the new value if the key is already in the symbol table.
//     * If the value is null, this effectively deletes the key from the symbol table.
//     * @param key the key
//     * @param val the value
//     * @throws NullPointerException if key is null
//     */
//    public void put(Key key, Value val) {
//        if (val == null) {
//            delete(key);
//            return;
//        }
//
//        // double table size if 50% full
//        if (N >= M/2) resize(2*M);
//
//        int i;
//        for (i = hash(key); keys[i] != null; i = (i + 1) % M) {
//            if (keys[i].equals(key)) {
//                vals[i] = val;
//                return;
//            }
//        }
//        keys[i] = key;
//        vals[i] = val;
//        N++;
//    }
//
//    /**
//     * Returns the value associated with the given key.
//     * @param key the key
//     * @return the value associated with the given key if the key is in the symbol table
//     *     and null if the key is not in the symbol table
//     * @throws NullPointerException if key is null
//     */
//    public Value get(Key key) {
//        for (int i = hash(key); keys[i] != null; i = (i + 1) % M) 
//            if (keys[i].equals(key))
//                return vals[i];
//        return null;
//    }
//
//    /**
//     * Removes the key and associated value from the symbol table
//     * (if the key is in the symbol table).
//     * @param key the key
//     * @throws NullPointerException if key is null
//     */
//    public void delete(Key key) {
//        if (!contains(key)) return;
//
//        // find position i of key
//        int i = hash(key);
//        while (!key.equals(keys[i])) {
//            i = (i + 1) % M;
//        }
//
//        // delete key and associated value
//        keys[i] = null;
//        vals[i] = null;
//
//        // rehash all keys in same cluster
//        i = (i + 1) % M;
//        while (keys[i] != null) {
//            // delete keys[i] an vals[i] and reinsert
//            Key   keyToRehash = keys[i];
//            Value valToRehash = vals[i];
//            keys[i] = null;
//            vals[i] = null;
//            N--;  
//            put(keyToRehash, valToRehash);
//            i = (i + 1) % M;
//        }
//
//        N--;        
//
//        // halves size of array if it's 12.5% full or less
//        if (N > 0 && N <= M/8) resize(M/2);
//
//        //assert check();
//    }
    
    
    public static String[] sortArray(String[] s)
    {
      int j;
      boolean flag = true;  // will determine when the sort is finished
      String temp;
      
      while ( flag )
            {
                  flag = false;
                  for ( j = 0;  j < x.length - 1;  j++ )
                  {
                          if ( x [ j ].compareToIgnoreCase( x [ j+1 ] ) > 0 )
                          {                                             // ascending sort
                                      temp = x [ j ];
                                      x [ j ] = x [ j+1];     // swapping
                                      x [ j+1] = temp; 
                                      flag = true;
                           } 
                   } 
            } 
          }
      } return s;
    }
      
//    /**
//     * Returns all keys in the symbol table as an Iterable
//     * To iterate over all of the keys in the symbol table named Iterable,
//     * use the foreach notation: for (Key key : st.keys()).
//     * @return all keys in the sybol table as an Iterable
//     */
//    public Iterable<Key> keys() {
//        ArrayDeque<Key> queue = new ArrayDeque<Key>();
//        for (int i = 0; i < M; i++)
//            if (keys[i] != null) queue.add(keys[i]);
//        return queue;
//    }
//
//    // integrity check - don't check after each put() because
//    // integrity not maintained during a delete()
//    private boolean check() {
//
//        // check that hash table is at most 50% full
//        if (M < 2*N) {
//            System.err.println("Hash table size M = " + M + "; array size N = " + N);
//            return false;
//        }
//
//        // check that each key in table can be found by get()
//        for (int i = 0; i < M; i++) {
//            if (keys[i] == null) continue;
//            else if (get(keys[i]) != vals[i]) {
//                System.err.println("get[" + keys[i] + "] = " + get(keys[i]) + "; vals[i] = " + vals[i]);
//                return false;
//            }
//        }
//        return true;
//    }


    /**
     * Unit tests the TxtSimilarityHashT data type.
     */
    public static void main(String[] args) { 
      LinearProbingHashST textSimHash
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
      commonWords = sortArray(commonWords);   
      for(String element: commonWords)
      {
        System.out.println(element + ", ");
      }
//        TxtSimilarityHashT<String, Integer> st = new TxtSimilarityHashT<String, Integer>();
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