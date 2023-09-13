import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        var ints = new int[8];

        for(int i = 0; i < 8; i++)
            ints[i] = scanner.nextInt();
        char[] operators = { '+', '-', '*', '/', '_' };

        List<String> combinations = new ArrayList<>();
        generateCombinations(ints, operators, combinations, "", 0);

        for (String combination : combinations) {
            if(Math.abs(ExpressionParser.parseExpression(combination) - 100) < 0.0000001)
                System.out.println(combination);
        }
    }

    public static void generateCombinations(int[] digits, char[] operators, List<String> combinations, String current, int index) {
        if (index == digits.length - 1) {
            combinations.add(current + digits[index]);
            return;
        }

        for (char op : operators) {
            String next = current + digits[index] + (op == '_' ? "" : op);
            generateCombinations(digits, operators, combinations, next, index + 1);
        }
    }
}