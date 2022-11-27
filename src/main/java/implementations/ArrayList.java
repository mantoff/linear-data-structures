package implementations;

import interfaces.List;

import java.util.Arrays;
import java.util.Iterator;

public class ArrayList<E> implements List<E> {

    private static final int DEFAULT_CAPACITY = 4;
    private Object[] elements;
    private int size;
    private int capacity;

    public ArrayList() {
        this.elements = new Object[DEFAULT_CAPACITY];
        this.capacity = DEFAULT_CAPACITY;
    }

    @Override
    public boolean add(E element) {
        resizeIfNeeded();
        this.elements[this.size] = element;
        this.size++;
        return true;

    }

    @Override
    public boolean add(int index, E element) {
        if (!isValidIndex(index)) {
            return false;
        }

        resizeIfNeeded();
        shiftRight(index);
        this.elements[index] = element;
        this.size++;
        return true;
    }

    @Override
    public E get(int index) {
        ensureIndex(index);
        return (E) this.elements[index];
    }

    @Override
    public E set(int index, E element) {
        ensureIndex(index);
        Object toRemove = this.elements[index];
        this.elements[index] = element;

        return (E) toRemove;
    }

    @Override
    public E remove(int index) {
        ensureIndex(index);
        Object toRemove = this.elements[index];
        shiftLeft(index);
        //TODO might need to be this.elements[index]
       // this.elements[size - 1] = null;
        this.size--;
        return (E) toRemove;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public int indexOf(E element) {
        for (int i = 0; i < this.size; i++) {
            if(this.elements[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean contains(E element) {
        return this.indexOf(element) >= 0;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < size();
            }

            @Override
            public E next() {
                return get(index++);
            }
        };
    }

    private void resizeIfNeeded() {
        if (this.size == this.capacity) {
            grow();
        } else if (this.size <= this.capacity / 3) {
            shrink();
        }
    }

    private void grow() {
        this.capacity *= 2;
        Object[] tmp = new Object[this.capacity];
        for (int i = 0; i < this.elements.length; i++) {
            tmp[i] = this.elements[i];
        }

        this.elements = tmp;
    }

    private void shrink() {
        this.capacity /= 2;
        this.elements = Arrays.copyOf(this.elements, this.capacity);
    }

    private boolean isValidIndex(int i) {
        return i >= 0 && i < this.size;
    }

    private void ensureIndex(int index) {
        if (!isValidIndex(index)) {
            throw new IndexOutOfBoundsException();
        }
    }

    private void shiftRight(int index) {
        for (int i = this.size; i > index; i--) {
            this.elements[i] = this.elements[i - 1];
        }
    }

    private void shiftLeft(int index) {
        for (int i = index; i < this.size - 1; i++) {
            this.elements[i] = this.elements[i + 1];
        }
    }
}
