package com.project1a;

public class ArrayDeque<Item> {

    public static double USEAGE_FACTOR = 0.25;
    public static int RESIZE = 2;

    public Item[] items;
    public int size;
    public int length;
    public int first;
    public int last;

    public ArrayDeque() {

        items = (Item[]) new Object[8];
        size = 0;
        length = 8;
        first = 0;
        last = 1;

    }

    public boolean isEmpty() {
        return (this.size == 0);

    }

    public boolean isFull() {
        return (this.size == length);
    }

    public int move(int index) {
        int prev = index - 1;
        if (prev < 0) {
            prev += this.length;
        }

        return prev;
    }

    public int moveForward(int index) {
        int prev = index + 1;
        if (prev == this.length) {
            prev = 0;
        }
        return prev;
    }

    public void addFirst(Item x) {

        if (isFull()) {
            int newLength = this.length * RESIZE;
        }
        this.items[this.first] = x;
        this.first = move(this.first);
        this.size += 1;

    }

    public void addLast(Item x) {
        if (isFull()) {
            int newLength = this.length * RESIZE;
        }
        this.items[this.last] = x;
        this.last = moveForward(this.last);
        this.size += 1;

    }

    public int size() {
        return this.size;
    }

    public void printDeque() {
        if (first >= last || size == items.length) {
            for (int i = first + 1; i < items.length; i++) {
                System.out.print(items[i] + " ");
            }
            for (int i = 0; i < last; i++) {
                System.out.print(items[i] + " ");
            }
        } else {
            for (int i = first + 1; i < last; i++) {
                System.out.print(items[i] + " ");
            }
        }
    }

    public Item removeFirst() {
        if (this.isEmpty()) {
            return null;
        }

        Item first = this.items[0];

        this.first = this.moveForward(this.first);
        this.size -= 1;

        return first;

    }

    public Item removeLast() {
        if (this.isEmpty()) {
            return null;
        }

        Item last = this.items[this.size - 1];

        this.last = this.move(this.last);
        this.size -= 1;

        return last;

    }

    public Item get(int index) {
        if (index >= this.size) {
            return null;
        }
        int n = (index + this.first + 1) % this.length;

        return this.items[n];
    }
}