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

public class HMTest {
    // CONSTRUCTOR TESTS //
    @Test
    void regconstr_test1() {
        MyHashMap<Integer> h = new MyHashMap<Integer>("weezer", 1);
        h.put("the cure", 3);
        h.put("the smiths", 6);
        h.put("smashing pumpkins", 2);
        h.put("pearl jam", 81);

        assert(h.contains("weezer"));
        assert(h.contains("the cure"));
        assert(h.contains("the smiths"));
        assert(h.contains("smashing pumpkins"));
        assert(h.contains("pearl jam"));

        assertEquals(1, h.get("weezer"));
        assertEquals(3,h.get("the cure"));
        assertEquals(6,h.get("the smiths"));
        assertEquals(2,h.get("smashing pumpkins"));
        assertEquals(81,h.get("pearl jam"));

        assertEquals(5, h.size());
        assertFalse(h.isEmpty());
    }

    @Test
    void defconstr_test1() {
        MyHashMap<Integer> h = new MyHashMap<Integer>();

        assertEquals(0, h.size());
        assertTrue(h.isEmpty());

        h.put("american football", 39);

        assertEquals(1, h.size());
        assertFalse(h.isEmpty());
    }
    
    // GET TESTS //
    @Test
    void get_test1() {
        MyHashMap<Integer> h = new MyHashMap<Integer>("weezer", 1);
        h.put("the cure", 3);
        h.put("the smiths", 6);
        h.put("smashing pumpkins", 2);
        h.put("pearl jam", 81);

        assertEquals(1, h.get("weezer"));
        assertEquals(3,h.get("the cure"));
        assertEquals(6,h.get("the smiths"));
        assertEquals(2,h.get("smashing pumpkins"));
        assertEquals(81,h.get("pearl jam"));
        assertNull((h.get("weerez")));
        assertNull((h.get("weeze")));
        assertNull((h.get("")));
    }

    @Test
    void get_test2() {
        MyHashMap<Integer> h = new MyHashMap<Integer>();

        assertNull(h.get("people"));
    }

    // PUT TESTS //
    @Test
    void put_test1() {
        MyHashMap<Integer> h = new MyHashMap<Integer>("weezer", 1);

        h.put("the cure", 3);
        
        assert(h.contains("weezer"));
        assert(h.contains("the cure"));
        assert(!(h.contains("green day")));
    }

    // CONTAINS TESTS //
    @Test 
    void contain_test1() {
        MyHashMap<Integer> h = new MyHashMap<Integer>("weezer", 1);
        h.put("the cure", 3);
        h.put("the smiths", 6);
        h.put("smashing pumpkins", 2);
        h.put("pearl jam", 81);

        assert(h.contains("weezer"));
        assert(h.contains("the cure"));
        assert(h.contains("the smiths"));
        assert(h.contains("smashing pumpkins"));
        assert(h.contains("pearl jam"));
        assert(!(h.contains("weerez")));
        assert(!(h.contains("weeze")));
        assert(!(h.contains("")));
    }

    @Test
    void contain_test2() {
        MyHashMap<Integer> h = new MyHashMap<Integer>();

        assertFalse(h.contains("anything at all"));
    }

    // SIZE TESTS //
    @Test
    void size_test1() {
        MyHashMap<Integer> h = new MyHashMap<Integer>("weezer", 1);
        h.put("the cure", 3);
        h.put("the smiths", 6);
        h.put("smashing pumpkins", 2);
        h.put("pearl jam", 81);

        assertEquals(5, h.size(), "size was returned incorrectly");
    }

    // ISEMPTY TESTS //
    @Test
    void isempty_test1() {
        MyHashMap<Integer> h = new MyHashMap<Integer>("weezer", 1);
        assert(!(h.isEmpty()));
    }

    @Test
    void isempty_test2() {
        MyHashMap<Integer> h = new MyHashMap<Integer>();

        assert(h.isEmpty());
    }

    // REPLACE TESTS //
    @Test
    void replace_test1() {
        MyHashMap<Integer> h = new MyHashMap<Integer>("weezer", 1);
        h.put("the cure", 3);
        h.put("the smiths", 6);
        h.put("smashing pumpkins", 2);
        h.put("pearl jam", 81);

        h.replace("the smiths", 1000);
        h.replace("smashing pumpkins", 199);

        assertEquals(1, h.get("weezer"));
        assertEquals(3,h.get("the cure"));
        assertEquals(1000,h.get("the smiths"));
        assertEquals(199,h.get("smashing pumpkins"));
        assertEquals(81,h.get("pearl jam"));
        assertNull(h.replace("the jonas brothers", 4389523));
    }

    // ITERATOR TESTS //
    @Test
    void hmiterator_test1() {
        ArrayList<Integer> a1 = new ArrayList<>();
        MyHashMap<Integer> h = new MyHashMap<>();
        for (Integer i = 0; i < 100; i++) {
            String is = i.toString();
            h.put(is, i);
            a1.add(i);
            //System.out.println("i.toString = " + is + " i*2 = " + i*2);
        }

        Iterator<Integer> iter = h.iterator();
        ArrayList<Integer> a2 = new ArrayList<>();
    
        while (iter.hasNext()) {
            a2.add(iter.next());
        }

        a2.sort(null);
        
        for (int i = 0; i < 100; i++) {
            assertEquals(a1.get(i), a2.get(i), "expected: " + a1.get(i) + ", got: " + a2.get(i));
        }

        ArrayList<Integer> a3 = new ArrayList<>();
        for (Integer hm : h) {
            a3.add(hm);
        }
        
        a3.sort(null);

        for (int i = 0; i < 100; i++) {
            assertEquals(a1.get(i), a3.get(i), "expected: " + a1.get(i) + ", got: " + a3.get(i));
        }
    }
    
    //this one tests the order
    @Test
    void hmiterator_test2() {
        MyHashMap<Integer> h = new MyHashMap<>();
        for (Integer i = 0; i < 10; i++) {
            String is = i.toString();
            //System.out.println((is.hashCode()%10) + " is the hash code for " + i);
            h.put(is, i);
            //System.out.println("i.toString = " + is + " i*2 = " + i*2);
        }

        ArrayList<Integer> a = new ArrayList<>();
        ArrayList<Integer> ha = new ArrayList<>();

        for (Integer hi : h) {
            ha.add(hi);
        }

        a.add(2); a.add(3); a.add(4); a.add(5); a.add(6); a.add(7); a.add(8); a.add(9); a.add(0); a.add(1);
        for (int i = 0; i < 10; i++) {
            assertEquals(a.get(i), ha.get(i), "expected: " + a.get(i) + ", got: " + ha.get(i));
        }
    }
}
