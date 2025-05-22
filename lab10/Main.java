public class Main {
    public static void main(String[] args) {
        IMyList<Integer> skipList = new SkipList<>();
        TestMyList tester = new TestMyList(skipList);
        tester.runTests();
    }
}
