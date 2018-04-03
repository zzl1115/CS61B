/** array based list.
 * @author Zhilong Zheng
 */
public class ArrayDeque<Type> implements Deque<Type> {
    private Type[] items;
    private int front;
    private int back;
    private int size;
    public ArrayDeque() {
        items = (Type[]) new Object[8];
        front = 0;
        back = 1;
        size = 0;
    }
    /*
    public void upsize(int capacity) {
        Type[] a = (Type[]) new Object[capacity];
        System.arraycopy(items, front + 1, a, 0, items.length - front - 1);
        System.arraycopy(items, 0, a, items.length - front - 1, back);
        items = a;
        front = a.length - 1;
        back = size;
    } */

    /** resize the array list to either larger or smaller scale.
     * resize the array list into new scale with parameter capacity.
     * @param capacity non-negative integer
     */
    public void resize(int capacity) {
        Type[] a = (Type[]) new Object[capacity];
        if (front >= back || size == this.items.length) {
            System.arraycopy(items, front + 1, a, 0, items.length - front - 1);
            System.arraycopy(items, 0, a, items.length - front - 1, back);
        }
        else {
            System.arraycopy(items, front + 1, a, 0, this.size);
        }
        items = a;
        front = a.length - 1;
        back = size;
    }

    public void addFirst(Type item) {
        if (size == items.length)
            this.resize(size * 2);
        items[front] = item;
        if (front == 0) {
            front = items.length - 1;
        }
        else {
            front -= 1;
        }
        size += 1;
    }
    public void addLast(Type item) {
        if (size == items.length)
            this.resize(size * 2);
        items[back] = item;
        if (back == items.length - 1) {
            back = 0;
        }
        else {
            back += 1;
        }
        size += 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        if(front >= back || size == items.length) {
            for (int i = front + 1; i < items.length; i++) {
                System.out.print(items[i] + " ");
            }
            for (int i = 0; i < back; i++) {
                System.out.print(items[i] + " ");
            }
        }
        else {
            for (int i = front + 1; i < back; i++) {
                System.out.print(items[i] + " ");
            }
        }
    }

    public Type removeFirst() {
        Type temp;
        if (front == items.length - 1) {
            front = 0;
        }
        else {
            front += 1;
        }
        temp = items[front];
        items[front] = null;
        size -= 1;
        if (size < (items.length * 0.25)) {
            this.resize(items.length / 2);
        }
        return temp;
    }

    public Type removeLast() {
        Type temp;
        if (back == 0) {
            back = items.length - 1;
        }
        else {
            back -= 1;
        }
        temp = items[back];
        items[back] = null;
        size -= 1;
        if (size < (items.length * 0.25)) {
            this.resize(items.length / 2);
        }
        return temp;
    }

    public Type get(int index) {
        if(index >= size){
           return null;
        }
        else if (front >= back) {
            if(index < items.length - front - 1) {
                return items[front + index + 1];
            }
            else {
                return items[index - (items.length - front - 1)];
            }
        }
        else {
            return items[(front + index + 1) % items.length];
        }
    }

//    public static void main(String[] args) {
//        ArrayDeque<Integer> array = new ArrayDeque<Integer>();
//        for (int i = 0; i < 18; i++){
//            array.addFirst(i);
//            array.printDeque();
//            System.out.println();
//        }
//
//        for (int i = 0; i < 18; i++){
//            array.addLast(i);
//            array.printDeque();
//            System.out.println();
//        }
//
//        for (int i = 0; i < 18; i++){
//            System.out.println(array.get(i));
//        }
//
//        for (int i = 0; i < 18; i++){
//            array.removeLast();
//            array.printDeque();
//            System.out.println();
//        }
//        for (int i = 0; i < 18; i++){
//            array.removeFirst();
//            array.printDeque();
//            System.out.println();
//        }

    //   }
}
