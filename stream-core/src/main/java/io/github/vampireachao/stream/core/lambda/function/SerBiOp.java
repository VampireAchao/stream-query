package io.github.vampireachao.stream.core.lambda.function;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;
import java.util.function.BinaryOperator;

/**
 * SerBiUnOp
 *
 * @author VampireAchao
 * @since 2022/6/8
 */
@FunctionalInterface
public interface SerBiOp<T> extends BinaryOperator<T>, Serializable {
    /**
     * Returns a {@link SerBiOp} which returns the lesser of two elements
     * according to the specified {@code Comparator}.
     *
     * @param <T>        the type of the input arguments of the comparator
     * @param comparator a {@code Comparator} for comparing the two values
     * @return a {@code SerBiUnOp} which returns the lesser of its operands,
     * according to the supplied {@code Comparator}
     * @throws NullPointerException if the argument is null
     */
    static <T> SerBiOp<T> minBy(Comparator<? super T> comparator) {
        Objects.requireNonNull(comparator);
        return (a, b) -> comparator.compare(a, b) <= 0 ? a : b;
    }

    /**
     * Returns a {@link SerBiOp} which returns the greater of two elements
     * according to the specified {@code Comparator}.
     *
     * @param <T>        the type of the input arguments of the comparator
     * @param comparator a {@code Comparator} for comparing the two values
     * @return a {@code SerBiUnOp} which returns the greater of its operands,
     * according to the supplied {@code Comparator}
     * @throws NullPointerException if the argument is null
     */
    static <T> SerBiOp<T> maxBy(Comparator<? super T> comparator) {
        Objects.requireNonNull(comparator);
        return (a, b) -> comparator.compare(a, b) >= 0 ? a : b;
    }

    /**
     * just before
     *
     * @param <T> type
     * @return before
     */
    static <T> SerBiOp<T> justBefore() {
        return (l, r) -> l;
    }

    /**
     * just after
     *
     * @param <T> type
     * @return after
     */
    static <T> SerBiOp<T> justAfter() {
        return (l, r) -> r;
    }
}

