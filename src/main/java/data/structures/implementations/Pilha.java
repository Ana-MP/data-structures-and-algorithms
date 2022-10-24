package data.structures.implementations;

public class Pilha<T> implements data.structures.interfaces.Pilha<T> {

    private final T[] elements;
    private int pos;

    public Pilha(int size) {
        if (size <= 0)
            throw new RuntimeException("Tamanho invalido: " + size);

        elements = (T[]) new Object[size];
        pos = -1;
    }

    @Override
    public void push(T data) {
        if (isFull())
            throw new RuntimeException("Pilha cheia");
        elements[++pos] = data;
    }

    @Override
    public T pop() {
        if (isEmpty())
            throw new RuntimeException("Pilha vazia");
        return elements[pos--];
    }

    @Override
    public T top() {
        if (isEmpty())
            return null;
        return elements[pos];
    }

    @Override
    public int size() {
        return elements.length;
    }

    @Override
    public boolean isEmpty() {
        return pos < 0;
    }

    @Override
    public boolean isFull() {
        return (pos + 1) == size();
    }
}
