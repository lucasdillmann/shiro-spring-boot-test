package br.com.dillmann.shirotest.test

import org.apache.shiro.SecurityUtils
import org.apache.shiro.authz.annotation.RequiresPermissions
import org.apache.shiro.authz.annotation.RequiresRoles
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/authorization")
class AuthorizationTestController {

    @RequiresRoles(value = ["admin"])
    @GetMapping("/requires-admin-role-by-annotation")
    fun requiresAdminRoleByAnnotation() =
        ResponseEntity.noContent().build<Any>()

    @GetMapping("/requires-admin-role-by-programmatic")
    fun requiresAdminRoleByProgrammatic(): ResponseEntity<Any> {
        SecurityUtils.getSubject().checkRole("admin")
        return ResponseEntity.noContent().build<Any>()
    }

    @GetMapping("/requires-lorem-ipsum-permission-by-annotation")
    @RequiresPermissions(value = ["lorem-ipsum"])
    fun requiresLoremIpsumPermissionByAnnotation() =
        ResponseEntity.noContent().build<Any>()

    @GetMapping("/requires-lorem-ipsum-permission-by-programmatic")
    fun requiresLoremIpsumPermissionByProgrammatic(): ResponseEntity<Any> {
        SecurityUtils.getSubject().checkPermission("lorem-ipsum")
        return ResponseEntity.noContent().build()
    }
}