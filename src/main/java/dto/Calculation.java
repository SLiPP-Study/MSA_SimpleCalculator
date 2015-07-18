package dto;

import util.Operator;

/**
 * Created by yoon on 15. 7. 17..
 */
public class Calculation {

    int operand1;
    int operand2;
    Operator operator;

    public Calculation(int operand1, int operand2, Operator operator) {
        this.operand1 = operand1;
        this.operand2 = operand2;
        this.operator = operator;
    }

    public int getOperand1() {
        return operand1;
    }

    public int getOperand2() {
        return operand2;
    }

    public Operator getOperator() {
        return operator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Calculation that = (Calculation) o;

        if (operand1 != that.operand1) return false;
        if (operand2 != that.operand2) return false;
        return operator == that.operator;

    }

    @Override
    public int hashCode() {
        int result = operand1;
        result = 31 * result + operand2;
        result = 31 * result + (operator != null ? operator.hashCode() : 0);
        return result;
    }
}
