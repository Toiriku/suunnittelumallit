public class Main {
    public static void main(String[] args) {
        FibonacciSequence fibonacci = new FibonacciSequence();
        
        System.out.println("First 10 Fibonacci numbers:");
        java.util.Iterator<Integer> iterator = fibonacci.iterator(10);
        
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println();
        
        System.out.println("\nTwo independent iterators:");
        java.util.Iterator<Integer> iter1 = fibonacci.iterator(5);
        java.util.Iterator<Integer> iter2 = fibonacci.iterator(5);
        
        System.out.print("Iterator 1: ");
        while (iter1.hasNext()) {
            System.out.print(iter1.next() + " ");
        }
        System.out.println();
        
        System.out.print("Iterator 2: ");
        while (iter2.hasNext()) {
            System.out.print(iter2.next() + " ");
        }
        System.out.println();
    }
}