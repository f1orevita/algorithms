import java.util.*;

public class TuringTest {

    public static void main(String[] args) {
        LinkedHashSet<Character> alphabet = new LinkedHashSet<>(Arrays.asList('0', '1', '_'));
        int[] states = {1, 2};
        Rule[][] rules = new Rule[alphabet.size()][states.length];

        rules[2][0] = new Rule('_', Move.R, 1);

        rules[0][1] = new Rule('1', Move.R, 1);
        rules[1][1] = new Rule('0', Move.R, 1);
        rules[2][1] = new Rule('_', Move.N, -1);

        TuringMachine turingMachine = new TuringMachine(alphabet, rules, "11100010", -1);
        System.out.println(turingMachine.run());
    }
}

class TuringMachine {
    ArrayList<Character> alphabet;
    Rule[][] rules;
    InfString infString;
    int currentIndex;
    int currentAlphabetIndex;
    int currentStateIndex;

    public TuringMachine(LinkedHashSet<Character> alphabet, Rule[][] rules, String inputString, int startIndex) {
        this.alphabet = new ArrayList<>(alphabet);
        this.rules = rules;
        this.infString = new InfString(inputString);
        this.currentIndex = startIndex;
        this.currentStateIndex = 0;
    }

    public String run() {
        while (currentStateIndex != -1) {
            char currentChar = infString.getCharAt(currentIndex);
            currentAlphabetIndex = alphabet.indexOf(currentChar);

            if (currentAlphabetIndex == -1 || currentStateIndex >= rules[0].length) {
                throw new IllegalStateException("No rule for character '" + currentChar + "' in state " + currentStateIndex);
            }

            Rule rule = rules[currentAlphabetIndex][currentStateIndex];
            if (rule == null) {
                throw new IllegalStateException("No rule defined for character '" + currentChar + "' in state " + currentStateIndex);
            }

            infString.setCharAt(currentIndex, rule.getReplace());

            switch (rule.getMove()) {
                case L -> currentIndex--;
                case R -> currentIndex++;
                case N -> {}
            }

            currentStateIndex = rule.getGoTo();
        }

        return infString.getWholeString();
    }
}

class InfString {
    private Map<Integer, Character> notEmptyCells;

    public InfString(String input) {
        notEmptyCells = new HashMap<>();
        for (int i = 0; i < input.length(); i++) {
            notEmptyCells.put(i, input.charAt(i));
        }
    }

    public char getCharAt(int i) {
        return notEmptyCells.getOrDefault(i, '_');
    }

    public void setCharAt(int i, char c) {
        notEmptyCells.put(i, c);
    }

    public String getWholeString() {
        if (notEmptyCells.isEmpty()) return "";

        TreeSet<Integer> indexes = new TreeSet<>(notEmptyCells.keySet());
        int minValue = indexes.first();
        int maxValue = indexes.last();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = minValue; i <= maxValue; i++) {
            stringBuilder.append(getCharAt(i));
        }
        return stringBuilder.toString().replace("_", " ").trim();
    }
}

class Rule {
    private char replace;
    private Move move;
    private int goTo;

    public Rule(char replace, Move move, int goTo) {
        this.replace = replace;
        this.move = move;
        this.goTo = goTo;
    }

    public char getReplace() {
        return replace;
    }

    public Move getMove() {
        return move;
    }

    public int getGoTo() {
        return goTo;
    }
}

enum Move {
    L,
    R,
    N
}
