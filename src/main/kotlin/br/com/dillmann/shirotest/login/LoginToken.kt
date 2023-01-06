package br.com.dillmann.shirotest.login

import org.apache.shiro.authc.AuthenticationToken

data class LoginToken(val payload: LoginDto) : AuthenticationToken {

    override fun getPrincipal() =
        payload

    override fun getCredentials() =
        payload

}