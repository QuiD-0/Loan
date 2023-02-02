package com.quid.loan.utils

interface Step<I, O> {
    fun process(input: I): O
}

class Pipeline<I, O>(private val current: Step<I, O>) {

    fun <NewO> pipe(next: Step<O, NewO>): Pipeline<I, NewO> {
        return Pipeline(object : Step<I, NewO> {
            override fun process(input: I): NewO {
                return next.process(current.process(input))
            }
        })
    }

    fun execute(input: I): O {
        return current.process(input)
    }

}