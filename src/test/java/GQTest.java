import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Iterator;

public class GQTest {
    @Test
    // CONSTRUCTOR TESTS //
    void GQConstr1_Test1() {
        GenericQueue<Integer> q = new GenericQueue<>(1);

        assertEquals(1, q.getHead().data, "head value is not 1");
        assertEquals(1, q.getTail().data, "tail value is not 1");
        assertNull(q.getHead().next, "head.next is not null");
        assertEquals(q.getHead(), q.getTail(), "head and tail do not refer to the same node");
    }

    @Test
    void GQDefConstr_Test1() {
        GenericQueue<Integer> q = new GenericQueue<>();

        assertNull(q.getHead(), "JAVA DEFAULT CONSTRUCTOR I DONT UNDERSTAND YOU!!!!");
        assertNull(q.getTail(), "tail is not properly defaulted to null");
        assertEquals(0, q.getLength(), "Length should be 0, instead it's" + q.getLength());
    }

    @Test
    void GQCodeConstr_Test1() {
        GenericQueue<Integer> q = new GenericQueue<>(1, 239543);
        assertEquals(1, q.getHead().data, "head data is not 1");
        assertEquals(239543, q.getHead().code, "head code is not 239543");
        assertEquals(q.getHead(), q.getTail(), "head and tail do not refer to the same node");
    }

    // DUMPLIST TESTS //
    @Test
    void dumplist_Test1() {
        GenericQueue<Integer> q = new GenericQueue<>();
        for (int i = 0; i < 100; i++) {
            q.add(i);
        }

        ArrayList<Integer> a = q.dumpList();

        for (int i = 0; i < 100; i++) {
            assertEquals(i, a.get(i), "dumped list does not have the correct value");
        }
    }

    // GET TESTS //
    @Test
    void get_Test1() {
        GenericQueue<Integer> q = new GenericQueue<>();
        for (int i = 0; i < 100; i++) {
            q.add(i);
        }

        for (int i = 0; i < 100; i++) {
            assertEquals(i, q.get(i));
        }
    }

    // SET TESTS //
    @Test
    void set_Test1() {
        GenericQueue<Integer> q = new GenericQueue<>();
        for (int i = 0; i < 11; i++) {
            q.add(i);
        }

        q.set(0, 8);
        q.set(10, 1000);
        q.set(4, 32);

        assertEquals(8, q.get(0));
        assertEquals(1000, q.get(10));
        assertEquals(32, q.get(4));
    }

    // GETLENGTH TESTS //
    @Test
    void getlength_Test1() {
        GenericQueue<Integer> q = new GenericQueue<>();
        for (int i = 0; i < 100; i++) {
            q.add(i);
        }

        assertEquals(100, q.getLength());
    }

    // SETLENGTH TESTS //
    @Test
    void setlength_Test1() {
        GenericQueue<Integer> q = new GenericQueue<>();
        for (int i = 0; i < 100; i++) {
            q.add(i);
        }

        q.setLength(1000000);
        assertEquals(1000000, q.getLength());
    }

    // GETHEAD TESTS //
    @Test
    void gethead_Test1() {
        GenericQueue<Integer> q = new GenericQueue<>();
        for (int i = 0; i < 100; i++) {
            q.add(i);
        }

        assertEquals(0, q.getHead().data);
        assertEquals(1, q.getHead().next.data);
    }

    // SETHEAD TESTS //
    @Test
    void sethead_Test1() {
        GenericQueue<Integer> q = new GenericQueue<>(10, 10);
        q.add(11,11);

        q.setHead(q.getHead().next);

        assertEquals(11, q.getHead().data);
        assertEquals(11, q.getHead().code); 
    }

    // GETTAIL TESTS //
    @Test
    void gettail_Test1() {
        GenericQueue<Integer> q = new GenericQueue<>();
        for (int i = 0; i < 100; i++) {
            q.add(i);
        }

        assertEquals(99, q.getTail().data);
    }

    // ADD TESTS //
    @Test
    void add_test1() {
        GenericQueue<Integer> q = new GenericQueue<>(5);

        q.add(6);

        assertEquals(6, q.get(1), "6 is not at index 1");
        assertEquals(5, q.getHead().data, "head is not 5");
    }

    @Test
    void add_test2() {
        ArrayList<Integer> a = new ArrayList<>();
        GenericQueue<Integer> q = new GenericQueue<>();
        for (int i = 0; i < 100; i++) {
            a.add(i);
            q.add(i);
            assertEquals(i+1, q.getLength(), "length is not accurate");
        }

        ArrayList<Integer> aq = q.dumpList();
        for (int i = 0; i < 100; i++) {
            assertEquals(a.get(i), aq.get(i), "Lists are not equal at index " + i + ". a = " + a.get(i) + ", q = " + aq.get(i));
        }
    }

