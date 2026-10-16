
package com.bernardomg.framework.testing.architecture.rule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.methods;

import com.bernardomg.framework.testing.architecture.condition.HaveWhenThenDisplayName;
import com.bernardomg.framework.testing.architecture.predicates.IsTestClass;
import com.bernardomg.framework.testing.architecture.predicates.IsTestMethod;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

/** Naming, visibility and display-name rules for JUnit Jupiter tests. */
public final class TestRules {

    @ArchTest
    static final ArchRule test_classes_should_be_package_private       = classes().that(new IsTestClass())
        .should()
        .bePackagePrivate();

    @ArchTest
    static final ArchRule test_methods_should_be_package_private       = methods().that(new IsTestMethod())
        .should()
        .bePackagePrivate();

    @ArchTest
    static final ArchRule test_methods_should_have_clear_display_names = methods().that(new IsTestMethod())
        .should(new HaveWhenThenDisplayName());

    @ArchTest
    static final ArchRule tests_should_start_with_Test_or_IT           = classes().that(new IsTestClass())
        .should()
        .haveSimpleNameStartingWith("Test")
        .orShould()
        .haveSimpleNameStartingWith("IT");

    private TestRules() {
        super();
    }

}
