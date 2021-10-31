// --== CS400 File Header Information ==--
// Name: Jacky Bai
// Email: bai59@wisc.edu
// Team: red
// Group: AC
// TA: Mu
// Lecturer: Florian Heimerl
// Notes to Grader: None

/**
 * class to store key and value in pair
 *
 * @author Jacky Bai
 * @param <KeyType> key type
 * @param <ValueType> value type
 */
public class HashNode<KeyType, ValueType> {

  private KeyType key;
  private ValueType value;

  public HashNode(KeyType key, ValueType value){
    this.key = key;
    this.value = value;
  }

  public KeyType getKey(){
    return key;
  }

  public ValueType getValue(){
    return value;
  }
}
