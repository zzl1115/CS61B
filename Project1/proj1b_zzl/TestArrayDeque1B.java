import static org.junit.Assert.*;
import org.junit.Test;

/** this flie is to run the junit test of array deque.
 *  Invariants:
  */

public class TestArrayDeque1B {
    @Test
    public void testSize() {
        ArrayDeque<Integer> test = new ArrayDeque<>();
        ArrayDequeSolution<Integer> solution = new ArrayDequeSolution<>();

        for (int i = 0; i < 100; i ++) {
            double numberBetweenZeroAndOne = StdRandom.uniform();

            if (numberBetweenZeroAndOne < 0.25) {
                test.addFirst(i);
                solution.addFirst(i);
                System.out.println("addFirst(" + i + ")");
                assertEquals("Add " + i + " at first", solution.getFirst(), test.get(0));
            }
            else if(numberBetweenZeroAndOne < 0.5) {
                test.addLast(i);
                solution.addLast(i);
                System.out.println("addLast(" + i + ")");
                assertEquals("Add " + i + " at last", solution.getLast(), test.get(test.size() - 1));
            }
            else if(numberBetweenZeroAndOne < 0.75) {
                if(test.size() > 1 && solution.size() > 1) {
                    System.out.println("reMove First");
                    int m = test.removeFirst();
                    int n = solution.removeFirst();
                    assertEquals("Remove first number", n, m);
                }
            }
            else {
                if(test.size() > 1 && solution.size() > 1) {
                    System.out.println("reMove Last");
                    int m = test.removeLast();
                    int n = solution.removeLast();
                    assertEquals("Remove first number", n, m);
                }
            }
        }
    }
//    public static void mian(String[] args) {
//        System.out.println("Run Test:");
//        jh61b.junit.TestRunner.runTests("all", TestArrayDeque1B.class);
//    }
}
