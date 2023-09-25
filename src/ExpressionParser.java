import java.util.*;

public class ExpressionParser {
    public static double parseExpression(String expression) {
        List<String> tokens = tokenize(expression);
        Queue<String> postfix = toPostfix(tokens);
        return evaluatePostfix(postfix);
    }

    public static List<String> tokenize(String expression) {
        List<String> tokens = new ArrayList<>();
        StringBuilder currentToken = new StringBuilder();

        for (char ch : expression.toCharArray()) {
            if (Character.isDigit(ch)) {
                currentToken.append(ch);
            } else {
                if (currentToken.length() > 0) {
                    tokens.add(currentToken.toString());
                    currentToken.setLength(0);
                }
                tokens.add(Character.toString(ch));
            }
        }

        if (currentToken.length() > 0)
            tokens.add(currentToken.toString());

        return tokens;
    }

    public static Queue<String> toPostfix(List<String> tokens) {
        Queue<String> postfix = new LinkedList<>();
        Stack<String> operatorStack = new Stack<>();
        Map<String, Integer> priority = new HashMap<>();

        priority.put("+", 1);
        priority.put("-", 1);
        priority.put("*", 2);
        priority.put("/", 2);

        for (String token : tokens) {
            if (token.matches("\\d+(\\.\\d+)?")) {
                postfix.offer(token);
            } else {
                while (!operatorStack.isEmpty() && priority.get(token) <= priority.get(operatorStack.peek())) {
                    postfix.offer(operatorStack.pop());
                }
                operatorStack.push(token);
            }
        }

        while (!operatorStack.isEmpty()) {
            postfix.offer(operatorStack.pop());
        }

        return postfix;
    }

    public static double evaluatePostfix(Queue<String> postfix) {
        Stack<Double> operandStack = new Stack<>();

        while (!postfix.isEmpty()) {
            String token = postfix.poll();

            //регулярное выражение о соответствии любой цифре от 0 до 9 и о том, что группа символов таких может
            //присутствовать один и более раз
            if (token.matches("\\d+")) {
                operandStack.push(Double.parseDouble(token));
            } else {
                double operand2 = operandStack.pop();
                double operand1 = operandStack.pop();

                switch (token) {
                    case "+" -> operandStack.push(operand1 + operand2);
                    case "-" -> operandStack.push(operand1 - operand2);
                    case "*" -> operandStack.push(operand1 * operand2);
                    case "/" -> operandStack.push(operand1 / operand2);
                }
            }
        }

        return operandStack.pop();
    }
}