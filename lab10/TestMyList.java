public class TestMyList {
    private final IMyList<Integer> list;

    public TestMyList(IMyList<Integer> list) {
        this.list = list;
    }

    public void runTests() {
        System.out.println("Додавання");
        list.add(10);
        list.add(20);
        list.add(30);
        print();

        System.out.println("\nДодавання за індексом");
        list.add(1, 15);
        print();

        System.out.println("\nОтримання елемента");
        System.out.println("Елемент на позиції 2: " + list.get(2));

        System.out.println("\nВидалення за індексом");
        System.out.println("Видалено: " + list.remove(1));
        print();

        System.out.println("\nВидалення за значенням");
        System.out.println("Було видалено 30? " + list.remove(Integer.valueOf(30)));
        print();

        System.out.println("\ncontains(10)" + list.contains(10));
        System.out.println("indexOf(10)" + list.indexOf(10));
        System.out.println("size()" + list.size());
        System.out.println("isEmpty()" + list.isEmpty());

        System.out.println("\nОчищення");
        list.clear();
        print();
    }

    private void print() {
        System.out.print("Список: ");
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + " ");
        }
        System.out.println();
    }
}
