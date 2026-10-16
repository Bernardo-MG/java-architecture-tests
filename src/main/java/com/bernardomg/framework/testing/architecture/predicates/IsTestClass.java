
package com.bernardomg.framework.testing.architecture.predicates;

import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.JavaClass;

/**
 * Checks if a class is a test class. It checks if any method is annotated with the JUnit {@code @Test} annotation.
 */
public final class IsTestClass extends DescribedPredicate<JavaClass> {

    public IsTestClass() {
        super("contain @Test methods");
    }

    @Override
    public boolean test(final JavaClass javaClass) {
        return javaClass.getAllMethods()
            .stream()
            .anyMatch(new IsTestMethod());
    }

}
