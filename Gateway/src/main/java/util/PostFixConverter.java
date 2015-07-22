package util;

import java.util.*;

import static util.Operator.ROUND_BRACKET_END;
import static util.Operator.ROUND_BRACKET_START;

/**
 * Created by yoon on 15. 7. 18..
 */
public class PostFixConverter {

    Queue<Object> resultQueue = new LinkedList<Object>();

    public PostFixConverter(String requestCalculation) {
        //%2F = /, %28 = (, %29 = )
        String trimedText = requestCalculation.replaceAll(" ", "").replaceAll("\\n", "").replace("=", "").replaceAll("%2F", "/").replaceAll("%28", "(").replaceAll("%29", ")");
        makeResultQueue(trimedText);
    }

    public final Queue<Object> getQueue() {
        return resultQueue;
    }

    public String getString() {

        StringBuilder sb = new StringBuilder();

        while(resultQueue.size() != 0) {
            Object object = resultQueue.poll();

            if (object instanceof Operator)
                sb.append(((Operator) object).getStringValue());
            else
                sb.append(object);
        }
        return sb.toString();
    }

    private final void makeResultQueue(String requestCalculation) {

        Stack<Operator> stack = new Stack<Operator>();
        List<String> operandList = Operator.valuesOfString();

        String delimiters = null;
        for (Operator operator : Operator.values()) {
            delimiters += operator.getStringValue();
        }

        StringTokenizer tokenizer = new StringTokenizer(requestCalculation, delimiters, true);
        while(tokenizer.hasMoreTokens()) {
            String letter = tokenizer.nextToken();

            //is Operator
            if (operandList.contains(letter)) {
                Operator operator = Operator.getOperand(letter);

                if (operator == ROUND_BRACKET_START) {
                    stack.push(operator);

                } else if (operator == ROUND_BRACKET_END) {
                    Operator popValue = null;

                    //Empty Stack Runtime Exception
                    while ((popValue = stack.pop()) != ROUND_BRACKET_START)
                        resultQueue.add(popValue);
                } else {
                    while (stack.size() != 0 && operator.isPriorTo(stack.peek()) == true)
                        resultQueue.add(stack.pop());

                    stack.push(operator);
                }

            //is Operand
            } else {
                resultQueue.add(letter);
            }
        }

        while(stack.size() != 0)
            resultQueue.add(stack.pop());
    }
}
