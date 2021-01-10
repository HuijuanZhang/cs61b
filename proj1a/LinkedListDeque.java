import javax.naming.InsufficientResourcesException;
import java.awt.*;

public class LinkedListDeque<T> {
    private class StuffNode {
        public T item;
        public StuffNode next;
        public StuffNode prev;

        public StuffNode(T i, StuffNode m, StuffNode n) {
            item = i;
            prev = m;
            next = n;
        }
    }

    private StuffNode sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new StuffNode(null, sentinel, sentinel);
        size = 0;
    }

    public LinkedListDeque(T x) {
        sentinel = new StuffNode(null, sentinel, sentinel);
        sentinel.next = new StuffNode(x, sentinel, sentinel);
        sentinel.prev = sentinel.next;
        size = 1;
    }

    public void addFirst(T item) {
        sentinel.next = new StuffNode(item, sentinel, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        size++;
    }

    public void addLast(T item) {
        sentinel.prev = new StuffNode(item, sentinel.prev, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        StuffNode p = sentinel;
        while (p.next != sentinel) {
            System.out.print(p.next.item + " ");
            p = p.next;
        }
        System.out.println("\n");
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T a = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size--;
        return a;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T a = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size--;
        return a;
    }

    public T get(int index) {
        T returnItem = null;
        if (index > size - 1 || size == 0) {
            return null;
        }
        if (index < size / 2) {
            StuffNode p = sentinel;
            for (int i = 0; i <= index; i++) {
                p = p.next;
            }
            returnItem = p.item;
        }
        if (index >= size / 2) {
            StuffNode p = sentinel;
            for (int i = size - 1; i >= index; i--) {
                p = p.prev;
            }
            returnItem = p.item;
        }
        return returnItem;
    }

    public T getRecursive(int index) {
        return getRecursiveHelper(sentinel, index);
    }

    private T getRecursiveHelper(StuffNode p, int index) {
        if (index > size - 1 || index < 0) {
            return null;
        }
        if (index == 0) {
            return p.next.item;
        } else {
            return getRecursiveHelper(p.next, index - 1);
        }
    }

//    public static void main(String[] args) {
//        LinkedListDeque<Integer> L = new LinkedListDeque<Integer>(2);
//        L.addFirst(1);
//        L.addFirst(0);
//        L.addLast(3);
//        L.addLast(4);
//        L.addLast(5);
//        L.printDeque();
//        //L.removeFirst();
//        //L.removeLast();
//        int a = L.getRecursive(3);
//        System.out.println(a);
//    }
}
