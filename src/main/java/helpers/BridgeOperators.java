package helpers;

import lombok.Getter;

import java.util.List;
import java.util.stream.Stream;

import static java.util.Collections.EMPTY_LIST;

public class BridgeOperators {
    @Getter
    private final long total;
    @Getter
    private final List<Long> operands;

    public BridgeOperators(String input) {
        String[] totalAndOperator = input.trim().split(":");
        this.total = Long.parseLong(totalAndOperator[0].trim());
        if (totalAndOperator.length == 1) {
            this.operands = EMPTY_LIST;
        } else {
            this.operands = Stream.of(totalAndOperator[1].trim().split(" "))
                    .map(String::trim)
                    .map(Long::parseLong)
                    .toList();
        }
    }

    public boolean canOperate() {
        if (operands.isEmpty()) {
            return false;
        }

        if (operands.size() == 1) {
            return this.total == this.operands.get(0);
        }

        return operate(1, this.operands.get(0));
    }

    private boolean operate(int index, long soFar) {
        if (index == this.operands.size()) {
            return soFar == this.total;
        }

        long times = soFar * this.operands.get(index);
        long plus = soFar + this.operands.get(index);
        return operate(index + 1, times) ||
                operate(index + 1, plus);
    }

    public boolean canConcatenate() {
        if (operands.isEmpty()) {
            return false;
        }

        if (operands.size() == 1) {
            return this.total == this.operands.get(0);
        }

        return concatenate(1, this.operands.get(0));
    }

    private boolean concatenate(int index, long soFar) {
        if (index == this.operands.size()) {
            return soFar == this.total;
        }

        long times = soFar * this.operands.get(index);
        long plus = soFar + this.operands.get(index);
        long concat = Long.parseLong(
                Long.toString(soFar)
                        .concat(
                                Long.toString(this.operands.get(index))
                        )
        );
        return concatenate(index + 1, times) ||
                concatenate(index + 1, plus) ||
                concatenate(index + 1, concat);
    }
}
