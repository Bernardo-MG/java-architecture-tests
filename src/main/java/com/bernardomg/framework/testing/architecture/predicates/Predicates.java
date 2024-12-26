/**
 * The MIT License (MIT)
 * <p>
 * Copyright (c) 2024-2025 the original author or authors.
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

import com.bernardomg.framework.testing.architecture.predicates.springframework.IsRepositoryNotSpringClass;
import com.bernardomg.framework.testing.architecture.predicates.springframework.IsSpringCacheAnnotation;
import com.bernardomg.framework.testing.architecture.predicates.springframework.IsSpringCachedMethod;
import com.bernardomg.framework.testing.architecture.predicates.springframework.IsSpringConfigurationClass;
import com.bernardomg.framework.testing.architecture.predicates.springframework.IsSpringControllerClass;
import com.bernardomg.framework.testing.architecture.predicates.springframework.IsSpringRepositoryClass;
import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.AccessTarget.MethodCallTarget;
import com.tngtech.archunit.core.domain.JavaAnnotation;
import com.tngtech.archunit.core.domain.JavaClass;

public final class Predicates {

    public static final DescribedPredicate<MethodCallTarget> areCachedMethod() {
        return new IsSpringCachedMethod();
    }

    public static final DescribedPredicate<JavaAnnotation<?>> areCachingAnnotation() {
        return new IsSpringCacheAnnotation();
    }

    public static final DescribedPredicate<JavaClass> areConfigurationClasses() {
        return new IsSpringConfigurationClass();
    }

    public static final DescribedPredicate<JavaClass> areControllerClasses() {
        return new IsSpringControllerClass();
    }

    public static final DescribedPredicate<JavaAnnotation<?>> areJpaAnnotations() {
        return new IsJpaAnnotation();
    }

    public static final DescribedPredicate<JavaClass> areJpaEntitiesClasses() {
        return new IsJpaAnnotatedClass();
    }

    public static final DescribedPredicate<JavaClass> areRepositoryClasses() {
        return new IsRepositoryNotSpringClass();
    }

    public static final DescribedPredicate<JavaClass> areServiceClasses() {
        return new IsInServicePackage();
    }

    public static final DescribedPredicate<JavaClass> areSpringRepositoryClasses() {
        return new IsSpringRepositoryClass();
    }

    public static final DescribedPredicate<JavaClass> areValidatorClasses() {
        return new IsValidatorClass();
    }

    public static final IsValidatorRuleClass areValidatorRuleClasses() {
        return new IsValidatorRuleClass();
    }

    private Predicates() {
        super();
    }

}
