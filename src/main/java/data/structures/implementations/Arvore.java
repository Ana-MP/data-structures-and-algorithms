package data.structures.implementations;

import data.structures.interfaces.ArvoreDeBuscaBinaria;

import java.util.LinkedList;
import java.util.Objects;

public class Arvore<T extends Comparable<T>> implements ArvoreDeBuscaBinaria<T> {
    private T data;
    private Arvore<T> esquerdo;
    private Arvore<T> direito;


    public Arvore(T data) {
        this.data = data;
    }

    @Override
    public void insert(T data) {
        insert(this, data);
    }

    private Arvore<T> insert(Arvore<T> tree, T data) {
        if (Objects.isNull(tree))
            tree = new Arvore<>(data);
        else if (data.compareTo(tree.data) > 0)
            tree.direito = insert(tree.direito, data);
        else if (data.compareTo(tree.data) < 0)
            tree.esquerdo = insert(tree.esquerdo, data);
        return tree;

    }

    @Override
    public boolean exists(T data) {
        return Objects.nonNull(retrieve(data));
    }

    @Override
    public ArvoreDeBuscaBinaria<T> retrieve(T data) {
        return retrieve(this, data);
    }

    private Arvore<T> retrieve(Arvore<T> tree, T data) {
        if (Objects.isNull(tree) || data.equals(tree.data))
            return tree;
        else if (data.compareTo(tree.data) > 0)
            return retrieve(tree.direito, data);
        return retrieve(tree.esquerdo, data);

    }

    @Override
    public void preOrder() {
        preOrder(this);

    }

    private void preOrder(Arvore<T> tree) {
        if (Objects.nonNull(tree)) {
            System.out.println(tree.data);
            inOrder(tree.esquerdo);
            inOrder(tree.direito);
        }

    }

    @Override
    public void inOrder() {
        inOrder(this);
    }

    private void inOrder(Arvore<T> tree) {
        if (Objects.nonNull(tree)) {
            inOrder(tree.esquerdo);
            System.out.println(tree.data);
            inOrder(tree.direito);
        }
    }

    @Override
    public void posOrder() {
        posOrder(this);
    }

    private void posOrder(Arvore<T> tree) {
        if (Objects.nonNull(tree)) {
            inOrder(tree.esquerdo);
            inOrder(tree.direito);
            System.out.println(tree.data);
        }

    }

    @Override
    public void bfs() {
        bfs(this);
    }

    private void bfs(Arvore<T> tree) {
        var queue = new LinkedList<Arvore<T>>();
        queue.add(tree);
        while (!queue.isEmpty()) {
            var temp = queue.poll();
            System.out.println(temp.data);

            if (temp.esquerdo != null)
                queue.add(temp.esquerdo);

            if (temp.direito != null)
                queue.add(temp.direito);
        }
    }

    public static void main(String[] args) {
        Arvore<String> tree = new Arvore<>("c");
        tree.insert("a");
        tree.insert("b");
        tree.insert("c");
        tree.insert("d");
        tree.insert("f");
        tree.insert("e");

        System.out.println("---In Order---");
        tree.inOrder();
        System.out.println("---Pre Order---");
        tree.preOrder();
        System.out.println("---Pos Order---");
        tree.posOrder();
        System.out.println("---Retrieve 'd' tree---");
        var t = tree.retrieve("d");
        t.inOrder();
        System.out.println("---'f' exists?---");
        System.out.println(tree.exists("f"));
        System.out.println("---'k' exists?---");
        System.out.println(tree.exists("k"));

        System.out.println("---BFS---");
        tree.bfs();

    }
}
