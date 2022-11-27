package implementations;

import interfaces.AbstractQueue;

import java.util.Iterator;

public class Queue<E> implements AbstractQueue<E> {

    private Node<E> head;
    private Node<E> tail;
    private int size;

    public Queue() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    private static class Node<E> {
        private E value;
        private Node<E> next;

        public Node(E value) {
            this.value = value;
            this.next = null;
        }
    }

    @Override
    public void offer(E element) {
        Node<E> node = new Node<>(element);
        if(this.head == null) {
            this.head = node;
            this.size++;
            return;
        }
        Node<E> tmp = this.head;
        while (tmp.next != null) {
            tmp = tmp.next;
        }
        tmp.next = node;
        this.size++;
    }

    @Override
    public E poll() {
        ensureNotEmpty();
        E value = this.head.value;
        this.head = this.head.next;
        this.size--;
        return value;
    }

    @Override
    public E peek() {
        ensureNotEmpty();
        return this.head.value;
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
                return this.current != null;
            }

            @Override
            public E next() {
                E value = this.current.value;
                this.current = this.current.next;
                return value;
            }
        };
    }

    private void ensureNotEmpty() {
        if (head == null) {
            throw new IllegalStateException();
        }
    }
}
