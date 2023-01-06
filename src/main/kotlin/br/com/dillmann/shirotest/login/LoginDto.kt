package br.com.dillmann.shirotest.login

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty

class LoginDto {
    var permissions: List<@NotBlank String>? = null
    var roles: List<@NotBlank String>? = null
}
