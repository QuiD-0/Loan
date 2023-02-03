package com.quid.loan.utils


class ServiceException(
    val statusCode: StatusCode,
    override val message: String,
    override val cause: Throwable? = null
) : RuntimeException(message, cause)


fun fail(msg: String): Nothing = throw IllegalStateException(msg)

fun fail(statusCode: StatusCode): Nothing = throw ServiceException(statusCode, statusCode.message)