package jp.co.casareal.kotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
//@ComponentScan("jp.co.casareal")
class KotlinApplication

fun main(args: Array<String>) {
    runApplication<KotlinApplication>(*args)
}
