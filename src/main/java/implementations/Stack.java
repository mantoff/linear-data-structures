package implementations;

import interfaces.AbstractStack;
import org.w3c.dom.Node;

import java.util.Iterator;

public class Stack<E> implements AbstractStack<E> {
    private Node<E> top;
    private int size;

    private static class Node<E> {
        private E value;
        private Node<E> next;

        public Node(E value) {
            this.value = value;
            this.next = null;
        }
    }

    public Stack() {
        this.top = null;
        this.size = 0;
    }

    @Override
    public void push(E element) {
        Node<E> newNode = new Node<>(element);
        newNode.next = this.top;
        this.top = newNode;

        this.size++;
    }

    @Override
    public E pop() {
        ensureNotEmpty();
        E tmp = this.top.value;
        this.top = this.top.next;
        this.size--;
        return tmp;
    }

    @Override
    public E peek() {
        ensureNotEmpty();
        return this.top.value;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.top == null;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Stack.Node<E> current = top;
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
        if(top == null) {
            throw new IllegalStateException();
        }
    }
}
