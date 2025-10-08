class FibonacciSequence implements Sequence {
    
    @Override
    public java.util.Iterator<Integer> iterator() {
        return new FibonacciIterator();
    }
    
    public java.util.Iterator<Integer> iterator(int limit) {
        return new FibonacciIterator(limit);
    }
}