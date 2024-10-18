import java.util.ArrayList;
import java.util.Iterator;

public class MyHashMap<T> implements Iterable<T>{
    
    protected ArrayList<GenericQueue<T>> map;

    public MyHashMap(String key, T value) {
        map = new ArrayList<GenericQueue<T>>();
        //initiates the map variable so I can actually operate on it
        for (int i = 0; i < 10; i++) {
            map.add(i, new GenericQueue<>());
        }
        //call to put to instantiate the first variable
        this.put(key, value);
    }

    //default constructor that initializes no queues
    public MyHashMap() {
        map = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            map.add(i, new GenericQueue<>());
        }
    }

    //this method will take a key value pair and create a hash code and hash value using the key passed into the method
    public void put(String key, T value) {
        //generate hash; absolute values because these can be negative
        int hash = Math.abs(key.hashCode());
        
        //pulls the queue at the index indicated by the hash and adds the value
        GenericQueue<T> q = map.get(hash%10);
        q.add(value, hash);
    }

    //checks through the queue at the specified index if the key exists
    public boolean contains(String key) {
        int hash = Math.abs(key.hashCode());

        GenericQueue<T> q = map.get(hash%10);
        
        //loops through the queue to find the exact hash
        if (q != null) {
            GenericQueue<T>.Node<T> curr = q.getHead();
            while (curr != null) {
                if (curr.code == hash) {return true;}
                curr = curr.next;
            }
        }
        return false;
    }

    //checks through the queue at the specific index to find the specific key, then returns its associated value
    public T get(String key) {
        int hash = Math.abs(key.hashCode());

        GenericQueue<T> q = map.get(hash%10);
        
        //loops through the queue to find the exact hash
        if (q != null) {
            GenericQueue<T>.Node<T> curr = q.getHead();
            while (curr != null) {
                if (curr.code == hash) {return curr.data;}
                curr = curr.next;
            }
        }
        return null;
    }

    //totals the lengths of every queue in the map array
    public int size() {
        int total_size = 0;
        //loops through map array to get the length of each queue and totals them
        for (int i = 0; i < 10; i++) {
            total_size += map.get(i).getLength();
        }
        return total_size;
    }

    //returns true if size() is 0, false otherwise
    public boolean isEmpty() {
        if (this.size() == 0) return true;
        return false;
    }

    public T replace(String key, T value) {
        int hash = Math.abs(key.hashCode());

        GenericQueue<T> q = map.get(hash%10);
        
        //loops through the queue to find the exact hash
        if (q != null) {
            GenericQueue<T>.Node<T> curr = q.getHead();
            while (curr != null) {
                //if the key is in the queue, replace it and whatever it's mapped to
                if (curr.code == hash) {
                    T ret = curr.data;
                    curr.data = value;
                    return ret;
                }
                curr = curr.next;
            }
        }
        //returns null if it's not in the map
        return null;
    }

    public Iterator<T> iterator() {
        return new HMIterator<>(this);
    }
}
