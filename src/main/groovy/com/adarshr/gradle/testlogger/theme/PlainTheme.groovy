package com.adarshr.gradle.testlogger.theme

import groovy.transform.InheritConstructors
import org.gradle.api.tasks.testing.TestDescriptor
import org.gradle.api.tasks.testing.TestResult

import static org.gradle.api.tasks.testing.TestResult.ResultType.*

@InheritConstructors
class PlainTheme extends AbstractTheme {

    private static final Map RESULT_TYPE_MAPPING = [
        (SUCCESS): 'PASSED',
        (FAILURE): 'FAILED',
        (SKIPPED): 'SKIPPED'
    ]

    @Override
    String suiteText(TestDescriptor descriptor) {
        "${escape(descriptor.className)}\n"
    }

    @Override
    String testText(TestDescriptor descriptor, TestResult result) {
        def line = new StringBuilder("  Test ${escape(descriptor.name)} ${RESULT_TYPE_MAPPING[result.resultType]}")

        if (result.resultType == FAILURE) {
            line << exceptionText(descriptor, result)
        }

        line
    }
}