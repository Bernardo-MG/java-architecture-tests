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

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.fields;
import static com.tngtech.archunit.library.DependencyRules.NO_CLASSES_SHOULD_DEPEND_UPPER_PACKAGES;
import static com.tngtech.archunit.library.GeneralCodingRules.DEPRECATED_API_SHOULD_NOT_BE_USED;
import static com.tngtech.archunit.library.GeneralCodingRules.NO_CLASSES_SHOULD_ACCESS_STANDARD_STREAMS;
import static com.tngtech.archunit.library.GeneralCodingRules.NO_CLASSES_SHOULD_THROW_GENERIC_EXCEPTIONS;
import static com.tngtech.archunit.library.GeneralCodingRules.NO_CLASSES_SHOULD_USE_FIELD_INJECTION;
import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;

import org.slf4j.Logger;

import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

public class CodingRules {

    @ArchTest
    static final ArchRule  no_access_to_standard_streams          = NO_CLASSES_SHOULD_ACCESS_STANDARD_STREAMS;

    @ArchTest
    static final ArchRule  no_accesses_to_upper_package           = NO_CLASSES_SHOULD_DEPEND_UPPER_PACKAGES;

    @ArchTest
    static final ArchRule  no_cycles                              = slices().matching("..(*)..")
        .namingSlices("$2 of $1")
        .should()
        .beFreeOfCycles();

    @ArchTest
    static final ArchRule  no_deprecated_api_calls                = DEPRECATED_API_SHOULD_NOT_BE_USED;

    @ArchTest
    static final ArchRule  no_field_injection                     = NO_CLASSES_SHOULD_USE_FIELD_INJECTION;

    @ArchTest
    static final ArchRule  no_generic_exceptions                  = NO_CLASSES_SHOULD_THROW_GENERIC_EXCEPTIONS;

    @ArchTest
    private final ArchRule loggers_should_be_private_static_final = fields().that()
        .haveRawType(Logger.class)
        .should()
        .bePrivate()
        .andShould()
        .beStatic()
        .andShould()
        .beFinal();
}
