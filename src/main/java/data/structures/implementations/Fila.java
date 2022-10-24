package data.structures.implementations;


public class Fila<T> implements data.structures.interfaces.Fila<T> {

    private final T[] elementos;
    private int proximo;
    private int ultimo;

    public Fila(int size) {
        if (size <= 0)
            throw new RuntimeException("Tamanho inválido!");

        elementos = (T[]) new Object[size];
        proximo = ultimo = -1;
    }


    @Override
    public void enqueue(T data) {
        if (isFull())
            throw new RuntimeException("A fila está cheia!");

        if (isEmpty())
            proximo++;

        if (ultimo == size() - 1)
            ultimo = -1;

        elementos[++ultimo] = data;
    }

    @Override
    public T dequeue() {
        if (isEmpty())
            throw new RuntimeException("A fila está vazia!");

        var retorno = elementos[proximo];
        elementos[proximo] = null;

        if (proximo == ultimo) {
            proximo = ultimo = -1;
            return retorno;
        }

        if (proximo == size() - 1) {
            proximo = 0;
            return retorno;
        }

        proximo++;
        return retorno;
    }

    @Override
    public T front() {
        if (isEmpty())
            return null;
        return elementos[proximo];
    }
    public T rear() {
        if (isEmpty())
            return null;
        return elementos[ultimo];
    }
    @Override
    public int size() {
        return elementos.length;
    }

    @Override
    public boolean isEmpty() {
        return proximo < 0;
    }

    @Override
    public boolean isFull() {
        return ((proximo == 0 && ultimo == size() - 1) || ultimo == proximo - 1);
    }

    @Override
    public int getIndexFront() {
        return proximo;
    }

    @Override
    public int getIndexRear() {
        return ultimo;
    }
    
}
