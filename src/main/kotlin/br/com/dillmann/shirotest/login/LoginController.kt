package br.com.dillmann.shirotest.login

import jakarta.validation.Valid
import jakarta.validation.constraints.NotNull
import org.apache.shiro.SecurityUtils
import org.apache.shiro.authz.annotation.RequiresAuthentication
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class LoginController {

    @PostMapping("/login")
    fun login(@Valid @NotNull @RequestBody payload: LoginDto) {
        val subject = SecurityUtils.getSubject()
        if (subject.isAuthenticated)
            subject.logout()

        val token = LoginToken(payload)
        subject.login(token)
    }

    @GetMapping("/logout")
    @RequiresAuthentication
    fun logout() {
        SecurityUtils.getSubject().logout()
    }

    @GetMapping("/subject")
    fun currentSubject(realm: LoginRealm): ResponseEntity<Any> {
        val subject = SecurityUtils.getSubject()
        val payload = mapOf(
            "principals" to subject.principals,
            "authenticated" to subject.isAuthenticated,
            "session" to subject.session,
            "authorizationInfo" to realm.buildAuthorizationInfo(subject.principals)
        )

        return ResponseEntity.ok(payload)
    }

}