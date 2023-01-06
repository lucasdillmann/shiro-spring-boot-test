package br.com.dillmann.shirotest

import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ApacheShiroConfiguration {

    @Bean
    fun shiroFilterChainDefinition(): ShiroFilterChainDefinition =
        DefaultShiroFilterChainDefinition()
            .also { it.addPathDefinition("/**", "anon") }


}