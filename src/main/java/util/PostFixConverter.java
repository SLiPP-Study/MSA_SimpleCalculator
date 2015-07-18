package util;

import java.util.*;

import static util.Operator.*;

/**
 * Created by yoon on 15. 7. 18..
 */
public class PostFixConverter {

    Queue<Object> resultQueue = new LinkedList<Object>();

    public PostFixConverter(String requestCalculation) {
        makeResultQueue(requestCalculation);
    }

    private void makeResultQueue(String requestCalculation) {

        Stack<Operator> stack = new Stack<Operator>();
        List<String> operandList = Operator.valuesOfString();

        for (String letter : requestCalculation.split("")) {

            //is Operator
            if (operandList.contains(letter)) {
                Operator operator = Operator.getOperand(letter);

                System.out.println(stack.toString());

                if (operator == ROUND_BRACKET_START) {
                    stack.push(operator);

                } else if (operator == ROUND_BRACKET_END) {
                    Operator popValue = null;

                    //Empty Stack Runtime Exception
                    while ((popValue = stack.pop()) != ROUND_BRACKET_START) {
                        resultQueue.add(popValue);
                    }
                } else {
                    while (stack.size() != 0 && operator.isPriorTo(stack.peek()) == true) {
                        resultQueue.add(stack.pop());
                    }

                    stack.push(operator);
                }

            //is Operand
            } else {
                resultQueue.add(letter);
            }
        }

        while(stack.size() != 0) {
            resultQueue.add(stack.pop());
        }
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
}
