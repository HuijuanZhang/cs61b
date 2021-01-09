public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        items = (T []) new Object[8];
        size = 0;
        nextFirst = 4;
        nextLast = 5;
    }

    private void resize(int capacity) {
        T[] a = (T []) new Object[capacity];
        System.arraycopy(items, 0, a, 0, nextFirst + 1);
        int i1 = capacity - items.length + nextLast;
        int i2 = items.length - nextLast;
        System.arraycopy(items, nextLast, a, i1, i2);
        nextFirst = capacity - 1 - items.length + nextLast;
        items = a;
    }

    public void addFirst(T item) {
        items[nextFirst] = item;
        nextFirst--;
        size++;
        if (nextFirst < 0 && size < items.length) {
            nextFirst = items.length - 1;
        } else if (nextFirst == nextLast - 1 && size == items.length) {
            resize(items.length * 2);
        }
    }

    public void addLast(T item) {
        items[nextLast] = item;
        nextLast++;
        size++;
        if (nextLast > items.length - 1 && size < items.length) {
            nextLast = 0;
        } else if (nextFirst == nextLast - 1 && size == items.length) {
            resize(items.length * 2);
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        if (nextFirst < nextLast) {
            int i = nextFirst + 1;
            while (i <= nextLast - 1) {
                System.out.print(items[i] + " ");
                i++;
            }
        } else {
            for (int i = nextFirst + 1; i < items.length; i++) {
                System.out.print(items[i] + " ");
            }
            for (int i = 0; i <= nextLast - 1; i++) {
                System.out.print(items[i] + " ");
            }
        }
        System.out.println("\n");
    }

    public T removeFirst() {
        T returnItem;
        if (nextFirst + 1 == items.length) {
            returnItem = items[0];
            items[0] = null;
            nextFirst = 0;
        } else {
            returnItem = items[nextFirst + 1];
            items[nextFirst + 1] = null;
            nextFirst++;
        }
        size--;
        return returnItem;
    }

    public T removeLast() {
        T returnItem;
        if (nextLast == 0) {
            returnItem = items[0];
            items[0] = null;
            nextLast = items.length - 1;
        } else {
            returnItem = items[nextLast - 1];
            items[nextLast - 1] = null;
            nextLast--;
        }
        size--;
        return returnItem;
    }

    public T get(int index) {
        if (nextFirst < nextLast || index < items.length - nextFirst - 1) {
            return items[nextFirst + index + 1];
        } else {
            return items[index - items.length + nextFirst + 1];
        }
    }

//    public static void main(String[] args) {
//        ArrayDeque A = new ArrayDeque();
//        A.addFirst(1);
//        A.addFirst(2);
//        A.addFirst(3);
//        A.addFirst(4);
//        A.addFirst(5);
//        A.addFirst(6);
//        A.addLast(7);
//        A.addLast(8);
//        A.addLast(9);
//        System.out.println(A.get(6));
//        A.addLast(10);
//        A.addFirst(11);
//        A.removeFirst();
//        A.removeLast();
//        A.printDeque();
//        System.out.println(A.get(6));
//    }
}
