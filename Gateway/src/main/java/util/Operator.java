package util;

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by yoon on 15. 7. 18..
 */
public enum Operator {
    ROUND_BRACKET_START("(", null, 1),
    ROUND_BRACKET_END(")", null, 1),
    DIVISION("/", "url", 5),
    MULTIPLICATION("*", "url", 5),
    ADDITION("+", "url", 10),
    SUBTRACTION("-", "url", 10);

    private final String stringValue;
    private final String serverUrl;
    private final int priority;

    static {
        //TODO Load ServerURL From DB
    }


    Operator(String stringValue, String serverUrl, int priority) {
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
