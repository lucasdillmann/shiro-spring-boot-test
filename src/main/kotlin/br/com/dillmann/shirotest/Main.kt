package br.com.dillmann.shirotest

import org.apache.shiro.spring.boot.autoconfigure.ShiroAutoConfiguration
import org.apache.shiro.spring.config.web.autoconfigure.ShiroWebAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan(
    basePackageClasses = [
        Main::class,
        ShiroWebAutoConfiguration::class,
        ShiroAutoConfiguration::class,
    ]
)
class Main

fun main(args: Array<String>) {
    runApplication<Main>(*args)
}
