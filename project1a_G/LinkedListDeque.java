package com.project1a;

public class LinkedListDeque<Item> {

    public class Node {

        public Node prev;
        public Item item;
        public Node next;

        public Node(Node p, Item i, Node n){
            this.prev = p;
            this.item = i;
            this.next = n;
        }
    }

    /* The first item (if it exists) is at node1.next. */
    private Node node1;
    private int size;

    public LinkedListDeque(){
        size = 0;
        node1 = new Node(this.node1, null, this.node1);
    }

    public boolean isEmpty(){
        return (this.size == 0);

    }


    public void addFirst(Item x) {
        Node firstNode;

        if(this.isEmpty()) {
            firstNode = new Node(this.node1, x, this.node1);
            this.node1.prev = firstNode;
        } else {
            Node oldFirstNode = this.node1.next;
            firstNode = new Node(this.node1, x, oldFirstNode);
            firstNode.next = oldFirstNode;
        }
        this.node1.next = firstNode;
        this.size += 1;

    }

    public void addLast(Item x) {
        Node lastNode;

        if(this.isEmpty()){
            lastNode = new Node(this.node1, x, this.node1);
            this.node1.next = lastNode;
        } else {
            Node oldLastNode = this.node1.prev;
            lastNode = new Node(oldLastNode, x, this.node1);
            oldLastNode.next = lastNode;
        }
        this.node1.prev = lastNode;
        this.size += 1;
    }

    public int size(){
        return this.size;
    }

    public void printDeque(){
        Node n = this.node1.next;

        while (n != node1){
            System.out.println(n.item + " ");
            n = n.next;
        }
    }

    public Item removeFirst(){
        if(this.isEmpty()){
            return null;
        }

        Node first = this.node1.next;

        this.node1.next = first.next;
        first.next.prev = this.node1;

        this.size -= 1;
        return this.node1.next.item;

    }

    public Item removeLast(){
        if(this.isEmpty()){
            return null;
        }
        Node last = this.node1.prev;

        this.node1.prev = last.prev;
        last.prev.next = this.node1;

        this.size -= 1;
        return this.node1.prev.item;
    }

    public Item get(int index){
        if(index >= this.size){
            return null;
        }
        Node n = this.node1.next;

        int i =0;
        while (i != index){
            n = n.next;
            i++;
        }

        return n.item;
    }

    public Item getRecursive (int index){
        if(index >= this.size) {
            return null;
        }

        return recursiveHelper(index, this.node1.next);

    }

    public Item recursiveHelper(int index, Node x){

        if(index == 0){
            return x.item;
        }

        return recursiveHelper(index -1, x.next);

    }

}