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

import static com.tngtech.archunit.lang.conditions.ArchPredicates.are;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.methods;

import com.bernardomg.framework.testing.architecture.predicates.Predicates;
import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.library.ProxyRules;

/**
 * Cache rules.
 */
public final class CacheRules {

    /**
     * Cache configuration should be in the outbound layer.
     */
    @ArchTest
    static final ArchRule cache_configuration_should_be_outbound                 = classes().that()
        .haveSimpleNameEndingWith("Caches")
        .should()
        .resideInAPackage("..adapter.outbound.cache..")
        .because("caching should be configured on outbound layer");

    /**
     * Only controllers are cached.
     */
    @ArchTest
    static final ArchRule classes_which_are_not_controllers_should_not_be_cached = methods().that()
        .areDeclaredInClassesThat(DescribedPredicate.not(Predicates.areControllerClasses()))
        .should()
        .notBeAnnotatedWith(Predicates.areCachingAnnotation())
        .because("caching should be applied only on controllers");

    /**
     * Cacheable methods are not called directly.
     */
    @ArchTest
    static final ArchRule no_direct_calls_to_cacheable_method                    = ProxyRules
        .no_classes_should_directly_call_other_methods_declared_in_the_same_class_that(
            are(Predicates.areCachedMethod()));

    /**
     * Services should not have caches.
     */
    @ArchTest
    static final ArchRule services_should_not_be_cached                          = classes()
        .that(Predicates.areServiceClasses())
        .and()
        .areNotInterfaces()
        .should()
        .notBeAnnotatedWith(Predicates.areCachingAnnotation());

    private CacheRules() {
        super();
    }

}
