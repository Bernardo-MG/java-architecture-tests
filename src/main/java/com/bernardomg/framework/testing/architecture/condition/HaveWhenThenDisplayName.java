
package com.bernardomg.framework.testing.architecture.condition;

import java.util.regex.Pattern;

import com.tngtech.archunit.core.domain.JavaMethod;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.SimpleConditionEvent;

/**
 * Checks if a test method has a valid display name.
 */
public final class HaveWhenThenDisplayName extends ArchCondition<JavaMethod> {

    private static final String  DISPLAY_NAME = "org.junit.jupiter.api.DisplayName";

    private static final Pattern FORMAT       = Pattern
        .compile("^When \\S(?:[^\\r\\n]*\\S)?, then \\S(?:[^\\r\\n]*\\S)?$");

    public HaveWhenThenDisplayName() {
        super("have @DisplayName 'When <condition or action>, then <observable result>'");
    }

    @Override
    public void check(final JavaMethod method, final ConditionEvents events) {
        final String value;

        if (method.isAnnotatedWith(DISPLAY_NAME)) {
            value = method.getAnnotationOfType(DISPLAY_NAME)
                .get("value")
                .map(Object::toString)
                .orElse("");
        } else {
            value = "";
        }

        events.add(new SimpleConditionEvent(method, FORMAT.matcher(value)
            .matches(),
            method.getFullName() + " must have @DisplayName "
                    + "'When <condition or action>, then <observable result>'; found '" + value + "'"));
    }
}
