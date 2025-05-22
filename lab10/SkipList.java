import java.util.Random;

public class SkipList<T extends Comparable<T>> implements IMyList<T> {
    private static final int MAX_LEVEL = 16;
    private final Node<T> head = new Node<>(null, MAX_LEVEL);
    private int level = 0;
    private int size = 0;
    private final Random random = new Random();

    @SuppressWarnings("unchecked")
    private static class Node<T> {
        T value;
        Node<T>[] forward;

        Node(T value, int level) {
            this.value = value;
            this.forward = new Node[level + 1];
        }
    }

    private int randomLevel() {
        int lvl = 0;
        while (random.nextBoolean() && lvl < MAX_LEVEL) {
            lvl++;
        }
        return lvl;
    }

    @Override
    public void add(T element) {
        add(size, element); // додаємо в кінець
    }

    @Override
    public void add(int index, T element) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException();

        @SuppressWarnings("unchecked")
        Node<T>[] update = new Node[MAX_LEVEL + 1];
        Node<T> current = head;

        int currentIndex = -1;

        for (int i = level; i >= 0; i--) {
            while (current.forward[i] != null && currentIndex + countNodes(current, i) < index) {
                currentIndex += countNodes(current, i);
                current = current.forward[i];
            }
            update[i] = current;
        }

        int newLevel = randomLevel();
        if (newLevel > level) {
            for (int i = level + 1; i <= newLevel; i++) {
                update[i] = head;
            }
            level = newLevel;
        }

        Node<T> newNode = new Node<>(element, newLevel);
        for (int i = 0; i <= newLevel; i++) {
            newNode.forward[i] = update[i].forward[i];
            update[i].forward[i] = newNode;
        }

        size++;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();

        Node<T> current = head.forward[0];
        for (int i = 0; i < index; i++) {
            current = current.forward[0];
        }
        return current.value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();

        @SuppressWarnings("unchecked")
        Node<T>[] update = new Node[MAX_LEVEL + 1];
        Node<T> current = head;
        int currentIndex = -1;

        for (int i = level; i >= 0; i--) {
            while (current.forward[i] != null && currentIndex + countNodes(current, i) < index) {
                currentIndex += countNodes(current, i);
                current = current.forward[i];
            }
            update[i] = current;
        }

        Node<T> toRemove = current.forward[0];
        for (int i = 0; i <= level; i++) {
            if (update[i].forward[i] != toRemove) break;
            update[i].forward[i] = toRemove.forward[i];
        }

        while (level > 0 && head.forward[level] == null) {
            level--;
        }

        size--;
        return toRemove.value;
    }

    @Override
    public boolean remove(T element) {
        @SuppressWarnings("unchecked")
        Node<T>[] update = new Node[MAX_LEVEL + 1];
        Node<T> current = head;

        for (int i = level; i >= 0; i--) {
            while (current.forward[i] != null && current.forward[i].value.compareTo(element) < 0) {
                current = current.forward[i];
            }
            update[i] = current;
        }

        current = current.forward[0];
        if (current != null && current.value.equals(element)) {
            for (int i = 0; i <= level; i++) {
                if (update[i].forward[i] != current) break;
                update[i].forward[i] = current.forward[i];
            }

            while (level > 0 && head.forward[level] == null) {
                level--;
            }

            size--;
            return true;
        }

        return false;
    }

    @Override
    public boolean contains(T element) {
        Node<T> current = head;
        for (int i = level; i >= 0; i--) {
            while (current.forward[i] != null && current.forward[i].value.compareTo(element) < 0) {
                current = current.forward[i];
            }
        }
        current = current.forward[0];
        return current != null && current.value.equals(element);
    }

    @Override
    public int indexOf(T element) {
        Node<T> current = head.forward[0];
        int index = 0;
        while (current != null) {
            if (current.value.equals(element)) return index;
            current = current.forward[0];
            index++;
        }
        return -1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        for (int i = 0; i <= MAX_LEVEL; i++) {
            head.forward[i] = null;
        }
        size = 0;
        level = 0;
    }

    private int countNodes(Node<T> node, int level) {
        int count = 0;
        Node<T> current = node.forward[level];
        while (current != null && level < current.forward.length) {
            count++;
            current = current.forward[level];
        }
        return count;
    }
}
