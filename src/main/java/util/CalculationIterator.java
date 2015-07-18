package util;

import dto.Calculation;
import org.springframework.util.Assert;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by yoon on 15. 7. 18..
 */
public class CalculationIterator {

    private final Queue<Object> originCalculationQueue;

    private Queue<Object> calculationQueue;
    private Stack<Object> calculationStack;

    public CalculationIterator(PostFixConverter postFixConverter) {
        Assert.notNull(postFixConverter);

        this.originCalculationQueue = postFixConverter.getQueue();

    }

    public boolean hasNext() {
        return this.calculationStack.empty() == false;
    }

    public Calculation first() {
        calculationQueue = new LinkedList<Object>(originCalculationQueue);
        calculationStack = new Stack<Object>();


        return getCalculation();
    }

    private Calculation getCalculation() {
        Object letter;
        int operand1;
        int operand2;

        while ((letter = calculationQueue.poll()) != null) {
            if (letter instanceof Operator) {

                //save operand2 first. Because It is extracted from stack
                operand2 = Integer.parseInt((String)calculationStack.pop());
                operand1 = Integer.parseInt((String)calculationStack.pop());
                return new Calculation(operand1, operand2, (Operator)letter);

            } else {
                calculationStack.add(letter);
            }
        }

        return null;
    }

    public Calculation nextWithPreviousResult(int previousResult) {
        calculationStack.add(""+previousResult);

        return getCalculation();
    }
}
