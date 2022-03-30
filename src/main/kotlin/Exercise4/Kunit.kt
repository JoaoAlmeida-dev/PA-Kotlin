package Exercise4

import java.lang.reflect.InvocationTargetException
import kotlin.reflect.KClass
import kotlin.reflect.KFunction
import kotlin.reflect.full.createInstance
import kotlin.reflect.full.declaredFunctions
import kotlin.reflect.full.hasAnnotation
import kotlin.test.assertTrue

class Kunit(val kClass: KClass<*>) {

    fun runTests() {
        var beforeTestRunFunction: KFunction<*>? = null
        val testFunctionList: MutableList<KFunction<*>> = mutableListOf()
        val instance = kClass.createInstance()
        kClass.declaredFunctions.forEach {
            val actual: Boolean = (it.hasAnnotation<BeforeTestRun>() && !it.hasAnnotation<TestCase>()) ||
                    (!it.hasAnnotation<BeforeTestRun>() && it.hasAnnotation<TestCase>()) ||
                    (!it.hasAnnotation<BeforeTestRun>() && !it.hasAnnotation<TestCase>())

            assertTrue(actual,
                "Can only have TestCase annotation or BeforeTestRun, not both , OFFENDER: ${it.name}")

            if (it.hasAnnotation<BeforeTestRun>()) {
                beforeTestRunFunction = it
            }
            if (it.hasAnnotation<TestCase>()) {
                testFunctionList.add(it)
            }
        }

        try {
            beforeTestRunFunction?.call(instance)
        } catch (e: InvocationTargetException) {
            println("Test failed: ${beforeTestRunFunction?.name}\n\t${e.cause?.message}")
        }

        testFunctionList.forEach {
            println("Calling test case for: ${it.name}")
            try {
                it.call(instance)
            } catch (e: InvocationTargetException) {
                println("Test failed: ${it.name}\n\t${e.cause?.message}")

            }
        }
    }
}
