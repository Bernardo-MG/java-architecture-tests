/**
 * The MIT License (MIT)
 * <p>
 * Copyright (c) 2024 the original author or authors.
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.bernardomg.framework.testing.architecture.predicates;

import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.JavaClass;

/**
 * Checks if a class is in a service package, which means inside package named "service".
 */
public final class IsInServicePackage extends DescribedPredicate<JavaClass> {

    /**
     * TODO: careful when checking by package
     */
    private static final String    INNER_PACKAGE           = ".service.";

    /**
     * TODO: careful when checking by package
     */
    private static final String    TAIL_PACKAGE            = ".service";

    /**
     * TODO: this is meaningless here
     */
    private final IsSyntheticClass syntheticClassPredicate = new IsSyntheticClass();

    public IsInServicePackage() {
        super("class in service package");
    }

    @Override
    public final boolean test(final JavaClass javaClass) {
        final String packageName = javaClass.getPackageName();
        return (packageName.contains(INNER_PACKAGE) || packageName.endsWith(TAIL_PACKAGE))
                && !syntheticClassPredicate.test(javaClass);
    }

}
