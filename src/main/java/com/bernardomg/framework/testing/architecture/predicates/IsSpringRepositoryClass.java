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

import org.springframework.data.repository.Repository;

import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.JavaClass;

/**
 * Checks if a class is a Repository class. This is done by checking it extends the
 * {@link org.springframework.data.repository.Repository Repository} interface or applied the
 * {@link org.springframework.stereotype.Repository Repository} annotation.
 */
public final class IsSpringRepositoryClass extends DescribedPredicate<JavaClass> {

    public IsSpringRepositoryClass() {
        super("Spring repository classes");
    }

    @Override
    public final boolean test(final JavaClass javaClass) {
        return javaClass.isAssignableTo(Repository.class)
                || javaClass.isMetaAnnotatedWith(org.springframework.stereotype.Repository.class);
    }

}
