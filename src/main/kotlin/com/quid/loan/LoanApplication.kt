package com.quid.loan

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@EnableJpaAuditing
@SpringBootApplication
class LoanApplication

fun main(args: Array<String>) {
    runApplication<LoanApplication>(*args)
}
