
package com.bernardomg.framework.testing.architecture.rule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

import org.springframework.stereotype.Service;

import com.bernardomg.framework.testing.architecture.predicates.Predicates;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

public final class ServiceRules {

    /**
     * TODO: the predicate already checks this is in a service package.
     */
    @ArchTest
    static final ArchRule services_should_be_in_service_package = classes().that(Predicates.areServiceClasses())
        .should()
        .resideInAPackage("..service..");

    @ArchTest
    static final ArchRule services_should_be_suffixed           = classes().that(Predicates.areServiceClasses())
        .should()
        .haveSimpleNameEndingWith("Service");

    @ArchTest
    static final ArchRule services_should_not_use_autoscan      = classes().that(Predicates.areServiceClasses())
        .and()
        .areNotInterfaces()
        .should()
        .notBeAnnotatedWith(Service.class);

}
