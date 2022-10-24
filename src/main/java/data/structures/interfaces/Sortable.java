package data.structures.interfaces;

public interface Sortable<T extends Comparable<T>> {

    T[] sort(T[] elements);
}
