package data.structures.interfaces;

public interface Fila<T> {

    void enqueue(T data);

    T dequeue();

    T front();

    T rear();

    int size();

    boolean isEmpty();

    boolean isFull();

    int getIndexFront();

    int getIndexRear();

}
