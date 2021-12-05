package com.ggp.blog.application

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["com.ggp.blog.application", "com.ggp.blog.domain", "com.ggp.blog.infrastructure"])
class BlogApplication

fun main(args: Array<String>) {
    runApplication<BlogApplication>(*args)
}
