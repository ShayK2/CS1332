import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

/**
 * Junit test set for Circular Singly LinkedList
 * The tests are written to provide coverage of all methods in HW2
 * according to the LinkedListInterface javadoc
 *
 * @author Tania Nasser
 * @version 1.0
 */
public class LinkedListStudentTests {

    private LinkedListInterface<String> list;

    public static final int TIMEOUT = 200;

    @Before
    public void setUp() {
        list = new SinglyLinkedList2<String>();
    }

    @Test(timeout = TIMEOUT)
    public void testAddStringsIndex() {
        assertEquals(0, list.size());
        assertNull(list.getHead());

        list.addAtIndex("0a", 0); //0a
        list.addAtIndex("1a", 1); //0a 1a
        list.addAtIndex("2a", 2); //0a 1a 2a
        list.addAtIndex("3a", 3); //0a 1a 2a 3a

        assertEquals(4, list.size());

        LinkedListNode<String> current = list.getHead();
        assertNotNull(current);
        assertEquals("0a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("1a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("2a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("3a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertSame(list.getHead(), current);
    }

    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void testAddAtNegativeIndex() {
        assertEquals(0, list.size());
        assertNull(list.getHead());

        list.addAtIndex("0a", 0); //0a
        list.addAtIndex("1a", 1); //0a 1a
        list.addAtIndex("2a", 2); //0a 1a 2a
        list.addAtIndex("3a", 3); //0a 1a 2a 3a

        assertEquals(4, list.size());

        list.addAtIndex("4a", -1);

    }

    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void testAddAtIndexGreaterThanSize() {
        assertEquals(0, list.size());
        assertNull(list.getHead());

        list.addAtIndex("0a", 0); //0a
        list.addAtIndex("1a", 1); //0a 1a
        list.addAtIndex("2a", 2); //0a 1a 2a
        list.addAtIndex("3a", 3); //0a 1a 2a 3a

        assertEquals(4, list.size());

        list.addAtIndex("4a", 5);

    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testAddNullDataAtIndex() {
        assertEquals(0, list.size());
        assertNull(list.getHead());

        list.addAtIndex("0a", 0); //0a
        list.addAtIndex("1a", 1); //0a 1a
        list.addAtIndex("2a", 2); //0a 1a 2a
        list.addAtIndex("3a", 3); //0a 1a 2a 3a

        assertEquals(4, list.size());

        list.addAtIndex(null, 4);

    }

    @Test(timeout = TIMEOUT)
    public void testAddStringsFront() {
        assertEquals(0, list.size());

        list.addToFront("0a");
        list.addToFront("1a");
        list.addToFront("2a");
        list.addToFront("3a");
        list.addToFront("4a");
        list.addToFront("5a"); //5a 4a 3a 2a 1a 0a

        assertEquals(6, list.size());

        LinkedListNode<String> current = list.getHead();
        assertNotNull(current);
        assertEquals("5a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("4a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("3a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("2a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("1a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("0a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertSame(list.getHead(), current);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testAddNullDataToFront() {
        assertEquals(0, list.size());

        list.addToFront("0a");
        list.addToFront("1a");
        list.addToFront("2a"); // 2a 1a 0a

        assertEquals(3, list.size());

        list.addToFront(null);
    }

    @Test(timeout = TIMEOUT)
    public void testAddStringsBack() {
        assertEquals(0, list.size());

        list.addToBack("0a");
        list.addToBack("1a");
        list.addToBack("2a");
        list.addToBack("3a");
        list.addToBack("4a");
        list.addToBack("5a"); //Oa 1a 2a 3a 4a 5a

        assertEquals(6, list.size());

        LinkedListNode<String> current = list.getHead();
        assertNotNull(current);
        assertEquals("0a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("1a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("2a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("3a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("4a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("5a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertSame(list.getHead(), current);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testAddNullDataToBack() {
        assertEquals(0, list.size());

        list.addToBack("0a");
        list.addToBack("1a");
        list.addToBack("2a"); // 2a 1a 0a

        assertEquals(3, list.size());

        list.addToBack(null);
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveStringAtIndex() {
        assertEquals(0, list.size());

        list.addAtIndex("0a", 0);
        list.addAtIndex("1a", 1);
        list.addAtIndex("2a", 2);
        list.addAtIndex("3a", 3);
        list.addAtIndex("4a", 4);
        list.addAtIndex("5a", 5); //0a 1a 2a 3a 4a 5a

        assertEquals(6, list.size());

        assertEquals("2a", list.removeAtIndex(2)); //0a 1a 3a 4a 5a

        LinkedListNode<String> curr = list.getHead();

        assertEquals("0a", curr.getData());

        curr = curr.getNext();
        assertEquals("1a", curr.getData());

        curr = curr.getNext();
        assertEquals("3a", curr.getData());

        curr = curr.getNext();
        assertEquals("4a", curr.getData());

        curr = curr.getNext();
        assertEquals("5a", curr.getData());

        assertEquals(5, list.size());

        LinkedListNode<String> current = list.getHead();
        assertNotNull(current);
        assertEquals("0a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("1a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("3a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("4a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("5a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertSame(list.getHead(), current);
    }

    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void testRemoveAtNegativeIndex() {
        assertEquals(0, list.size());

        list.addAtIndex("0a", 0);
        list.addAtIndex("1a", 1);
        list.addAtIndex("2a", 2);
        list.addAtIndex("3a", 3);
        list.addAtIndex("4a", 4);
        list.addAtIndex("5a", 5); //0a 1a 2a 3a 4a 5a

        assertEquals(6, list.size());

        list.removeAtIndex(-1); //0a 1a 3a 4a 5a
    }

    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void testRemoveAtIndexEqualToSize() {
        assertEquals(0, list.size());

        list.addAtIndex("0a", 0);
        list.addAtIndex("1a", 1);
        list.addAtIndex("2a", 2);
        list.addAtIndex("3a", 3);
        list.addAtIndex("4a", 4);
        list.addAtIndex("5a", 5); //0a 1a 2a 3a 4a 5a

        assertEquals(6, list.size());

        list.removeAtIndex(6); //0a 1a 3a 4a 5a
    }

    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void testRemoveAtIndexGreaterThanSize() {
        assertEquals(0, list.size());

        list.addAtIndex("0a", 0);
        list.addAtIndex("1a", 1);
        list.addAtIndex("2a", 2);
        list.addAtIndex("3a", 3);
        list.addAtIndex("4a", 4);
        list.addAtIndex("5a", 5); //0a 1a 2a 3a 4a 5a

        assertEquals(6, list.size());

        list.removeAtIndex(10); //0a 1a 3a 4a 5a
    }

    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void testRemoveAtIndexFromEmptyList() {
        assertEquals(0, list.size());

        list.removeAtIndex(0); //0a 1a 3a 4a 5a
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveStringFromFront() {
        assertEquals(0, list.size());

        list.addAtIndex("0a", 0);
        list.addAtIndex("1a", 1);
        list.addAtIndex("2a", 2);
        list.addAtIndex("3a", 3);
        list.addAtIndex("4a", 4);
        list.addAtIndex("5a", 5); //0a 1a 2a 3a 4a 5a

        assertEquals(6, list.size());

        assertEquals("0a", list.removeFromFront()); //1a 2a 3a 4a 5a

        assertEquals(5, list.size());

        LinkedListNode<String> current = list.getHead();
        assertNotNull(current);
        assertEquals("1a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("2a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("3a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("4a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("5a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertSame(list.getHead(), current);

        assertEquals("1a", list.removeFromFront()); //2a 3a 4a 5a
        assertEquals("2a", list.getHead().getData());

        assertEquals("2a", list.removeFromFront()); //3a 4a 5a

        assertEquals("3a", list.removeFromFront()); //4a 5a

        assertEquals("4a", list.removeFromFront()); //5a

        assertEquals("5a", list.removeFromFront()); //5a

        assertEquals(null, list.getHead());

    }

    @Test(timeout = TIMEOUT)
    public void testRemoveFromFrontOfEmptyList() {
        assertEquals(0, list.size());

        assertEquals(null, list.removeFromFront()); //0a 1a 3a 4a 5a

    }

    @Test(timeout = TIMEOUT)
    public void testRemoveStringFromBack() {
        assertEquals(0, list.size());

        list.addAtIndex("0a", 0);
        list.addAtIndex("1a", 1);
        list.addAtIndex("2a", 2);
        list.addAtIndex("3a", 3);
        list.addAtIndex("4a", 4);
        list.addAtIndex("5a", 5); //0a 1a 2a 3a 4a 5a

        assertEquals(6, list.size());

        assertEquals("5a", list.removeFromBack()); //0a 1a 2a 3a 4a

        assertEquals(5, list.size());

        LinkedListNode<String> current = list.getHead();
        assertNotNull(current);
        assertEquals("0a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("1a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("2a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("3a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("4a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertSame(list.getHead(), current);

        assertEquals("4a", list.removeFromBack()); //0a 1a 2a 3a
        assertEquals("0a", list.getHead().getData());

        assertEquals("3a", list.removeFromBack()); //0a 1a 2a

        assertEquals("2a", list.removeFromBack()); //0a 1a

        assertEquals("1a", list.removeFromBack()); //0a

        assertEquals("0a", list.removeFromBack()); //

        assertEquals(null, list.getHead());

    }

    @Test(timeout = TIMEOUT)
    public void testRemoveLastOccuranceOfString() {
        assertEquals(0, list.size());

        list.addAtIndex("0a", 0);
        list.addAtIndex("1a", 1);
        list.addAtIndex("2a", 2);
        list.addAtIndex("3a", 3);
        list.addAtIndex("4a", 4);
        list.addAtIndex("5a", 5); //0a 1a 2a 3a 4a 5a

        assertEquals(6, list.size());

        assertEquals("2a", list.removeLastOccurrence("2a")); //0a 1a 3a 4a 5a

        assertEquals(5, list.size());

        LinkedListNode<String> current = list.getHead();
        assertNotNull(current);
        assertEquals("0a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("1a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("3a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("4a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("5a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertSame(list.getHead(), current);

    }

    @Test(timeout = TIMEOUT)
    public void testRemoveLastOccuranceOfStringAtIndex0() {
        assertEquals(0, list.size());

        list.addAtIndex("0a", 0);
        list.addAtIndex("1a", 1);
        list.addAtIndex("2a", 2);
        list.addAtIndex("3a", 3);
        list.addAtIndex("4a", 4);
        list.addAtIndex("5a", 5); //0a 1a 2a 3a 4a 5a

        assertEquals(6, list.size());

        assertEquals("0a", list.removeLastOccurrence("0a")); //0a 1a 3a 4a 5a

        assertEquals(5, list.size());

        LinkedListNode<String> current = list.getHead();
        assertNotNull(current);
        assertEquals("1a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("2a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("3a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("4a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("5a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertSame(list.getHead(), current);

    }

    @Test(timeout = TIMEOUT)
    public void testRemoveLastOccuranceOfStringNotInList() {
        assertEquals(0, list.size());

        list.addAtIndex("0a", 0);
        list.addAtIndex("1a", 1);
        list.addAtIndex("2a", 2);
        list.addAtIndex("3a", 3);
        list.addAtIndex("4a", 4);
        list.addAtIndex("5a", 5); //0a 1a 2a 3a 4a 5a

        assertEquals(6, list.size());

        assertEquals(null, list.removeLastOccurrence("notHere"));

        assertEquals(6, list.size());

        LinkedListNode<String> current = list.getHead();
        assertNotNull(current);
        assertEquals("0a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("1a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("2a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("3a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("4a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("5a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertSame(list.getHead(), current);

    }


    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testRemoveLastOccuranceNullParameter() {
        assertEquals(0, list.size());

        list.addAtIndex("0a", 0);
        list.addAtIndex("1a", 1);
        list.addAtIndex("2a", 2);
        list.addAtIndex("3a", 3);
        list.addAtIndex("4a", 4);
        list.addAtIndex("5a", 5); //0a 1a 2a 3a 4a 5a

        assertEquals(6, list.size());

        list.removeLastOccurrence(null);
    }

    @Test(timeout = TIMEOUT)
    public void testGet() {
        assertEquals(0, list.size());

        list.addAtIndex("0a", 0);
        list.addAtIndex("1a", 1);
        list.addAtIndex("2a", 2);
        list.addAtIndex("3a", 3);
        list.addAtIndex("4a", 4);
        list.addAtIndex("5a", 5); //0a 1a 2a 3a 4a 5a

        assertEquals(6, list.size());

        assertEquals("2a", list.get(2));
    }

    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void testGetAtNegativeIndex() {
        assertEquals(0, list.size());

        list.addAtIndex("0a", 0);
        list.addAtIndex("1a", 1);
        list.addAtIndex("2a", 2);
        list.addAtIndex("3a", 3);
        list.addAtIndex("4a", 4);
        list.addAtIndex("5a", 5); //0a 1a 2a 3a 4a 5a

        assertEquals(6, list.size());

        list.get(-3);
    }

    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void testGetOnEmptyList() {
        assertEquals(0, list.size());
        list.get(0);
    }

    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void testGetAtIndexGreaterThanSize() {
        assertEquals(0, list.size());
        list.addAtIndex("0a", 0);
        list.addAtIndex("1a", 1);
        list.addAtIndex("2a", 2);
        list.addAtIndex("3a", 3);
        list.addAtIndex("4a", 4);
        list.addAtIndex("5a", 5); //0a 1a 2a 3a 4a 5a

        assertEquals(6, list.size());

        list.get(8);
    }

    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void testGetAtIndexEqualToSize() {
        assertEquals(0, list.size());
        list.addAtIndex("0a", 0);
        list.addAtIndex("1a", 1);
        list.addAtIndex("2a", 2);
        list.addAtIndex("3a", 3);
        list.addAtIndex("4a", 4);
        list.addAtIndex("5a", 5); //0a 1a 2a 3a 4a 5a

        assertEquals(6, list.size());

        list.get(6);
    }

    @Test(timeout = TIMEOUT)
    public void testToArray() {
        String[] expectedItems = new String[10];

        for (int x = 0; x < expectedItems.length; x++) {
            expectedItems[x] = "a" + x;
            list.addToBack(expectedItems[x]);
        }

        Object[] array = list.toArray();
        assertArrayEquals(expectedItems, array);
    }

    @Test(timeout = TIMEOUT)
    public void testToArrayEmptyList() {
        String[] expectedItems = new String[0];

        Object[] array = list.toArray();
        assertArrayEquals(expectedItems, array);
    }

    @Test(timeout = TIMEOUT)
    public void testClear() {
        assertEquals(0, list.size());
        list.addAtIndex("0a", 0);
        list.addAtIndex("1a", 1);
        list.addAtIndex("2a", 2);
        list.addAtIndex("3a", 3);
        list.addAtIndex("4a", 4);
        list.addAtIndex("5a", 5); //0a 1a 2a 3a 4a 5a

        assertEquals(6, list.size());

        list.clear();

        assertEquals(0, list.size());
        assertEquals(null, list.getHead());
    }

    @Test(timeout = TIMEOUT)
    public void testIsNotEmpty() {
        assertEquals(0, list.size());
        list.addAtIndex("0a", 0);
        list.addAtIndex("1a", 1);
        list.addAtIndex("2a", 2);
        list.addAtIndex("3a", 3);
        list.addAtIndex("4a", 4);
        list.addAtIndex("5a", 5); //0a 1a 2a 3a 4a 5a

        assertEquals(6, list.size());
        assertEquals(false, list.isEmpty());

    }

    @Test(timeout = TIMEOUT)
    public void testIsEmpty() {
        assertEquals(0, list.size());
        assertEquals(true, list.isEmpty());

    }
}