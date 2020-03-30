package synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Iterator;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {

    @Test
    public void testIterator() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<Integer>(4);
        assertTrue(arb.isEmpty());       //                       (returns true)
        for (int i = 0; i < arb.capacity(); i += 1) {
            arb.enqueue(i);
        }
        Iterator<Integer> arbItor = arb.iterator();
        while(arbItor.hasNext()) {
            System.out.println(arbItor.next());
        }

    }


    @Test
    public void someTest() {
        ArrayRingBuffer arb = new ArrayRingBuffer(4);
        assertTrue(arb.isEmpty());       //                       (returns true)
        for (int i = 0; i < arb.capacity(); i += 1) {
            arb.enqueue(i);
        }
        assertTrue(arb.isFull());


        for (int i = 0; i < arb.capacity(); i += 1) {
            int actual = i;
            assertEquals(actual, arb.peek());
            assertEquals(actual, arb.dequeue());
        }
        assertTrue(arb.isEmpty());
    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
