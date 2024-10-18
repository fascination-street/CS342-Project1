import java.util.Iterator;
import java.util.ArrayList;

public class HMIterator<E> implements Iterator<E> {
    
    ArrayList<GenericQueue<E>> m;
    GenericQueue<E> curr_q = null;
    Iterator<E> curr_q_iter = null;
    int index = -1;
    public HMIterator(MyHashMap<E> h) {
        m = h.map;
    }

    public boolean hasNext() {
        //We are on the last element IF the index is 9 AND the iterator does NOT have a next
        //supposed to catch it here since next can't, issues are likely if next is used without hasNext for some reason
        if (index == 9 && !(curr_q_iter.hasNext())) {return false;}
        else {return true;}
    }

    public E next() {
        
        //if our iterator exists and can continue, just return its value
        if (curr_q_iter != null && curr_q_iter.hasNext()) {
            return curr_q_iter.next();
        }
        //otherwise, increment index (this is why index is initialized to -1)
        else {
            index++;
            //presumably unnecessary if statement; don't want to fix what isn't broken
            if (index < 10) {
                //if the queue is empty, continue indexing until we have a queue with elements
                while (m.get(index).getLength() == 0) {
                    index++;
                }
                //update the curr variables to reflect new queue
                curr_q = m.get(index);
                curr_q_iter = curr_q.iterator();
            }
            //return our element
            return curr_q_iter.next();
        }
    }
}
