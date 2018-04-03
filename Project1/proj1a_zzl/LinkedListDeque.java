public class LinkedListDeque<Type> {
    private Node sentinel;
    private int size;

    public class Node  {
        private Node prev;
        private Type item;
        private Node next;

        public Node(Type item, Node prev, Node next) {
            this.prev = prev;
            this.item = item;
            this.next = next;
        }
        public Node() {
            this.item = null;
            this.next = this;
            this.prev = this;
        }
        public Type getRecursive(int index) {
            if (index == 0)
                return sentinel.next.item;
            return sentinel.next.getRecursive(index - 1);
        }
    }

    /** constructor. */
    public LinkedListDeque() {
        sentinel = new Node();
        this.size = 0;
    }

    public LinkedListDeque(Type x) {
        Node node = new Node(x, sentinel, sentinel);
        sentinel.next = node;
        sentinel.prev = node;
        this.size = 1;
    }

    /** addFirst */
    public void addFirst(Type item) {
        Node node = new Node(item, sentinel, sentinel.next);
        sentinel.next.prev = node;
        sentinel.next = node;
        size += 1;
    }

    /** addLast */
    public void addLast(Type item) {
        Node node = new Node(item, sentinel.prev, sentinel);
        sentinel.prev.next = node;
        sentinel.prev = node;
        size += 1;
    }

    /** is empty? */
    public boolean isEmpty() {
        return this.size == 0;
    }

    /** size */
    public int size() {
        return this.size;
    }

    /** print deque */
    public void printDeque() {
        Node temp = sentinel.next;
        while(temp != sentinel) {
            System.out.print(temp.item + " ");
            temp = temp.next;
        }
    }

    /** remove first*/
    public void removeFirst() {
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size -= 1;
    }

    /** remove last */
    public void removeLast() {
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size -= 1;
    }

    /** get index */
    public Type get(int index) {
        Node temp = sentinel.next;
        while(index != 0) {
            temp = temp.next;
            index -= 1;
        }
        return temp.item;
    }


}

