package data.structures.interfaces;

public interface ArvoreDeBuscaBinaria<T extends Comparable<T>> {


    void insert(T data);

    boolean exists(T data);

    ArvoreDeBuscaBinaria<T> retrieve(T data);

    void preOrder();

    void inOrder();

    void posOrder();

    void bfs();
}
