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
import com.tngtech.archunit.core.domain.AccessTarget.MethodCallTarget;

/**
 * Checks if a method is annotated with a Spring cache annotation. This is delegated to {@link IsSpringCacheAnnotation},
 * which will check the annotations in the class.
 */
public final class IsSpringCachedMethod extends DescribedPredicate<MethodCallTarget> {

    private final IsSpringCacheAnnotation isSpringCacheAnnotation = new IsSpringCacheAnnotation();

    public IsSpringCachedMethod() {
        super("Spring cached methods");
    }

    @Override
    public final boolean test(final MethodCallTarget method) {
        return method.isMetaAnnotatedWith(isSpringCacheAnnotation);
    }

}
