//NOTE: THIS IS A QUEUE
//FIRST-IN-FIRST-OUT
//YOU SHOULD ALWAYS BE REMOVING THE HEAD!
//ADD TO THE BACK!
public class GenericQueue<T> extends GenericList<T> {
    private Node<T> tail;

    //Constructor 
    public GenericQueue(T data) {
        this.setLength(0);
        this.add(data);
    }
    
    //default, empty queue constructor
    public GenericQueue() {this.setLength(0);}

    //code and data constructor
    public GenericQueue(T data, int code) {
        this.add(data, code);
    }

    //enqueue and dequeue support
    public void enqueue(T data) {this.add(data);}
    public T dequeue() {return this.delete();}

    //add - will create a new node and adjust tail and length fields as needed
    public void add(T data) {
        //for the empty queue
        if (this.getHead() == null) {
            //create new node and set it as both the head and tail
            Node<T> n = new Node<T>();
            n.data = data;
            this.setHead(n);
            this.setLength(this.getLength()+1);
            this.tail = n;
        }
        //otherwise, add node as usual
        else {
            //get the head and go until the end, we add to the back
            Node<T> curr = this.getHead();
            while (curr.next != null) {
                curr = curr.next;
            }
            //instantiate new node and update variables accordingly
            curr.next = new Node<T>();
            curr.next.data = data;
            curr.next.next = null;
            this.setLength(this.getLength()+1);
            this.tail = curr.next;
        }
    }

    //add again, does the same thing but with the code variable as well
    public void add(T data, int code) {
        //for the empty queue
        if (this.getHead() == null) {
            //create new node and set it as both the head and tail
            Node<T> n = new Node<T>();
            n.data = data;
            n.code = code;
            this.setHead(n);
            this.setLength(this.getLength()+1);
            this.tail = n;
        }
        //otherwise, add node as usual
        else {
            //get the head and go until the end, we add to the back
            Node<T> curr = this.getHead();
            while (curr.next != null) {
                curr = curr.next;
            }
            //instantiate new node and update variables accordingly
            curr.next = new Node<T>();
            curr.next.data = data;
            curr.next.code = code;
            curr.next.next = null;
            this.setLength(this.getLength()+1);
            this.tail = curr.next;
        }
    }

    //delete - updates the node after the current head to be the new head
    public T delete() {
        Node<T> ret = this.getHead();
        //can't operate on a null so just return null
        if (ret == null) {return null;}

        //update head and length variables accordingly
        this.setHead(ret.next);
        this.setLength(getLength()-1);
        
        //if ret.next is null then we're removing the last node; update tail
        if (ret.next == null) {
            tail = ret.next;
        }

        //not deleting this old head node is giving me C-anxiety... i hope Java garbage collectors are paid well
        return ret.data;
    }
    
    //I hope final works as expected here...
    //This really won't even be used outside of testing but if "final" acts like const in C++ i'm happy
    final public Node<T> getTail(){
        return this.tail;
    }

}
