import javax.naming.InsufficientResourcesException;
import java.awt.*;

public class LinkedListDeque <Blah> {
    private class StuffNode {
        public Blah item;
        public StuffNode next;
        public StuffNode prev;

        public StuffNode(Blah i, StuffNode m, StuffNode n) {
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

    public LinkedListDeque(Blah x) {
        sentinel = new StuffNode(null, sentinel, sentinel);
        sentinel.next = new StuffNode(x, sentinel, sentinel);
        sentinel.prev = sentinel.next;
        size = 1;
    }

    public void addFirst(Blah item) {
        sentinel.next = new StuffNode(item, sentinel, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        size++;
    }

    public void addLast(Blah item) {
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

    public Blah removeFirst() {
        Blah a = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size--;
        return a;
    }

    public Blah removeLast() {
        Blah a = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size--;
        return a;
    }

    public Blah get(int index) {
        if (index > size - 1) {
            return null;
        }
        if (index < size/2) {
            StuffNode p = sentinel;
            for (int i = 0; i <=index; i++) {
                p = p.next;
            }
            return p.item;
        }
        if (index >= size/2) {
            StuffNode p = sentinel;
            for (int i = size - 1; i >= index; i--) {
                p = p.prev;
            }
            return p.item;
        }
        return null;
    }

    public Blah getRecursive(int index) {
        return getRecursiveHelper(sentinel, index);
    }

    public Blah getRecursiveHelper (StuffNode p ,int index) {
        if (index > size -1) {
            return null;
        }
        if(index == 0) {
            return p.next.item;
        } else {
            return getRecursiveHelper(p.next, index - 1);
        }
    }

    public static void main(String[] args) {
        LinkedListDeque<Integer> L = new LinkedListDeque<Integer>(2);
        L.addFirst(1);
        L.addFirst(0);
        L.addLast(3);
        L.addLast(4);
        L.addLast(5);
        L.printDeque();
        //L.removeFirst();
        //L.removeLast();
        int a = L.getRecursive(3);
        System.out.println(a);
    }
}
