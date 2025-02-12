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

import com.bernardomg.framework.testing.architecture.predicates.IsValidatorClass;
import com.bernardomg.framework.testing.architecture.predicates.IsValidatorRuleClass;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

/**
 * Validation rules.
 */
public final class ValidationRules {

    /**
     * Validator rules should be in the validation package.
     */
    @ArchTest
    static final ArchRule validator_rules_should_be_in_validation_package = classes().that(new IsValidatorRuleClass())
        .should()
        .resideInAPackage("..validation..")
        .allowEmptyShould(true);

    /**
     * Validator rules should be suffixed.
     */
    @ArchTest
    static final ArchRule validator_rules_should_be_suffixed              = classes().that(new IsValidatorRuleClass())
        .should()
        .haveSimpleNameEndingWith("Rule")
        .allowEmptyShould(true);

    /**
     * Validators should be in a validation package.
     */
    @ArchTest
    static final ArchRule validators_should_be_in_validation_package      = classes().that(new IsValidatorClass())
        .should()
        .resideInAPackage("..validation..")
        .allowEmptyShould(true);

    /**
     * Validators should be suffixed.
     */
    @ArchTest
    static final ArchRule validators_should_be_suffixed                   = classes().that(new IsValidatorClass())
        .should()
        .haveSimpleNameEndingWith("Validator")
        .allowEmptyShould(true);

    private ValidationRules() {
        super();
    }

}
