package synthesizer;
import java.util.Iterator;

public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        first = 0;
        last = 0;
        this.fillCount = 0;
        this.capacity = capacity;
        rb = (T[]) new Object[capacity];

    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    @Override
    public void enqueue(T x) {
        if (fillCount + 1 > capacity) {
            throw new IllegalArgumentException("Ring Buffer Overflow");
        }
        rb[last] = x;
        last = (last + 1) % capacity;
        fillCount += 1;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    @Override
    public T dequeue() {
        T rtItem;
        if (fillCount == 0) {
            throw new IllegalArgumentException("Ring Buffer Underflow");
        }
        rtItem = rb[first];
        first = (first + 1) % capacity;
        fillCount -= 1;
        return rtItem;
    }

    /**
     * Return oldest item, but don't remove it.
     */
    @Override
    public T peek() {
        T rtItem = null;
        if(!isEmpty()) {
            rtItem = rb[first];
        }
        return rtItem;
    }

    public class ArrayRingBufferIterator implements Iterator<T> {
        private int wizPos;
        private int arrayIndex;

        public ArrayRingBufferIterator() {
            wizPos = 0;
            arrayIndex = first;
        }

        @Override
        public boolean hasNext() {
            return wizPos < fillCount;
        }

        @Override
        public T next() {
            T rtVal = rb[arrayIndex];
            arrayIndex = (arrayIndex + 1) % capacity;
            wizPos += 1;
            return rtVal;
        }
    }

    @Override
    public Iterator<T> iterator() {
        Iterator<T> arrayRingBufferIterator =  new ArrayRingBufferIterator();
        return arrayRingBufferIterator;
    }
}
