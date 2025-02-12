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

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.fields;

import java.io.Serializable;

import com.bernardomg.framework.testing.architecture.condition.BeAnnotatedWithTableOnClassOrSuperclass;
import com.bernardomg.framework.testing.architecture.predicates.IsAbstractClass;
import com.bernardomg.framework.testing.architecture.predicates.Predicates;
import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Transient;

/**
 * JPA entity rules.
 */
public final class JpaEntityRules {

    /**
     * Serial UID should be transient.
     */
    @ArchTest
    static final ArchRule entity_serial_uid_should_be_transient   = fields().that()
        .areDeclaredInClassesThat(Predicates.areJpaEntitiesClasses())
        .and()
        .haveName("serialVersionUID")
        .should()
        .beAnnotatedWith(Transient.class);

    /**
     * JPA entities should be annotated.
     */
    @ArchTest
    static final ArchRule jpa_entities_should_be_annotated        = classes().that(Predicates.areJpaEntitiesClasses())
        .should()
        .beAnnotatedWith(Entity.class)
        .andShould(new BeAnnotatedWithTableOnClassOrSuperclass())
        .orShould()
        .beAnnotatedWith(Embeddable.class);

    /**
     * JPA entities should be in a model package.
     */
    @ArchTest
    static final ArchRule jpa_entities_should_be_in_model_package = classes().that(Predicates.areJpaEntitiesClasses())
        .should()
        .resideInAPackage("..adapter.inbound.jpa.model..");

    /**
     * JPA entities should be serializable.
     */
    @ArchTest
    static final ArchRule jpa_entities_should_be_serializable     = classes().that(Predicates.areJpaEntitiesClasses()
        .and(DescribedPredicate.not(new IsAbstractClass())))
        .should()
        .beAssignableTo(Serializable.class);

    /**
     * JPA entities should be suffixed.
     */
    @ArchTest
    static final ArchRule jpa_entities_should_be_suffixed         = classes().that()
        .areAnnotatedWith(Entity.class)
        .should()
        .haveSimpleNameEndingWith("Entity");

    /**
     * JPA entity fields should be annotated.
     */
    @ArchTest
    static final ArchRule jpa_entity_fields_should_be_annotated   = fields().that()
        .areDeclaredInClassesThat(Predicates.areJpaEntitiesClasses())
        .and()
        .areNotStatic()
        .should()
        .beAnnotatedWith(Predicates.areJpaAnnotations());

    private JpaEntityRules() {
        super();
    }

}
