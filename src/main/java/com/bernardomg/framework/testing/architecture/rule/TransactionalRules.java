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

package com.bernardomg.framework.testing.architecture.rule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

import org.springframework.transaction.annotation.Transactional;

import com.bernardomg.framework.testing.architecture.predicates.Predicates;
import com.tngtech.archunit.core.domain.JavaModifier;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.library.ProxyRules;

/**
 * Transactional rules.
 */
public final class TransactionalRules {

    /**
     * Controllers should not be transactional.
     */
    @ArchTest
    static final ArchRule controllers_should_not_be_transactional = classes().that(Predicates.areControllerClasses())
        .should()
        .notBeAnnotatedWith(Transactional.class);

    /**
     * There should be no direct call to transactional methods.
     */
    @ArchTest
    static final ArchRule no_direct_calls_to_transactional_method = ProxyRules
        .no_classes_should_directly_call_other_methods_declared_in_the_same_class_that_are_annotated_with(
            Transactional.class);

    /**
     * Repositories should be transactional.
     */
    @ArchTest
    static final ArchRule repositories_should_be_transactional    = classes().that(Predicates.areRepositoryClasses())
        .and()
        .doNotHaveModifier(JavaModifier.ABSTRACT)
        .and()
        .areNotInterfaces()
        .should()
        .beAnnotatedWith(Transactional.class);

    /**
     * Services should be transactional.
     */
    @ArchTest
    static final ArchRule services_should_be_transactional        = classes().that(Predicates.areServiceClasses())
        .and()
        .doNotHaveModifier(JavaModifier.ABSTRACT)
        .and()
        .areNotInterfaces()
        .should()
        .beAnnotatedWith(Transactional.class);

    private TransactionalRules() {
        super();
    }

}
