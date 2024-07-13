package deque;

public class LinkedListDeque<T> implements Deque<T> {
    private static class Node<T> {
        T item;
        Node<T> next;
        Node<T> prev;

        public Node(Node p, T i, Node n) {
            prev = p;
            item = i;
            next = n;
        }
    }
    private Node sentinel;
    private int size;
    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    public void addFirst(T item) {
        sentinel.next = new Node(sentinel, item, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        size++;
    }

    public void addLast(T item) {
        sentinel.prev = new Node(sentinel.prev, item, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
        size++;
    }

    /**public boolean isEmpty() {
        return size == 0;
    }
     */

    public int size() {
        return size;
    }

    public void printDeque() {
        Node p = sentinel.next;
        while (p != sentinel) {
            System.out.print(p.item + " ");
            p = p.next;
        }
        System.out.println();
    }

    public T removeFirst() {
        if (isEmpty()) return null;
        T item = (T)sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size--;
        return item;
    }

    public T removeLast() {
        if (isEmpty()) return null;
        T item = (T)sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size--;
        return item;
    }

    public T get(int index) {
        if (index >= size) return null;
        Node p = sentinel.next;
        while (index > 0) {
            p = p.next;
            index--;
        }
        return (T)p.item;
    }

    private T getRecursiveHelper(Node p, int index) {
        if (index == 0) return (T)p.item;
        return getRecursiveHelper(p.next, index - 1);
    }

    public T getRecursive(int index) {
        if (index >= size) return null;
        return getRecursiveHelper(sentinel.next, index);
    }

}