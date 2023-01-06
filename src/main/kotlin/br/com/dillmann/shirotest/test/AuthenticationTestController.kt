package br.com.dillmann.shirotest.test

import org.apache.shiro.SecurityUtils
import org.apache.shiro.authz.AuthorizationException
import org.apache.shiro.authz.annotation.RequiresAuthentication
import org.apache.shiro.authz.annotation.RequiresGuest
import org.apache.shiro.authz.annotation.RequiresUser
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/authentication")
class AuthenticationTestController {

    @RequiresAuthentication
    @GetMapping("/requires-authenticated-by-annotation")
    fun requiresAuthenticatedByAnnotation() =
        ResponseEntity.noContent().build<Any>()

    @GetMapping("/requires-authenticated-by-programmatic")
    fun requiresAuthenticatedByProgrammatic(): ResponseEntity<Any> {
        val subject = SecurityUtils.getSubject()
        if (!subject.isAuthenticated)
            throw AuthorizationException("Current subject is not authenticated")

        return ResponseEntity.noContent().build()
    }

    @RequiresGuest
    @GetMapping("/requires-guest-by-annotation")
    fun requiresGuestByAnnotation() =
        ResponseEntity.noContent().build<Any>()

    @GetMapping("/requires-guest-by-programmatic")
    fun requiresGuestByProgrammatic(): ResponseEntity<Any> {
        val subject = SecurityUtils.getSubject()
        if (subject.principals?.isEmpty == false)
            throw AuthorizationException("Current subject is not a guest")

        return ResponseEntity.noContent().build()
    }

    @RequiresUser
    @GetMapping("/requires-user-by-annotation")
    fun requiresUserByAnnotation() =
        ResponseEntity.noContent().build<Any>()

    @GetMapping("/requires-user-by-programmatic")
    fun requiresUserByProgrammatic(): ResponseEntity<Any> {
        val subject = SecurityUtils.getSubject()
        if (subject.principals?.isEmpty != false)
            throw AuthorizationException("Current subject is an user")

        return ResponseEntity.noContent().build()
    }
}