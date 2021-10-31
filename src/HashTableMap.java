// --== CS400 File Header Information ==--
// Name: Jacky Bai
// Email: bai59@wisc.edu
// Team: red
// Group: AC
// TA: Mu
// Lecturer: Florian Heimerl
// Notes to Grader: None

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Class that implement MapADT to achieve the function of a hash table
 *
 * @author Jacky Bai
 * @param <KeyType> value type of the key
 * @param <ValueType> value type of the value
 */
public class HashTableMap<KeyType, ValueType> implements MapADT<KeyType, ValueType> {

  public int capacity;
  private int size = 0;
  private LinkedList<HashNode<KeyType, ValueType>>[] nodes;

  /**
   * Constructor method
   * @param capacity capacity of the hash table
   */
  public HashTableMap(int capacity) {
    this.capacity = capacity;
    nodes = new LinkedList[capacity];
  }

  public HashTableMap() {
    capacity = 10;
    nodes = new LinkedList[capacity];

  }// with default capacity = 10

  /**
   * add an element to the hash table
   * @param key key
   * @param value value
   * @return
   */
  @Override
  public boolean put(KeyType key, ValueType value) {
    // if the arguments are invalid or the key is repeated, fail to put.
    if (key == null || value == null) return false;
    // initialize the arraylist
    if(nodes[hashFunction(key)] == null) {
      nodes[hashFunction(key)] = new LinkedList<HashNode<KeyType, ValueType>>();
    }
    nodes[hashFunction(key)].add(new HashNode(key, value));
    // increment the size.
    size++;
    if ((double)size()/(double)capacity >= 0.85) doubleSize(nodes);
    return true;
  }

  /**
   * get the value of a key
   * @param key key
   * @return value of that key
   * @throws NoSuchElementException nothing found
   */
  @Override
  public List<ValueType> get(KeyType key) throws NoSuchElementException {
    List<ValueType> result = new LinkedList<>();
    if(nodes[hashFunction(key)] != null) {
      for (int i = 0; i < nodes[hashFunction(key)].size(); i++) {
        if (nodes[hashFunction(key)].get(i).getKey().equals(key));
          result.add(nodes[hashFunction(key)].get(i).getValue());
        }
      }

    return result;
  }

  /**
   * return the number of elements in the hash table
   * @return the number of elements in the hash table
   */
  @Override
  public int size() {
    return size;
  }

  /**
   * check whether the hash table contains a certain key
   * @param key key
   * @return true or false
   */
  @Override
  public boolean containsKey(KeyType key) {
    if(nodes[hashFunction(key)] != null)
      for(HashNode<KeyType, ValueType> j : nodes[hashFunction(key)]) {
        if(j.getKey().equals(key)) return true; // if equals, return true
      }
    return false; // not found, return false
  }

  /**
   * remove a key with its value
   * @param key key
   * @return removed value
   */
  @Override
  public ValueType remove(KeyType key) {
      if(nodes[hashFunction(key)] != null)
        for(int j  = 0; j < nodes[hashFunction(key)].size(); j++) {
          if(nodes[hashFunction(key)].get(j).getKey().equals(key)) {
            size --;
            return nodes[hashFunction(key)].remove(j).getValue();
          }
        }
    return null;
  }

  /**
   * clear the whole hash table
   */
  @Override
  public void clear() {
    nodes = new LinkedList[capacity];
    this.size = 0;
  }

  /**
   * private helper to double the size of the table and rehash.
   * @param nodes
   */
  private void doubleSize(LinkedList<HashNode<KeyType, ValueType>>[] nodes){
    capacity = capacity * 2;
    LinkedList<HashNode<KeyType, ValueType>>[] temp = new LinkedList[capacity];
    // rehash
    for(int i = 0; i < nodes.length; i++) {
      if(nodes[i] != null)
        for(HashNode<KeyType, ValueType> j : nodes[i]) {
          if(temp[hashFunction(j.getKey())] == null) temp[hashFunction(j.getKey())] = new LinkedList<>();
          temp[hashFunction(j.getKey())].add(j);
        }
    }
    // rehash completed
    this.nodes = temp;
  }
  private int hashFunction (KeyType str){
    return Math.abs(str.hashCode())%capacity;
  }
}