    @Test
    void add_testCode() {
        ArrayList<Integer> a = new ArrayList<>();
        GenericQueue<Integer> q = new GenericQueue<>();
        for (int i = 0; i < 100; i++) {
            a.add(i);
            q.add(i, i*2);
            assertEquals(i+1, q.getLength(), "length is not accurate");
        }

        ArrayList<Integer> aq = q.dumpList();
        for (int i = 0; i < 100; i++) {
            assertEquals(a.get(i), aq.get(i), "Lists are not equal at index " + i + ". a = " + a.get(i) + ", q = " + aq.get(i));
        }

        for (int i = 0; i < 100; i++) {
            assertEquals(i*2, q.getHead().code, "codes are not accurate, expected: " + (i*2) + ", got: " + q.getHead().code);
            q.delete();
        }
    }

    // DELETE TESTS //
    @Test
    void delete_test1() {
        GenericQueue<Integer> q = new GenericQueue<>();
        assertNull(q.delete(), "delete did not return null for empty queue");
    } 

    @Test
    void delete_test2() {
        GenericQueue<Integer> q = new GenericQueue<>();

        for (int i = 0; i < 10; i++) {
            q.add(i);
        }

        for (int i = 0; i <= 9; i++) {
            assertEquals(10-i, q.getLength(), "length is not accurate, expected = " + (10-i) + ", got = " + q.getLength());
            assertEquals(i, q.delete(), "delete did not return the correct value.");
            if (i != 9) {
                assertEquals(i+1, q.getHead().data, "next head was not assigned properly.");
                assertEquals(9, q.getTail().data, "tail was incorrectly updated");
            }
            //after last removal
            else {
                assertNull(q.getHead(), "delete did not make the head null");
                assertNull(q.getTail(), "tail was not correctly updated to null after final delete");
            }
        }
    }

    // ENQUEUE AND DEQUEUE TESTS //
    //enqueue and dequeue tests will be the same as add and delete tests for obvious reasons
    @Test
    void enqueue_test1() {
        GenericQueue<Integer> q = new GenericQueue<>(5);

        q.enqueue(6);

        assertEquals(6, q.get(1), "6 is not at index 1");
        assertEquals(5, q.getHead().data, "head is not 5");
    }

    @Test
    void enqueue_test2() {
        ArrayList<Integer> a = new ArrayList<>();
        GenericQueue<Integer> q = new GenericQueue<>();
        for (int i = 0; i < 100; i++) {
            a.add(i);
            q.enqueue(i);
            assertEquals(i+1, q.getLength(), "length is not accurate");
        }

        ArrayList<Integer> aq = q.dumpList();
        for (int i = 0; i < 100; i++) {
            assertEquals(a.get(i), aq.get(i), "Lists are not equal at index " + i + ". a = " + a.get(i) + ", q = " + aq.get(i));
        }
    }

    @Test
    void dequeue_test1() {
        GenericQueue<Integer> q = new GenericQueue<>();
        assertNull(q.dequeue(), "dequeue did not return null for empty queue");
    } 

    @Test
    void dequeue_test2() {
        GenericQueue<Integer> q = new GenericQueue<>();

        for (int i = 0; i < 10; i++) {
            q.enqueue(i);
        }

        for (int i = 0; i <= 9; i++) {
            assertEquals(10-i, q.getLength(), "length is not accurate, expected = " + (10-i) + ", got = " + q.getLength());
            assertEquals(i, q.dequeue(), "dequeue did not return the correct value.");
            if (i != 9) {
                assertEquals(i+1, q.getHead().data, "next head was not assigned properly.");
                assertEquals(9, q.getTail().data, "tail was incorrectly updated");
            }
            //after last removal
            else {
                assertNull(q.getHead(), "dequeue did not make the head null");
                assertNull(q.getTail(), "tail was not correctly updated to null after final delete");
            }
        }
    }

    // FOREACH TESTS //
    @Test
    void queueforeach_test1() {
        ArrayList<Integer> a = new ArrayList<>();
        GenericQueue<Integer> q = new GenericQueue<>();
        for (int i = 0; i < 100; i++) {
            a.add(i);
            q.add(i);
            assertEquals(i+1, q.getLength(), "length is not accurate");
        }

        int a_i = 0;
        Iterator<Integer> iter = q.iterator();
        while (iter.hasNext()) {
            assertEquals(q.get(a_i),iter.next(), "values are not equal");
            a_i++;
        }

        a_i = 0;
        for (Integer d : q) {
            assertEquals(a.get(a_i),d, "values are not equals. Expected: " + a.get(a_i) + ", Got: " + d);
            a_i++;
        }
    }

    // DESCENDINGITERATOR TEST //
    
    @Test
    void descendingiterator_test() {
        ArrayList<Integer> a = new ArrayList<>();
        GenericQueue<Integer> q = new GenericQueue<>();
        for (int i = 0; i < 100; i++) {
            a.add(i);
            q.add(i);
            assertEquals(i+1, q.getLength(), "length is not accurate");
        }

        int a_i = 99;
        Iterator<Integer> iter = q.descendingIterator();
        while (iter.hasNext()) {
            assertEquals(q.get(a_i),iter.next(), "values are not equal");
            a_i--;
        }
    }
}
