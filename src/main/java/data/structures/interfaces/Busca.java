package data.structures.interfaces;

public interface Busca<T extends Comparable<T>> {

    int buscaSequencial(T[] elements);

    int buscaBinaria(T[] elements);
}
