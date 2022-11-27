package implementations;

import interfaces.LinkedList;

import java.util.Iterator;

public class SinglyLinkedList<E> implements LinkedList<E> {
    private Node<E> head;
    private int size;

    public SinglyLinkedList() {
        this.head = null;
        this.size = 0;
    }

    private static class Node<E> {
        private Node<E> next;
        private E value;

        public Node(E value) {
            this.next = null;
            this.value = value;
        }
    }

    @Override
    public void addFirst(E element) {
        Node<E> node = new Node<>(element);
        node.next = this.head;
        this.head = node;
        size++;
    }

    @Override
    public void addLast(E element) {
        Node<E> node = new Node<>(element);
        if (this.head == null) {
            this.head = node;
        } else {
            Node<E> tmp = this.head;
            while (tmp.next != null) {
                tmp = tmp.next;
            }
            tmp.next = node;
        }
        size++;

    }

    @Override
    public E removeFirst() {
        ensureNotEmpty();
        E value = this.head.value;
        this.head = this.head.next;
        this.size--;
        return value;
    }

    @Override
    public E removeLast() {
        ensureNotEmpty();
        if (this.size == 1) {
            E value = this.head.value;
            this.head = null;

            return value;
        }
        Node<E> current = this.head.next;
        Node<E> previous = this.head;
        while (current.next != null) {
            previous = current;
            current = current.next;
        }
        previous.next = null;

        return current.value;
    }

    @Override
    public E getFirst() {
        ensureNotEmpty();
        return this.head.value;
    }

    @Override
    public E getLast() {
        ensureNotEmpty();
        Node<E> current = this.head;
        while (current.next != null) {
            current = current.next;
        }
        return current.value;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.head == null;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node<E> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public E next() {
                E value = current.value;
                current = current.next;
                return value;
            }
        };
    }

    private void ensureNotEmpty() {
        if (this.head == null) {
            throw new IllegalStateException();
        }
    }
}
