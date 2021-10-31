import java.util.List;
import java.util.NoSuchElementException;

public interface MapADT<Integer, ValueType> {

	public boolean put(Integer key, ValueType value);
	public List<ValueType> get(Integer key) throws NoSuchElementException;
	public int size();
	public boolean containsKey(Integer key);
	public ValueType remove(Integer key);
	public void clear();
	
}