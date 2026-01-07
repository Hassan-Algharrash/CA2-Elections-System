package controllers;

@FunctionalInterface
public interface Comparator<T> {
    int compare(T a, T b);
}
