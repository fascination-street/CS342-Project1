import java.util.Iterator;

public class GLLIterator<E> implements Iterator<E> {
    GenericList<E>.Node<E> curr;
    public GLLIterator(GenericList<E> l) {
        curr = l.getHead();
    }

    public boolean hasNext() {
        if (curr == null) {return false;}
        else {return true;}
    }

    public E next() {
        E ret = curr.data;
        curr = curr.next;
        return ret;
    }
}
