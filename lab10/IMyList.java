public interface IMyList<T extends Comparable<T>> {
    void add(T element);
    void add(int index, T element);
    T get(int index);
    T remove(int index);
    boolean remove(T element);
    boolean contains(T element);
    int indexOf(T element);
    int size();
    boolean isEmpty();
    void clear();
}
