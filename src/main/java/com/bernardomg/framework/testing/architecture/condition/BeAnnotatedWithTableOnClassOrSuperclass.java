
package com.bernardomg.framework.testing.architecture.condition;

import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.SimpleConditionEvent;

import jakarta.persistence.Table;

/**
 * Checks if a class is annotated with @Table, or else if any super class is annotated with it.
 */
public final class BeAnnotatedWithTableOnClassOrSuperclass extends ArchCondition<JavaClass> {

    public BeAnnotatedWithTableOnClassOrSuperclass() {
        super("JPA table annotation");
    }

    @Override
    public final void check(final JavaClass javaClass, final ConditionEvents events) {
        final boolean hasTableAnnotation;
        final String  message;

        hasTableAnnotation = javaClass.isAnnotatedWith(Table.class) || javaClass.getAllRawSuperclasses()
            .stream()
            .anyMatch(superClass -> superClass.isAnnotatedWith(Table.class));

        if (!hasTableAnnotation) {
            message = javaClass.getName()
                    + " is neither annotated with @Table nor inherits from a class annotated with @Table.";
            events.add(SimpleConditionEvent.violated(javaClass, message));
        }
    }

}
