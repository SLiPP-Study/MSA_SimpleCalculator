package util;

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by yoon on 15. 7. 18..
 */
public enum Operator {
    ROUND_BRACKET_START("(", 1),
    ROUND_BRACKET_END(")", 1),
    DIVISION("/", 5),
    MULTIPLICATION("*", 5),
    ADDITION("+", 10),
    SUBTRACTION("-", 10);

    private final String stringValue;
    private String serverUrl;
    private final int priority;

    static {

        //TODO Update data from Physical Database
        Operator.ADDITION.setServerUrl("http://127.0.0.1:9001");
        Operator.SUBTRACTION.setServerUrl("http://127.0.0.1:9002");
        Operator.MULTIPLICATION.setServerUrl("http://127.0.0.1:9003");
        Operator.DIVISION.setServerUrl("http://127.0.0.1:9004");
    }


    Operator(String stringValue, int priority) {
        this.stringValue = stringValue;
        this.serverUrl = serverUrl;
        this.priority = priority;
    }

    public String getStringValue() {
        return stringValue;
    }

    public String getServerUrl() {
        return serverUrl;
    }

    public int getPriority() {
        return priority;
    }

    public static Operator getOperand(String stringValue) {

        if (StringUtils.isEmpty(stringValue))
            return null;

        for (Operator operator : Operator.values()) {
            if (operator.getStringValue().equals(stringValue))
                return operator;
        }

        return null;
    }

    private final void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public static List<String> valuesOfString() {
        List<String> values = new ArrayList<String>();

        for (Operator operator : values()) {
            values.add(operator.getStringValue());
        }

        return Collections.unmodifiableList(values);
    }

    public boolean isPriorTo(Operator operator) {
        if (operator == null)
            return false;

        if (getPriority() > operator.getPriority())
            return false;

        return true;
    }
}
