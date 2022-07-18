package com.web

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


@SpringBootApplication
open class BootWebApplication

fun main(args: Array<String>) {
    runApplication<BootWebApplication>(*args)
}