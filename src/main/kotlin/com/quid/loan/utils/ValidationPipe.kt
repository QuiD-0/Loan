package com.quid.loan.utils

@FunctionalInterface
interface Step<I> {
    fun process(input: I): I
}

class ValidationPipe<I> (private val current: Step<I>) {

    fun addPipe(next: Step<I>): ValidationPipe<I> {
        return ValidationPipe(object : Step<I> {
            override fun process(input: I): I {
                return next.process(current.process(input))
            }
        })
    }

    fun execute(input: I): I {
        return current.process(input)
    }

}