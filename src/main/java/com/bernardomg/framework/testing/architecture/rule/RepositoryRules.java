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

public final class RepositoryRules {

    @ArchTest
    static final ArchRule jpa_repositories_should_be_prefixed                            = classes()
        .that(Predicates.areRepositoryClasses())
        .and()
        .resideInAPackage("..adapter.inbound.jpa.repository..")
        .should()
        .haveSimpleNameStartingWith("Jpa");

    @ArchTest
    static final ArchRule jpa_repositories_should_implement_repository_interface         = classes()
        .that(Predicates.areRepositoryClasses())
        .and()
        .areNotInterfaces()
        .should()
        .implement(Predicates.areRepositoryClasses());

    @ArchTest
    static final ArchRule repositories_interfaces_should_be_in_domain_repository_package = classes()
        .that(Predicates.areRepositoryClasses())
        .and()
        .areInterfaces()
        .should()
        .resideInAPackage("..domain.repository..");

    @ArchTest
    static final ArchRule repositories_should_not_use_autoscan                           = classes()
        .that(Predicates.areRepositoryClasses())
        .should()
        .notBeAnnotatedWith(Repository.class);

    @ArchTest
    static final ArchRule spring_repositories_should_be_in_jpa_repository_package        = classes()
        .that(Predicates.areSpringRepositoryClasses())
        .should()
        .resideInAPackage("..adapter.inbound.jpa.repository..");

    @ArchTest
    static final ArchRule spring_repositories_should_be_suffixed                         = classes()
        .that(Predicates.areSpringRepositoryClasses())
        .should()
        .haveSimpleNameEndingWith("SpringRepository");

    @ArchTest
    static final ArchRule spring_repositories_should_no_have_find_one                    = methods().that()
        .areDeclaredInClassesThat(Predicates.areSpringRepositoryClasses())
        .and()
        .arePublic()
        .should(not(haveNameStartingWith("findOne")));

}
