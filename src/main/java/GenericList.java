import java.util.ArrayList;
import java.lang.Iterable;

public abstract class GenericList<T> implements Iterable<T> {

    class Node<T> {
        T data;
        int code;
        Node<T> next;
    }

    private Node<T> head;
    private int length;

    //print() - loops through linked list and prints each element's data
    public void print() {
        Node<T> curr = this.head;
        while (curr != null) {
            System.out.println(curr.data);
            curr = curr.next;
        }
    }

    //dumpList() - loops through linked list to create an equivalent ArrayList, then returns it
    public ArrayList<T> dumpList() {
        ArrayList<T> list = new ArrayList<>();
        Node<T> curr = this.head;
        while (curr != null) {
            list.add(curr.data);
            curr = curr.next;
        }
        return list;
    }

    //get() - returns element at index
    public T get(int index) {
        if (index > length) {return null;}
        Node<T> curr = this.head;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        return curr.data;
    }

    //set() - changes element at index, return previous element
    public T set(int index, T element) {
        if (index > this.length) {
            return null;
        }
        Node<T> curr = this.head;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        T t = curr.data;
        curr.data = element;
        return t;
    }

    //abstract functions
    public abstract void add(T data);
    public abstract T delete();

    //setters and getters
    public int getLength() {return this.length;}
    public void setLength(int l) {this.length = l;}
    public Node<T> getHead() {return this.head;}
    public void setHead(Node<T> h) {this.head = h;}

    //public Iterator<T> descendingIterator() {}
}
