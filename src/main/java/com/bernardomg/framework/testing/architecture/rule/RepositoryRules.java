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

package com.bernardomg.framework.testing.architecture.rule;

import static com.tngtech.archunit.lang.conditions.ArchConditions.haveNameStartingWith;
import static com.tngtech.archunit.lang.conditions.ArchConditions.not;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.methods;

import org.springframework.stereotype.Repository;

import com.bernardomg.framework.testing.architecture.predicates.Predicates;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

/**
 * Repository rules.
 */
public final class RepositoryRules {

    /**
     * JPA repositories should be prefixed.
     */
    @ArchTest
    static final ArchRule jpa_repositories_should_be_prefixed                            = classes()
        .that(Predicates.areRepositoryClasses())
        .and()
        .resideInAPackage("..adapter.inbound.jpa.repository..")
        .should()
        .haveSimpleNameStartingWith("Jpa");

    /**
     * Repository interfaces should be in the domain package.
     */
    @ArchTest
    static final ArchRule repositories_interfaces_should_be_in_domain_repository_package = classes()
        .that(Predicates.areRepositoryClasses())
        .and()
        .areInterfaces()
        .should()
        .resideInAPackage("..domain.repository..");

    /**
     * Repositories should not use the Spring annotation.
     */
    @ArchTest
    static final ArchRule repositories_should_be_annotated_with_spring                   = classes()
        .that(Predicates.areRepositoryClasses())
        .should()
        .notBeAnnotatedWith(Repository.class);

    /**
     * Spring repositories should be in a JPA package.
     * <p>
     * TODO: this only should affect JPA repositories
     */
    @ArchTest
    static final ArchRule spring_repositories_should_be_in_jpa_repository_package        = classes()
        .that(Predicates.areSpringRepositoryClasses())
        .should()
        .resideInAPackage("..adapter.inbound.jpa.repository..");

    /**
     * Spring repositories should be suffixed.
     */
    @ArchTest
    static final ArchRule spring_repositories_should_be_suffixed                         = classes()
        .that(Predicates.areSpringRepositoryClasses())
        .should()
        .haveSimpleNameEndingWith("SpringRepository");

    /**
     * Spring repositories should not have a findOne method.
     */
    @ArchTest
    static final ArchRule spring_repositories_should_no_have_find_one                    = methods().that()
        .areDeclaredInClassesThat(Predicates.areSpringRepositoryClasses())
        .and()
        .arePublic()
        .should(not(haveNameStartingWith("findOne")));

    private RepositoryRules() {
        super();
    }

}
