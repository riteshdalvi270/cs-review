package com.mmnaseri.cs.clrs.ch29.s1.impl;

import com.mmnaseri.cs.clrs.ch29.s1.ConstraintType;
import com.mmnaseri.cs.clrs.ch29.s1.LinearProgram;
import com.mmnaseri.cs.clrs.ch29.s1.LinearProgramConstraint;
import com.mmnaseri.cs.clrs.ch29.s1.LinearProgramConverter;
import com.mmnaseri.cs.clrs.common.NumberUtils;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

import java.util.*;

/**
 * @author Milad Naseri (milad.naseri@cdk.com)
 * @since 1.0 (8/31/16, 10:39 AM)
 */
@Quality(Stage.TESTED)
public class StandardLinearProgramConverter<E extends Number> implements LinearProgramConverter<E> {

    private final Class<E> type;

    public StandardLinearProgramConverter(Class<E> type) {
        this.type = type;
    }

    @Override
    public LinearProgram<E> convert(LinearProgram<E> original) {
        Objects.requireNonNull(original);
        if (original.isSlack()) {
            throw new IllegalArgumentException("Input is not a standard form linear program");
        }
        final Set<LinearProgramConstraint<E>> constraints = new HashSet<>();
        for (LinearProgramConstraint<E> constraint : original.getConstraints()) {
            if (ConstraintType.LESS_THAN_OR_EQUAL_TO.equals(constraint.getConstraintType())) {
                final List<E> coefficients = new ArrayList<>();
                for (int i = 0; i < constraint.size(); i++) {
                    coefficients.add(NumberUtils.negate(constraint.getCoefficient(i)));
                }
                constraints.add(new DefaultLinearProgramConstraint<>(type, coefficients, ConstraintType.GREATER_THAN_OR_EQUAL_TO, NumberUtils.negate(constraint.getValue())));
            } else {
                constraints.add(constraint);
            }
        }
        return new DefaultLinearProgram<>(constraints, original.getObjective());
    }

}
