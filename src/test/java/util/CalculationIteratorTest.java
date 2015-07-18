package util;

import dto.Calculation;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by yoon on 15. 7. 18..
 */
public class CalculationIteratorTest {

    @Test
    public void first_메서드를_이용한_Operation객체생성() {
        CalculationIterator analyzer = new CalculationIterator(new PostFixConverter("2+3"));
        Calculation calculation = analyzer.first();

        assertEquals(2, calculation.getOperand1());
        assertEquals(3, calculation.getOperand2());
        assertEquals(Operator.ADDITION, calculation.getOperator());
    }

    @Test
    public void next_메서드를_이용한_Operation객체생성() {
        CalculationIterator iterator = new CalculationIterator(new PostFixConverter("2*(2+3)"));

        Calculation first = iterator.first();
        assertTrue(iterator.hasNext());

        assertEquals(2, first.getOperand1());
        assertEquals(3, first.getOperand2());
        assertEquals(Operator.ADDITION, first.getOperator());



        Calculation next = iterator.nextWithPreviousResult(5);
        assertFalse(iterator.hasNext());


        assertEquals(2, next.getOperand1());
        assertEquals(5, next.getOperand2());
        assertEquals(Operator.MULTIPLICATION, next.getOperator());
    }
}