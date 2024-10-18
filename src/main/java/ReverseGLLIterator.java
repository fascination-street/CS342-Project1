import java.util.Iterator;

public class ReverseGLLIterator<E> implements Iterator<E> {
    GenericList<E>.Node<E> curr;
    GenericList<E>.Node<E> head;
    GenericList<E>.Node<E> prev_curr;
    public ReverseGLLIterator(GenericList<E> l) {
        curr = l.getHead();
        head = l.getHead();
        prev_curr = l.getHead();
        
        //sets curr to the tail
        while (curr != null) {
            prev_curr = curr;
            curr = curr.next; 
        }
        curr = prev_curr;
    }

    public boolean hasNext() {
        if (curr == head) {return false;}
        else {return true;}
    }

    public E next() {
        E ret = curr.data;
        
        //to get to the node before
        // set curr equal to head, the prev_curr field keeps the previous node mind
        // iterate through the linked list until next node is prev_curr 
        // update prev_curr to be current curr; won't be used again until curr is reassigned to be the head
        curr = head;
        while (curr.next != prev_curr) {curr = curr.next;}
        prev_curr = curr;

        return ret;
    }
}
