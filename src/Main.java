import org.mariuszgromada.math.mxparser.Expression;
import org.mariuszgromada.math.mxparser.License;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        boolean isCallSuccessful = License.iConfirmNonCommercialUse("John Doe");
        int[] digits = { 9, 8, 7, 6, 5, 4, 3, 2 };
        char[] operators = { '+', '-', '*', '/', '_' };

        List<String> combinations = new ArrayList<>();
        generateCombinations(digits, operators, combinations, "", 0);

        for (String combination : combinations) {

            if(v == 100){
                System.out.println(combination);

            }
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