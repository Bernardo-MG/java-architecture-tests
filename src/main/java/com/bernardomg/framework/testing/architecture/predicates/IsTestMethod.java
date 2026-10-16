
package com.bernardomg.framework.testing.architecture.predicates;

import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.JavaMethod;

/**
 * Checks if a method is a test method. It checks it it is annotated with the JUnit {@code @Test} annotation.
 */
public final class IsTestMethod extends DescribedPredicate<JavaMethod> {

    public IsTestMethod() {
        super("are annotated with org.junit.jupiter.api.Test");
    }

    @Override
    public boolean test(final JavaMethod method) {
        return method.isAnnotatedWith("org.junit.jupiter.api.Test");
    }

}
