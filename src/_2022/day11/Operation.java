package _2022.day11;

import java.util.Optional;

public enum Operation {
    PLUS ("+"),
    MINUS("-"),
    MULTIPLY("*"),
    DIVIDE("/");

    String val;
    Operation(String val) {
        this.val = val;
    }

    public static Optional<Operation> fromString (String val) {

        for (Operation o : values())
            if(o.val.equals(val))
                return Optional.of(o);

        return Optional.empty();
    }
}
