import java.util.Iterator;

class FibonacciIterator implements Iterator<Integer> {
    private int previous = 0;
    private int current = 1;
    private int count = 0;
    private Integer limit;
    
    public FibonacciIterator() {
        this.limit = null;
    }
    
    public FibonacciIterator(int limit) {
        this.limit = limit;
    }
    
    @Override
    public boolean hasNext() {
        return limit == null || count < limit;
    }
    
    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new java.util.NoSuchElementException();
        }
        
        int result = current;
        int next = previous + current;
        previous = current;
        current = next;
        count++;
        
        return result;
    }
}